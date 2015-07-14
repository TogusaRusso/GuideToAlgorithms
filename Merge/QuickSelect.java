public class QuickSelect {
  
  private static <T extends Comparable<T>> int partition(T[] a, int lo, int hi) {
    int i = lo, j = hi + 1;
    while (true) {
      while (less(a[++i], a[lo])) // find item on left to swap
        if (i == hi) break;
      while (less(a[lo], a[--j])) // find item on right to swap
        if (j == lo) break;

      if (i >= j) break;          // check if pointers cross
      exch(a, i, j);              // swap
    }
    exch(a, lo, j);               //swap with partitioning item
    return j;                     //return index of item< now known to be in place
  }

  private static <T extends Comparable<T>> void exch(T[] a, int i, int j) {
    T swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  }

  private static <T extends Comparable<T>> boolean less(T v, T  w) {
    return  v.compareTo(w) < 0;
  }

  public static <T extends Comparable<T>> T select(T[] a, int k) {
    StdRandom.shuffle(a);
    int lo = 0, hi = a.length - 1;
    while (hi > lo) {
      int j = partition(a, lo, hi);
      if      (j < k) lo = j + 1;
      else if (j > k) hi = j - 1;
      else return a[k];
    }
    return a[k];
  }
}
    