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

        Percolation percolation = new Percolation(size);

        double counter = 0;

        while (!percolation.percolates()) {

            counter++;

            //pick a random site
            //(N+1 because second value to uniform is exclusive)
            int i = StdRandom.uniform(1, size+1);
            int j = StdRandom.uniform(1, size+1);

            //generate new random sites until a blocked one is found
            while (percolation.isOpen(i, j)) {

                i = StdRandom.uniform(1, size+1);
                j = StdRandom.uniform(1, size+1);

            }

            //open that site
            percolation.open(i, j);
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
