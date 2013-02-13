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
}
