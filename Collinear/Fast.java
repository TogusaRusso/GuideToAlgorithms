import java.util.Arrays;
  
public class Fast {
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
    
    // reset the pen radius
    StdDraw.setPenRadius();
    
    Arrays.sort(points);
    Point[] aux = new Point[N];
    for (int i = 0; i < N; i++)
      aux[i] = points[i];
    
    // fast search
    for (int p = 0; p < N; p++) {
      Arrays.sort(aux);
      Arrays.sort(aux, points[p].SLOPE_ORDER);
      double currentSlope = points[p].slopeTo(aux[0]);
      int counter = 1;
      for (int i = 1; i < N; i++)
        if(currentSlope == points[p].slopeTo(aux[i]))
          counter++;
        else {
          if (counter > 2) {
            String result = points[p].toString();
            boolean flag = true;
            for(int j = i - counter; j < i; j++)
              if (points[p].compareTo(aux[j]) < 0)
                result += " -> " + aux[j].toString();
              else 
                flag = false;
            
            if (flag) {
              StdOut.println(result);
              points[p].drawTo(aux[i - 1]);
            }
          }
          currentSlope = points[p].slopeTo(aux[i]);
          counter = 1;
        }
        if (counter > 2) {
          String result = points[p].toString();
          boolean flag = true;
          for(int j = N - counter; j < N; j++)
            if (points[p].compareTo(aux[j]) < 0)
              result += " -> " + aux[j].toString();
            else
              flag = false;
          if (flag) {
            StdOut.println(result);
            points[p].drawTo(aux[N - 1]);
          }
        }
    }
    
    //StdOut.println(points[p].toString() + " -> " + points[q].toString() + " -> " + points[r].toString() + " -> " + points[s].toString());
    //points[p].drawTo(points[s]);
        
    // display to screen all at once
    StdDraw.show(0);  
  }
    
}
