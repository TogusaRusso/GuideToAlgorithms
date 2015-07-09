public class Shell {
    public static <Key extends Comparable<Key>> void sort(Key[] a) {
      int N = a.length;
      int h = 1;
      while (h < N / 3) h = 3 * h + 1;
      while (h >= 1) {
        for (int i = h; i < N; i++)
          for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) exch(a, j, j - h);
        h = h / 3;
      }
    }

    private static <Key extends Comparable<Key>> void exch(Key[] a, int i, int j) {
      Key swap = a[i];
      a[i] = a[j];
      a[j] = swap;
    }
    private static <Key extends Comparable<Key>> boolean less(Key v, Key w) {
      return  v.compareTo(w) < 0;
    }
}