import java.util.Comparator;

public class Point implements Comparable<Point> {
  
  // compare points by slope
  public final Comparator<Point> SLOPE_ORDER = new SlopeOrder(this);
  
  private static class SlopeOrder implements Comparator<Point> {
    private Point x0;
    
    public SlopeOrder(Point x) { this.x0 = x; }
    
    public int compare(Point p, Point q) { 
      double slopeP = x0.slopeTo(p);
      double slopeQ = x0.slopeTo(q);
      if      (slopeP < slopeQ)
        return -1;
      else if (slopeP > slopeQ)
        return 1;
      else
        return 0;
    }
  }

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
    if      (this.x == that.x && that.y == this.y)
      return Double.NEGATIVE_INFINITY;
    else if (this.y == that.y)
      return +0.0;
    else if (this.x == that.x)
      return Double.POSITIVE_INFINITY;
    else 
      return (double)(that.y - this.y) / (that.x - this.x);
  }

  // is this point lexicographically smaller than that one?
  // comparing y-coordinates and breaking ties by x-coordinates
  public int compareTo(Point that) {
    if      (this.y < that.y)
      return -1;
    else if (this.y > that.y)
      return 1;
    else if (this.x < that.x)
      return -1;
    else if (this.x > that.x)
      return 1;
    else
      return 0;
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