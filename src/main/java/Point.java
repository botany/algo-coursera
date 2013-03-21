/**
 * User: alex
 * Date: 3/7/13
 * Time: 11:21 AM
 */

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
        @Override
        public int compare(Point o1, Point o2) {
            if(o1 == null || o2 == null) throw new NullPointerException("arg is null");
            double s1 = Point.this.slopeTo(o1);
            double s2 = Point.this.slopeTo(o2);
            if (s1 < s2) return -1;
            if (s1 == s2) return 0;
            return 1;
        }
    };       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        if (that == null) throw new NullPointerException("that is null");
        if (this.y == that.y  && this.x == that.x)
            return Double.NEGATIVE_INFINITY;
        else if (this.y == that.y)
            return 0;
        else if (this.x == that.x)
            return Double.POSITIVE_INFINITY;
        return ((double)(that.y - this.y) / (double)(that.x - this.x));
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        if (this.y < that.y) return -1;
        if (this.y == that.y && this.x < that.x) return -1;
        if (this.y == that.y && this.x == that.x) return 0;
        return 1;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}
