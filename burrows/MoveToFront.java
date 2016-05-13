import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
    private static final int R = 256;

    // apply move-to-front encoding, reading from standard input and writing 
    // to standard output
    public static void encode() {
        char[] m = new char[R];
        for (int i = 0; i < R; i++)
            m[i] = (char) i;
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();
            int p;
            for (p = 0; p < R && m[p] != c; p++) { }
            BinaryStdOut.write(p, 8);
            for (int j = p; j > 0; j--) 
                    m[j] = m[j - 1];
            if (p > 0) m[0] = c;
        }
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and writing 
    // to standard output
    public static void decode() {
        char[] m = new char[R];
        for (int i = 0; i < R; i++)
            m[i] = (char) i;
        while (!BinaryStdIn.isEmpty()) {
            int p = BinaryStdIn.readChar();
            char c = m[p];
            BinaryStdOut.write(c);
            for (int j = p; j > 0; j--) 
                    m[j] = m[j - 1];
            if (p > 0) m[0] = c;
        }
        BinaryStdOut.close();
    }

    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
    public static void main(String[] args) {
        if      (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
        else throw new 
            IllegalArgumentException("Illegal command line argument");
    }
}