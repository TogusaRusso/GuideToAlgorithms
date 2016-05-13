import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stack;
import java.util.TreeSet;
import java.util.BitSet;
//import edu.princeton.cs.algs4.SET;

public class BoggleSolver {
    private int N, M; // size
    private Dictionary dict;
    private int[] toR, toC;
    private int[][] dirs;
    
    private class State {
        private Node node;
        private BitSet marked;
        private int pos;
        public State(Node n, BitSet m, int p) {
            node = n;
            pos = p;
            marked = (BitSet) m.clone();
        }
    }

    private class Node {
        private Object val;
        private Node prev;
        private char c;
        private Node[] next;
    }
    
    private class Dictionary {
        private static final int R = 26; // ABC
        
        private Node root;
        
         public Dictionary() {
             root = null;
         }
         
         public int get(String key) {
             if (key == null)
                 return 0;
             Node x = get(root, key, 0);
             if (x == null) return 0;
             if (x.val == null) return 0;
             return (Integer) x.val;
         }    
         public Node getNode(String key) {
             return getNode(root, key);
         }
         public Node getNode(Node source, String key) {
             if (key.equals("Q"))
                 return get(source, "QU", 0);
             return get(source, key, 0);
         }
         public String word(Node node) {
             String w = "";
             while (node.prev != null) {
                 w = node.c + w;
                 node = node.prev;
             }
             return w;
         }
         private Node get(Node x, String key, int d) {
             if (x == null) return null;
             if (d == key.length()) return x;
             int c = key.charAt(d) - (int) 'A';
             return get(x.next[c], key, d+1);
         }
         public void put(String key, int val) {
             root = put(root, null, key, val, 0);
         }
         
         private Node put(Node x, Node p, String key, int val, int d) {
             if (x == null) {
                 x = new Node();
                 x.next = new Node[R];
                 x.prev = p;
                 if (d > 0)
                     x.c = key.charAt(d - 1);
             }
             if (d == key.length()) {
                 x.val = val;
                 return x;
             }
             int c = key.charAt(d) - (int) 'A';
             //if (x.next == null) x.next = new Node[R];  
             x.next[c] = put(x.next[c], x, key, val, d+1);
             return x;
         }         
    }
    
    
    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
        dict = new Dictionary();
        for (String word : dictionary) {
            int len = word.length();
            int value = 0;
            if (len > 7) {
                value = 11;
            } else if (len > 6) {
                value = 5;
            } else if (len > 5) {
                value = 3;
            } else if (len > 4) {
                value = 2;
            } else if (len > 2) {
                value = 1;
            }
            if (value > 0)
                dict.put(word, value);            
        }
    }
    private int toP(int row, int col) {
        return row * N + col;
    }
    //precompute directions
    private void precompute(BoggleBoard board) {
        if (N == board.cols() && M == board.rows())
            return;
        N = board.cols();
        M = board.rows();
        toR = new int[N * M];
        toC = new int[N * M];
        dirs =  new int[N * M][9];
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                int count = 0;
                toR[toP(r, c)] = r;
                toC[toP(r, c)] = c;
                for (int dr = -1; dr < 2; dr++) {
                    for (int dc = -1; dc < 2; dc++) {
                        if (r + dr >= 0 && r + dr < M 
                                && c + dc >= 0 && c + dc < N 
                                && !(dc == 0 && dr == 0)) {
                            dirs[toP(r, c)][count++] = toP(r + dr, c + dc);
                        }
                    }
                }
                dirs[toP(r, c)][count] = -1;
            }
        }
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        TreeSet<String> validWords = new TreeSet<String>();
        Stack<State> words = new Stack<State>();
        precompute(board);
        BitSet marked = new BitSet(N * M);
        for (int pos = 0; pos < N * M; pos++) {
            marked.set(pos, true);
            Node node = dict.getNode("" + board.getLetter(toR[pos], toC[pos]));
            if (node != null)
                words.push(new State(node, marked, pos));
            marked.set(pos, false);
        }
        while (!words.isEmpty()) {
            State state = words.pop();
            if (state.node == null)
                continue;
            if (state.node.val != null)                
                validWords.add(dict.word(state.node));
            for (int pos : dirs[state.pos]) {
                if (pos == -1)
                    break;
                if (!state.marked.get(pos)) {
                    state.marked.set(pos, true);
                    Node node = 
                        dict.getNode(state.node, 
                                     "" + board.getLetter(toR[pos], toC[pos]));
                    words.push(new State(node, state.marked, pos));
                    state.marked.set(pos, false);
                }
            }
        }
        return (Iterable<String>) validWords;
    }

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        //int score = dict.get(word);
        //if (score == -1) return 0;
        return dict.get(word);
    }
    
    public static void main(String[] args)
    {
        In in = new In(args[0]);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(args[1]);
        int score = 0;
        for (String word : solver.getAllValidWords(board))
        {
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);
    }
}