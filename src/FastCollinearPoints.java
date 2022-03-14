import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

import java.util.Arrays;

public class FastCollinearPoints {
    private final Point[] points;

    public FastCollinearPoints(Point[] points)
    {
        if(points == null)
            throw new IllegalArgumentException();

        for(int i = 0; i < points.length - 2; ++i)
        {
            if(points[i] == null)
                throw new IllegalArgumentException();

            for(int j = i + 1; j < points.length; ++j)
                if(points[i].equals(points[j]))
                    throw new IllegalArgumentException();
        }

        if(points[points.length - 1] == null)
            throw new IllegalArgumentException();

        this.points = points;
    }

    public int numberOfSegments()
    {
        int lengthOfSequence;
        int nrSegments = 0;
        Point[] aux = points.clone();

        Point pointmax;
        Point pointmin;
        Point start;
        Point end;

        for(int i = 0; i < points.length; ++i)
        {
            Arrays.sort(aux);
            Arrays.sort(aux, points[i].slopeOrder());

            for(int j = 1; j < aux.length - 1; ++j)
            {
                if(aux[0].slopeTo(aux[j]) == aux[0].slopeTo(aux[j + 1]))
                {
                    lengthOfSequence = 3;
                    int k = j + 1;

                    pointmax = aux[0];
                    pointmin = aux[0];

                    start = aux[0];
                    end = aux[0];

                    if(pointmax.compareTo(aux[j]) < 0)
                        pointmax = aux[j];

                    if(pointmin.compareTo(aux[j]) > 0)
                        pointmin = aux[j];

                    while(k < aux.length - 1 && aux[0].slopeTo(aux[j]) == aux[0].slopeTo(aux[k + 1]))
                    {
                        k++;

                        if(pointmax.compareTo(aux[k]) < 0)
                            pointmax = aux[k];

                        if(pointmin.compareTo(aux[k]) > 0)
                            pointmin = aux[k];

                        end = aux[k];

                        j = k;

                        lengthOfSequence++;
                    }

                    if(lengthOfSequence > 3 && !start.equals(pointmin) && !end.equals(pointmax))
                        nrSegments++;
                }
            }
        }

        return nrSegments;
    }

    public LineSegment[] segments()
    {
        int lengthOfSequence;
        LineSegment[] s = new LineSegment[numberOfSegments()];
        int ind = 0;
        Point[] aux = points.clone();
        Point pointmax;
        Point pointmin;
        Point start;
        Point end;

        for(int i = 0; i < points.length; ++i)
        {
            Arrays.sort(aux);
            Arrays.sort(aux, points[i].slopeOrder());

            for(int j = 1; j < aux.length - 1; ++j)
            {
                if(aux[0].slopeTo(aux[j]) == aux[0].slopeTo(aux[j + 1]))
                {
                    lengthOfSequence = 3;
                    int k = j + 1;

                    pointmax = aux[0];
                    pointmin = aux[0];

                    start = aux[0];
                    end = aux[0];

                    if(pointmax.compareTo(aux[j]) < 0)
                        pointmax = aux[j];

                    if(pointmin.compareTo(aux[j]) > 0)
                        pointmin = aux[j];

                    while(k < aux.length - 1 && aux[0].slopeTo(aux[j]) == aux[0].slopeTo(aux[k + 1]))
                    {
                        k++;

                        if(pointmax.compareTo(aux[k]) < 0)
                            pointmax = aux[k];

                        if(pointmin.compareTo(aux[k]) > 0)
                            pointmin = aux[k];

                        end = aux[k];

                        j = k;

                        lengthOfSequence++;
                    }

                    if(lengthOfSequence > 3 && !start.equals(pointmin) && !end.equals(pointmax))
                    {
                        s[ind++] = new LineSegment(pointmin, pointmax);
                    }
                }
            }
        }

        return s;
    }

    public static void main(String[] args)
    {
        In in = new In(args[0]);
        int n = in.readInt();

        Point[] points = new Point[n];

        for(int i = 0; i < n; ++i)
        {
            int x = in.readInt();
            int y = in.readInt();

            points[i] = new Point(x, y);
        }

        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        StdDraw.show();

        FastCollinearPoints collinearPoints = new FastCollinearPoints(points);

        for(LineSegment segment : collinearPoints.segments())
        {
            StdOut.println(segment);
            segment.draw();
        }

        StdDraw.show();
    }
}
