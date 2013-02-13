import java.util.Random;

/**
 * User: alex
 * Date: 2/12/13
 * Time: 1:58 PM
 */
public class PercolationStats {

    private int experimentCount; // number of experiments
    private int size; // field size
    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;

    /*
       Perform T independent computational experiments on an N-by-N grid
      */
    public PercolationStats(int N, int T) {
        this.size = N;
        this.experimentCount = T;

        calc();
    }

    /*
      Sample mean of percolation threshold
      */
    public double mean() {
        return this.mean;
    }

    private void calc() {
        double[] meanElements = new double[experimentCount];

        for (int i = 0; i < experimentCount; i++) {
            meanElements[i] = experiment();
        }

        this.mean = StdStats.mean(meanElements);
        this.stddev = StdStats.stddev(meanElements);
        this.confidenceLo = this.mean - (1.96 * stddev / Math.sqrt(experimentCount));
        this.confidenceHi = this.mean + (1.96 * stddev / Math.sqrt(experimentCount));
    }

    private double experiment() {

        Percolation percolation = new Percolation(this.size);

        Random rand = new Random();
        int counter = 0;

        int i1 = 1;
        int j1 = 1;

        while (!percolation.percolates()) {
            int i = rand.nextInt(this.size+1);
            int j = rand.nextInt(this.size+1);

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
        return stddev;
    }

    /*
      Returns lower bound of the 95% confidence interval
     */
    public double confidenceLo() {
        return Math.abs(confidenceLo);
    }

    /*
      Returns upper bound of the 95% confidence interval
     */
    public double confidenceHi() {
        return Math.abs(confidenceHi);
    }

    public static void main(String[] args)   // test client, described below
    {
        PercolationStats stats = new PercolationStats(Integer.valueOf(args[0]), Integer.valueOf(args[1]));
        System.out.println("mean = " + stats.mean());
        System.out.println("stddev = " + stats.stddev());
        System.out.println("95% confidence interval = " + stats.confidenceLo() + ", " + stats.confidenceHi());
    }
}
