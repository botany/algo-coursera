import org.junit.Test;

/**
 * User: alex
 * Date: 3/6/13
 * Time: 2:44 PM
 */
public class SortTests {
    @Test
    public void testMergeSortOutput() {
       Integer[] a = new Integer[]{74, 35, 46, 82, 25, 96, 63, 21, 15, 72, 77, 18 };

       Merge.sort(a);
    }

    @Test
    public void testMergeSortBUOutput() {
        Integer[] a = new Integer[]{92, 62, 19, 28, 42, 59, 35, 87, 34, 41  };

        MergeBU.sort(a);
    }

    @Test
    public void testGrahamScan() {
      Point2D[] points = new Point2D[]{
              new Point2D(0.0, 1.0), // A
              new Point2D(5.0, 8.0), // B
              new Point2D(6.0, 0.0), // C
              new Point2D(4.0, 3.0), // D
              new Point2D(2.0, 4.0), // E
              new Point2D(7.0, 6.0), // F
              new Point2D(1.0, 7.0), // G
              new Point2D(8.0, 9.0), // H
              new Point2D(3.0, 5.0), // I
              new Point2D(9.0, 2.0), // J
      };
      GrahamScan gs = new GrahamScan(points);
        for (Point2D p : gs.hull())
            StdOut.println(p);
    }

    @Test
    public void testQuickSortPart() {

        Integer[] a = new Integer[]{42, 51, 66, 72, 83, 40, 99, 91, 24, 32, 33, 98  };

        partition(a, 0, a.length - 1);

        for(Integer i : a)
        {
            System.out.print(i.toString() + ' ');
        }
    }

    @Test
    public void testQuickSortPartChar() {

        Character[] a = new Character[]{'B', 'B', 'A', 'B', 'A', 'A', 'B', 'B', 'B', 'A', 'B', 'B'};

        partition(a, 0, a.length - 1);

        for(Character i : a)
        {
            System.out.print(i.toString() + ' ');
        }
    }

    @Test
    public void test3WayPartition() {
        Integer[] a = new Integer[]{52, 81, 84, 52, 52, 54, 59, 57, 22, 55};

        partition3way(a, 0, a.length - 1);

        for(Integer i : a)
        {
            System.out.print(i.toString() + ' ');
        }
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        while (true) {

            // find item on lo to swap
            while (less(a[++i], v))
                if (i == hi) break;

            // find item on hi to swap
            while (less(v, a[--j]))
                if (j == lo) break;      // redundant since a[lo] acts as sentinel

            // check if pointers cross
            if (i >= j) break;

            exch(a, i, j);
        }

        // put v = a[j] into position
        exch(a, lo, j);

        // with a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }

    private static void partition3way(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        Comparable v = a[lo];
        int i = lo;
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if      (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else              i++;
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
