public class Quicksort {
  private static final int CUTOFF = 1;
  
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
  
  public static <T extends Comparable<T>> void sort(T[] a) {
    //StdRandom.shuffle(a);
    sort(a, 0, a.length - 1);
    insertionSort(a, 0, a.length - 1); //insertion sort almost sorted array in one pass
  }
  
  private static <T extends Comparable<T>> void sort(T[] a, int lo, int hi) {
    if (hi < lo + CUTOFF) return; //don't use qucksort for small arrays
    printArray(a);

    int m = medianOf3(a, lo, lo + (hi - lo) / 2, hi);
    exch(a, lo, m);
    
    int j = partition(a, lo, hi);
    sort(a, lo, j - 1);
    sort(a, j + 1, hi);
  }
 
  private static <T extends Comparable<T>> void insertionSort(T[] a, int lo, int hi) {
    for (int i = lo; i <= hi; i++)
      for (int j = i; j > lo && less(a[j], a[j-1]); j--)
        exch(a, j, j-1);
  }
  
  private static <T extends Comparable<T>> int medianOf3(T[] a, int i, int j, int k) {
    if (less(a[i], a[j])) {
      if (less(a[j], a[k]))
        return j; 
      else if (less(a[i], a[k])) 
        return k;
      else 
        return i;
    }
    else if (less(a[i], a[k]))
      return i;
    else if (less(a[j], a[k]))
      return j;
    return k;
  }
      
  
  private static <T extends Comparable<T>> void exch(T[] a, int i, int j) {
    T swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  }

  private static <T extends Comparable<T>> boolean less(T v, T  w) {
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
    String[] a = {"mint", "ceil", "lime", "palm", "bole", "rust", "jade", "flax", "onyx", "bone", "bark", "pine", "pink", "silk", "buff", "coal", "puce", "iris", "drab", "gray", "kobi", "aqua", "corn", "fawn"};
    printArray(a);
    sort(a);
    printArray(a);
     
  }

}
    
    
  