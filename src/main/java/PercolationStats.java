import java.util.Random;

/**
 * User: alex
 * Date: 2/12/13
 * Time: 1:58 PM
 */
public class PercolationStats {

    private int experimentCount; // number of experiments
    private int size; // field size
    private double[] experiments;

    /*
       Perform T independent computational experiments on an N-by-N grid
      */
    public PercolationStats(int N, int T) {
        if (N < 1 || T < 1)
            throw new IllegalArgumentException("Wrong N or T");

        this.size = N;
        this.experimentCount = T;
    }

    /*
      Sample mean of percolation threshold
      */
    public double mean() {

        return StdStats.mean(calc());
    }

    private double[] calc() {

        if (experiments == null || experiments.length == 0) {
            double[] meanElements = new double[experimentCount];

            for (int i = 0; i < experimentCount; i++) {
                meanElements[i] = experiment();
            }

            experiments = meanElements;
        }

        return experiments;
    }

    private double experiment() {

        Percolation percolation = new Percolation(this.size);

        Random rand = new Random();
        int counter = 0;

        int i1 = 1;
        int j1 = 1;

        while (!percolation.percolates()) {
            int i = rand.nextInt(this.size + 1);
            int j = rand.nextInt(this.size + 1);

            if ((i == 0 || j == 0) || (i == i1 && j == j1)) continue;
            else {
                i1 = i;
                j1 = j;
                percolation.open(i, j);

                ++counter;
            }
        }

        return (double) counter / (size * size);
    }

    /*
      Sample standard deviation of percolation threshold
     */
    public double stddev() {

        return StdStats.stddev(calc());
    }

    /*
      Returns lower bound of the 95% confidence interval
     */
    public double confidenceLo() {
        return Math.abs(mean() - (1.96 * stddev() / Math.sqrt(experimentCount)));
    }

    /*
      Returns upper bound of the 95% confidence interval
     */
    public double confidenceHi() {
        return Math.abs(mean() + (1.96 * stddev() / Math.sqrt(experimentCount)));
    }

    public static void main(String[] args)   // test client, described below
    {
        PercolationStats stats = new PercolationStats(Integer.valueOf(args[0]), Integer.valueOf(args[1]));
        System.out.println("mean = " + stats.mean());
        System.out.println("stddev = " + stats.stddev());
        System.out.println("95% confidence interval = " + stats.confidenceLo() + ", " + stats.confidenceHi());
    }
}
