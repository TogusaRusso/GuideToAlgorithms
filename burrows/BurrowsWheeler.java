import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {
    // apply Burrows-Wheeler encoding, reading from standard input and writing 
    // to standard output
    public static void encode() {
        String s = BinaryStdIn.readString();
        CircularSuffixArray csa = new CircularSuffixArray(s);
        int N = csa.length();
        int first = -1;
        for (int i = 0; i < N; i++) {
            if (csa.index(i) == 0) {
                first = i;
                break;
            }
        }
        BinaryStdOut.write(first);
        for (int i = 0; i < N; i++) {
            BinaryStdOut.write(s.charAt((N + csa.index(i) -1) % N));
        }
        BinaryStdOut.close();
    }

    // apply Burrows-Wheeler decoding, reading from standard input and writing 
    // to standard output
    public static void decode() {
        int first = BinaryStdIn.readInt();
        String t = BinaryStdIn.readString();
        int N = t.length();
        int R = 256;
        int[] next = new int[N];
        char[] c = new char[N];
        // sort by key-indexed counting
        // compute frequency counts
        int[] count = new int[R + 1];
        count[0] = 0;
        for (int i = 0; i < N; i++)
            count[t.charAt(i) + 1]++;
        // compute cumulates
        for (int r = 0; r < R; r++)
            count[r + 1] += count[r];
        // move data
        for (int i = 0; i < N; i++) {
            next[count[t.charAt(i)]] = i;       
            c[count[t.charAt(i)]++] = t.charAt(i);
        }
        for (int i = 0; i < N; i++) {
            BinaryStdOut.write(c[first]);
            first = next[first];
        }
        BinaryStdOut.close();
    }

    // if args[0] is '-', apply Burrows-Wheeler encoding
    // if args[0] is '+', apply Burrows-Wheeler decoding
    public static void main(String[] args) {
        if      (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
        else throw new 
            IllegalArgumentException("Illegal command line argument");
    }
}