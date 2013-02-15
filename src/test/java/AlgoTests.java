import junit.framework.Assert;
import org.junit.Test;
/**
 * User: alex
 * Date: 2/11/13
 * Time: 4:12 PM
 */
public class AlgoTests {

//    @Test
//    public void testQuickFind() {
//        QuickFindUF qf = new QuickFindUF(10);
//        qf.union(0, 9);
//        qf.union(3, 6);
//        qf.union(2, 7);
//        qf.union(4, 9);
//        qf.union(3, 7);
//        qf.union(9, 1);
//    }
//
//    @Test
//    public void testWeightedQuickFind() {
//        WeightedQuickUnionUF wqu = new WeightedQuickUnionUF(10);
//        wqu.union(9, 5);
//        wqu.union(6, 0);
//        wqu.union(5, 2);
//        wqu.union(4, 7);
//        wqu.union(5, 3);
//        wqu.union(2, 1);
//        wqu.union(7, 8);
//        wqu.union(4, 6);
//        wqu.union(2, 6);
//    }

    @Test
    public void testWeightedQuickUnionVariants() {
        WeightedQuickUnionUF wqu = new WeightedQuickUnionUF(10);
    }

    @Test
    public void testPercolation() {
        PercolationStats stats = new PercolationStats(200, 100);

        System.out.println("mean = " + stats.mean());
        System.out.println("stddev = " + stats.stddev());
        System.out.println("95% confidence interval = " + stats.confidenceLo() + ", " + stats.confidenceHi());
    }

    @Test
    public void testVisualizer() throws Exception {
        int n = 100;
        PercolationVisualizer.simulateFromFile("/home/alex/dev/java/algo/src/test/resources/input8.txt");

        Thread.sleep(100000);
    }

    @Test
    public void testVisualizerRandom() throws Exception {
        int n = 10;
        PercolationVisualizer.simulateFromRandom(n);

        Thread.sleep(100000);
    }

    @Test
    public void testPercolationCorrectness() {
        int n = 8;
        Percolation percolation = new Percolation(n);

        percolation.open(1, 6);

        Assert.assertTrue(percolation.isOpen(1, 6));

        percolation.open(2,6);

        Assert.assertTrue(percolation.isOpen(2, 6));
        Assert.assertTrue(percolation.isFull(2, 6));

        percolation.open(2,7);
        percolation.open(3,7);
        percolation.open(4,7);

        Assert.assertTrue(percolation.isFull(4, 7));
    }

    @Test
    public void testException() {
        int n = 10;
        Percolation percolation = new Percolation(n);

        percolation.open(6, 12);
    }

    @Test
    public void testSmallN() {
        int n = 1;
        Percolation percolation = new Percolation(n);

        Assert.assertFalse(percolation.percolates());

        n = 2;
        percolation = new Percolation(n);

        Assert.assertFalse(percolation.percolates());
    }

    @Test
    public void testSmallNPercolates() {
        int n = 5;
        Percolation percolation = new Percolation(n);

        percolation.open(1, 4);
        percolation.open(5, 1);
        percolation.open(2, 4);
        percolation.open(3, 4);
        percolation.open(4, 4);
        percolation.open(4, 5);
        percolation.open(5, 5);

        Assert.assertTrue(percolation.percolates());

        Assert.assertFalse(percolation.isFull(5, 3));

    }

    @Test
    public void testPercolationStats() {
        Stopwatch sw = new Stopwatch();
        PercolationStats percolationStats = new PercolationStats(200, 100);
        System.out.println("mean = " + percolationStats.mean());
        System.out.println("stddev = " + percolationStats.stddev());
        System.out.println("95% confidence interval = " + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi());
        System.out.println(sw.elapsedTime());
    }

    @Test
    public void testPercolates() {
        int passes = 0;
        int total = 0;
        int[][] works = {{1, 1},         {1, 3},
                {2, 1}, {2, 2},         {2, 4},
                {3, 2}, {3, 3},
                {4, 1},          {4, 3}};
        total++;
        if (testPercolates(4, works, true))
            passes++;

        int[][] bad = {{1, 1},         {1, 3},
                {2, 1}, {2, 2},            {2, 4},
                {3, 2},
                {4, 1},          {4, 3}};
        total++;
        if (testPercolates(4, bad, false))
            passes++;


        System.err.println("Tests: " + passes + "/" + total);
    }

    private static boolean testPercolates(int N, int[][] openSites,
                                          boolean expectation) {
        boolean result;
        Percolation tested = new Percolation(N);
        for (int[] openSite: openSites)
            tested.open(openSite[0], openSite[1]);
        result = tested.percolates();
        if (!result && expectation) {
            System.err.println("Unexpected failure");
            return false;
        }
        else if (result && !expectation) {
            System.err.println("Unexpected success");
            return false;
        }
        return true;
    }
}
