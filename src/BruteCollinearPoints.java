import java.util.Arrays;

public class BruteCollinearPoints{
    private final Point[] points;

    // Finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points)
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

        Arrays.sort(points);
    }

    // The number of line segments
    public int numberOfSegments()
    {
        int nrSegments = 0;

        for(int i = 0; i < points.length - 3; ++i)
            for(int j = i + 1; j < points.length - 2; ++j)
                for(int k = j + 1; k < points.length - 1; ++k)
                    for(int l = k + 1; l < points.length; ++l)
                    {
                        if(points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]) &&
                                points[i].slopeTo(points[j]) == points[i].slopeTo(points[l]))
                            nrSegments++;
                    }

        return nrSegments;
    }

    // the line segments
    public LineSegment[] segments()
    {
        LineSegment[] s = new LineSegment[this.numberOfSegments()];
        int ind = 0;

        for(int i = 0; i < points.length - 3; ++i)
            for(int j = i + 1; j < points.length - 2; ++j)
                for(int k = j + 1; k < points.length - 1; ++k)
                    for(int l = k + 1; l < points.length; ++l)
                    {
                        if(points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]) &&
                                points[i].slopeTo(points[j]) == points[i].slopeTo(points[l]))
                            s[ind++] = new LineSegment(points[i], points[l]);
                    }

        return s;
    }
}
