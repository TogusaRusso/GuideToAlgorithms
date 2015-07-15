import java.util.Arrays;
  
public class Brute {
  public static void main(String[] args) {
    In input = new In(args[0]);
    
    int N = input.readInt();
    Point[] points = new Point[N];

    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    StdDraw.show(0);
    StdDraw.setPenRadius(0.01);
    

    for (int i = 0; i < N; i++) {     
      points[i] = new Point(input.readInt(), input.readInt()); 
      //StdOut.println(points[i].toString());
      points[i].draw();
    }
    
    Arrays.sort(points);
    
    // reset the pen radius
    StdDraw.setPenRadius();
 
    // brute force, four loops
    for (int p = 0; p < N - 3; p++) 
      for (int q = p + 1; q < N - 2; q++) 
        for (int r = q + 1; r < N - 1; r++) {
          double slopePQ = points[p].slopeTo(points[q]);
          double slopeQR = points[q].slopeTo(points[r]);
          if (slopePQ == slopeQR) 
            for (int s = r + 1; s < N; s++) {
              double slopeRS = points[r].slopeTo(points[s]);
              if (slopeQR == slopeRS) {
                StdOut.println(points[p].toString() + " -> " + points[q].toString() + " -> " + points[r].toString() + " -> " + points[s].toString());
                //StdOut.println(slopePQ + ", " + slopeQR + ", " + slopeRS);
                points[p].drawTo(points[s]);
              }
            }
        }
        
    // display to screen all at once
    StdDraw.show(0);  
  }
    
}
