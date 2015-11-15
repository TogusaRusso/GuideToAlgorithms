import java.util.Comparator;

class InsertionComparator {
  public static <T> void sort(T[] a, Comparator<T> c) {
    int N = a.length;
    for (int i = 0; i < N; i++)
      for (int j = i; j > 0 && less(c, a[j], a[j - 1]); j--)
        exch(a, j, j-1);
    assert isSorted(a, c);
  }
  private static void exch(Object[] a, int i, int j) {
    Object swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  }
  private static <T> boolean less(Comparator<T> c, T v, T w) {
    return  c.compare(v, w) < 0;
  }
  
  private static <T> boolean isSorted(T[] a, Comparator<T> c) {
    for (int i = 1; i < a.length; i++)
      if (less(c, a[i], a[i - 1])) return false;
    return true;
  }
      

}
      