public class ThreeWayQuicksort {
  
  public static <T extends Comparable<T>> void sort(T[] a) {
    StdRandom.shuffle(a);
    sort(a, 0, a.length - 1);
  }
  
  private static <T extends Comparable<T>> void sort(T[] a, int lo, int hi) {
    if (hi <= lo) return; //don't use qucksort for small arrays

    int lt = lo, gt = hi, i = lo;
    T v = a[lo];
    while(i <= gt) {
      int cmp = a[i].compareTo(v);
      if      (cmp < 0) exch(a, lt++, i++);
      else if (cmp > 0) exch(a, i, gt--);
      else              i++;
    }
    sort(a, lo, lt - 1);
    sort(a, gt + 1, hi);
  }
  
  private static <T extends Comparable<T>> void partition(T[] a, int lo, int hi) {
    if (hi <= lo) return; //don't use qucksort for small arrays

    int lt = lo, gt = hi, i = lo;
    T v = a[lo];
    while(i <= gt) {
      int cmp = a[i].compareTo(v);
      if      (cmp < 0) exch(a, lt++, i++);
      else if (cmp > 0) exch(a, i, gt--);
      else              i++;
    }
  }

  private static <T extends Comparable<T>> void exch(T[] a, int i, int j) {
    T swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  }

  private static <T extends Comparable<T>> void printArray(T[] a) {
    String out = "";
    for (int i = 0; i < a.length; i++)
      out += a[i] + " ";
    StdOut.println("[" + out + "]");
  }

  public static void main(String[] args)    // test client (described below)
  {
    
    Integer[] a = {47, 83, 88, 43, 47, 47, 80, 67, 92, 71};
    printArray(a);
    partition(a, 0, a.length - 1);
    printArray(a);
     
  }

}
    
    
  