public class Percolation {
  private byte[] grid;
  private int size;
  private boolean percolated;
  private WeightedQuickUnionUF union;
  private int topParent;
  
   public Percolation(int N)               // create N-by-N grid, with all sites blocked
   {
     if (N <= 0)
       throw new java.lang.IllegalArgumentException();
     size = N;
     grid = new byte[size * size + 1];
     union = new WeightedQuickUnionUF(size * size + 1);
     percolated = false;
     topParent = 0;
     //nobackwash = new WeightedQuickUnionUF(size * size + 2);
     
   }
   private int toUnion(int i, int j)
   {
     if (i == 0)
       return 0;
     else
       return (i - 1) * size + j;
   }
   private void setOpen(int i, int j)
   {
     int pointer = toUnion(i, j);
     grid[pointer] = (byte) (grid[pointer] | 1);
   }
   private void setBottomParent(int i)
   {
     grid[i] = (byte) (grid[i] | 2);
   }
   private boolean isBottomParent(int i)
   {
     if (i == -1)
       return false;
     else
       return (grid[i] &  2) == 2;
   }
   private void check(int i, int j)
   {
     if (i < 1 || i > size || j < 1 || j > size)
       throw new java.lang.IndexOutOfBoundsException();
   }
   public void open(int i, int j)          // open site (row i, column j) if it is not open already
   {
     check(i, j);
     if (!isOpen(i, j))
     {
       setOpen(i, j);
       boolean top = false;
       boolean bottom = false;
       int middle = toUnion(i, j);
       
       // check up
       int up = -1;
       if (i > 1)
       {
         if (isOpen(i - 1, j))
           up = union.find(toUnion(i - 1, j));
       }
       else 
       {
         up = topParent;
         top = true;
       }
       // check down
       int down = -1;
       if (i < size)
       {
         if (isOpen(i + 1, j))
           down = union.find(toUnion(i + 1, j));
       }
       else 
         bottom = true;
       // check left
       int left = -1;
       if (j > 1)
         if (isOpen(i, j - 1))
           left = union.find(toUnion(i, j - 1));
       // check right
       int right = -1;
       if (j < size)
         if (isOpen(i, j + 1))
           right = union.find(toUnion(i, j + 1));
       if (up == topParent || down == topParent || left == topParent || right == topParent)
         top = true;
       if (isBottomParent(up) || isBottomParent(down) || isBottomParent(left) || isBottomParent(right))
         bottom = true;
       if (top && bottom)
         percolated = true;
       if (up > -1)
         union.union(middle, up);
       if (down > -1)
         union.union(middle, down);
       if (left > -1)
         union.union(middle, left);
       if (right > -1)
         union.union(middle, right);
       middle = union.find(middle);
       if (top)
         topParent = middle;
       if (bottom)
         setBottomParent(middle);
     }
   }
   public boolean isOpen(int i, int j)     // is site (row i, column j) open?
   {
     check(i, j);
     return (grid[toUnion(i, j)] &  1) == 1;
   }
   public boolean isFull(int i, int j)     // is site (row i, column j) full?
   {
     check(i, j);
     return union.find(toUnion(i, j)) == topParent;
   }

   public boolean percolates()             // does the system percolate?
   {
     return percolated;
   }

   public static void main(String[] args)   // test client (optional)
   {
        int N = StdIn.readInt();
        Percolation gr = new Percolation(N);
        while (!StdIn.isEmpty()) {
            int i = StdIn.readInt();
            int j = StdIn.readInt();
            if (gr.isOpen(i, j)) continue;
            gr.open(i, j);
            StdOut.println(i + " " + j);
        }
        if (gr.percolates())
          StdOut.println("It's percolates");
        else
          StdOut.println("It isn't percolates");
   }
}
