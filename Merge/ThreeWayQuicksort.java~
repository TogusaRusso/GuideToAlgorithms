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
  
  private static <T extends Comparable<T>> void exch(T[] a, int i, int j) {
    T swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  }
}
    
    
  