import java.util.Arrays;

/**
 * User: alex
 * Date: 3/8/13
 * Time: 11:29 AM
 */
public class Brute {

    public static void main(String[] args) {
        String filename = args[0];
        In input = new In(filename);
        int N = input.readInt();
        if (N < 4)
            return;

        Point[] points = new Point[N];

        for (int i = 0; i < N; i++) {
            int x = input.readInt();
            int y = input.readInt();
            points[i] = new Point(x, y);
        }

        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);

        for(int i = 0; i < N; i++)
            for(int j = 0; i < N && j != i; j++)
                for(int k = 0; k < N && k != j && k != i; k++)
                    for(int l = 0; l < N && l != k && l != j && l != i; l++)
                    {
                         Point p1 = points[i];
                         Point p2 = points[j];
                         Point p3 = points[k];
                         Point p4 = points[l];

                         if(col(p1, p2, p3, p4))
                             drawPoints(p1, p2, p3, p4);
                    }

    }

    private static boolean col(Point p1, Point p2, Point p3, Point p4) {
        double slope1 = p1.slopeTo(p2);
        double slope2 = p2.slopeTo(p3);
        double slope3 = p3.slopeTo(p4);
        if (slope1 == slope2 && slope2 == slope3) {
            return true;
        }
        return false;
    }

    private static void drawPoints(Point p1, Point p2, Point p3, Point p4) {
        Point[] points = new Point[4];
        points[0] = p1;
        points[1] = p2;
        points[2] = p3;
        points[3] = p4;
        Arrays.sort(points);
        StdOut.print(points[0] + " -> " + points[1] + " -> " + points[2]
                + " -> " + points[3]);
        StdOut.println();
        points[0].draw();
        points[1].draw();
        points[2].draw();
        points[3].draw();
        points[0].drawTo(points[3]);
    }
}
