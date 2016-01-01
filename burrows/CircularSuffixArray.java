import edu.princeton.cs.algs4.StdRandom;
//import edu.princeton.cs.algs4.StdOut;
public class CircularSuffixArray {
    private static final int CUTOFF =  15;   // cutoff to insertion sort
    private int[] index;
    private int N;

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null)
            throw new java.lang.NullPointerException("Empty string");
        N = s.length();
        index = new int[N];
        for (int i = 0; i < N; i++)
            index[i] = i;
        StdRandom.shuffle(index);
        sort(s, 0, N - 1, 0);
    }

    private void sort(String s, int lo, int hi, int d) {
        // cutoff to insertion sort for small subarrays
        if (hi <= lo + CUTOFF) {
            insertion(s, lo, hi, d);
            return;
        }

        int lt = lo, gt = hi;
        int v = charAt(s, lo, d);
        int i = lo + 1;
        while (i <= gt) {
            int t = charAt(s, i, d);
            if      (t < v) exch(lt++, i++);
            else if (t > v) exch(i, gt--);
            else              i++;
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi]. 
        sort(s, lo, lt-1, d);
        if (v >= 0) sort(s, lt, gt, d + 1);
        sort(s, gt + 1, hi, d);
    }

    // exchange index[i] and index[j]
    private void exch(int i, int j) {
        int temp = index[i];
        index[i] = index[j];
        index[j] = temp;
    }

    // return the dth character of s with index[i], -1 if d = length of s
    private int charAt(String s, int i, int d) {
        assert d >= 0 && d <= N;
        if (d == N) return -1;
        int off = (index[i] + d) % N;
        return s.charAt(off);
    }  
    
    // sort from a[lo] to a[hi], starting at the dth character
    private void insertion(String s, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(s, j, j-1, d); j--)
                exch(j, j-1);
    }
    
    // is v less than w, starting at character d
    private boolean less(String s, int v, int w, int d) {
        for (int i = d; i < N; i++) {
            if (charAt(s, v, i) < charAt(s, w, i)) return true;
            if (charAt(s, v, i) > charAt(s, w, i)) return false;
        }
        return false;
    }
   
    // length of s
    public int length() {
        return N;
    }
    // returns index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i >= N)
            throw new java.lang.IndexOutOfBoundsException("i = " + i);
        return index[i];
    }
    // unit testing of the methods (optional)
    public static void main(String[] args) {
        //CircularSuffixArray csa = new CircularSuffixArray(args[0]);
        //StdOut.println("Length of string: " + csa.length());
        //for (int i = 0; i < csa.length(); i++)
        //    StdOut.print(csa.index(i) + " ");
        //StdOut.println();
    }
}