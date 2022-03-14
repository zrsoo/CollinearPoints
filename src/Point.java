import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point>{
    private final int x;
    private final int y;

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    // Draws the point
    public void draw()
    {
        StdDraw.point(x, y);
    }

    // Draws the line segment from this point to that point
    public void drawTo(Point that)
    {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // String representation
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }

    // Compares two points by y-coordinates, breaking ties by x-coordinates
    public int compareTo(Point that)
    {
        if(this.y < that.y)
            return -1;

        if(this.y == that.y)
            if(this.x < that.x)
                return -1;

        return 1;
    }

    // The slope between this point and that point
    public double slopeTo(Point that)
    {
        // Equal points
        if(this.x == that.x && this.y == that.y)
            return Double.NEGATIVE_INFINITY;

        // Horizontal line
        if(this.y == that.y)
            return +0.0;

        // Vertical line
        if(this.x == that.x)
            return Double.POSITIVE_INFINITY;

        return ((double)(that.y - this.y)) / (that.x - this.x);
    }

    // Compare two points by slope they make with this point
    public Comparator<Point> slopeOrder()
    {
        return Comparator.comparingDouble(this::slopeTo);
    }
}
