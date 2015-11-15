//look http://algs4.cs.princeton.edu/22mergesort/MergeX.java.html
//for maximum optimized mergesort

public class MergeTrace { 
  private static int counter = 0;
  private static <T extends Comparable<T>> void merge(T[] a, T[] aux, int lo, int mid, int hi) {
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

  private static <T extends Comparable<T>> boolean less(T v, T w) {
    return  v.compareTo(w) < 0;
  }
  
  private static <T extends Comparable<T>> boolean isSorted(T[] a, int start, int finish) {
      for (int i = start + 1; i <= finish; i++)
        if (less(a[i], a[i - 1])) return false;
      return true;
  }  
  
  private static <T extends Comparable<T>> void exch(T[] a, int i, int j) {
    T swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  }
  
  private static <T extends Comparable<T>> void insertion_sort(T[] a, int lo, int hi) {
    for (int i = lo; i <= hi; i++)
      for (int j = i; j > lo && less(a[j], a[j-1]); j--)
        exch(a, j, j-1);
  }
  
  private static <T extends Comparable<T>> void sort(T[] a, T[] aux, int lo, int hi) {
    if (hi <= lo) return;
    
    int mid = lo + (hi - lo) / 2;
    sort (a, aux, lo, mid);
    sort (a, aux, mid + 1, hi);
    //if (!less(a[mid+1], a[mid])) return; //alredy sorted, not need to merge
    merge(a, aux, lo, mid, hi);
    counter++;
    printArray(a);   
  }
  
  public static <T extends Comparable<T>> void sort(T[] a) {
    @SuppressWarnings("unchecked")
    T[] aux = (T[]) new Comparable[a.length];
    sort(a, aux, 0, a.length - 1);
  }
  
  private static <T extends Comparable<T>> void printArray(T[] a) {
    String out = "";
    for (int i = 0; i < a.length; i++)
      out += a[i] + " ";
    StdOut.println(counter + ": [" + out + "]");
  }
    
  public static void main(String[] args)    // test client (described below)
  {
    //String[] a = {"lifo", "list", "tree", "heap", "java", "fifo", "swim", "leaf", "flip", "load", "flow", "time"};
    String[] a = {"mint", "ceil", "lime", "palm", "bole", "rust", "jade", "flax", "onyx", "bone", "bark", "pine", "pink", "silk", "buff", "coal", "puce", "iris", "drab", "gray", "kobi", "aqua", "corn", "fawn"};
     MergeTrace.printArray(a);
     MergeTrace.sort(a);
     
  }

}

  