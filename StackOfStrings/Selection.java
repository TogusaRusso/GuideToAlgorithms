public class Selection {
  public static void sort(Comparable[] a) {
    int N = a.length;
    for (int i = 0; i < N; i++) {
      int min = i;
      for (int j = i + 1; j < N; j++) 
        if (less(a[j], a[min])) min = j;
      exch(a, i, min);
      printArray(a);
    }
  }
    private static void exch(Comparable[] a, int i, int j) {
    Comparable swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  }
  private static boolean less(Comparable v, Comparable w) {
    return  v.compareTo(w) < 0;
  }
  private static <T extends Comparable<T>> void printArray(T[] a) {
    String out = "";
    for (int i = 0; i < a.length; i++)
      out += a[i] + " ";
    StdOut.println("[" + out + "]");
  }

  public static void main(String[] args)    // test client (described below)
  {
    //String[] a = {"lifo", "list", "tree", "heap", "java", "fifo", "swim", "leaf", "flip", "load", "flow", "time"};
    String[] a = {"mint", "ceil", "lime", "palm", "bole", "rust", "jade", "flax", "onyx", "bone", "bark", "pine", "pink", "silk", "buff", "coal", "puce", "iris", "drab", "gray", "kobi", "aqua", "corn", "fawn"};
    Selection.printArray(a);
    Selection.sort(a);
     
  }
}