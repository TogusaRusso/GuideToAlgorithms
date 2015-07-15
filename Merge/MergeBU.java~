public class MergeBU {
  
  private static Comparable[] aux;
  
  private static void merge(Comparable[] a, int lo, int mid, int hi) {
    //assert isSorted(a, lo, mid);
    //assert isSorted(a, mid + 1, hi);
  
    for (int k = lo; k <= hi; k++) 
      aux[k] = a[k];
  
    int i = lo, j = mid + 1;
    for (int k = lo; k <= hi; k++) {
      if      (i > mid)              a[k] = aux[j++];
      else if (j > hi)               a[k] = aux[i++];
      else if (less(aux[j], aux[i])) a[k] = aux[j++];
      else                           a[k] = aux[i++];
    }
    //assert isSorted (a, lo, hi);
  }
  
  private static boolean less(Comparable v, Comparable  w) {
    return  v.compareTo(w) < 0;
  }
  
  private static boolean isSorted(Comparable[] a, int start, int finish) {
      for (int i = start + 1; i <= finish; i++)
        if (less(a[i], a[i - 1])) return false;
      return true;
  }  

  public static void sort(Comparable[] a) {
    int N = a.length;
    aux = new Comparable[N];
    for (int sz = 1; sz < N; sz = sz + sz)
      for (int lo = 0; lo < N - sz; lo += sz + sz)
        merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
  }
}
    
    
  