//import Queue.java;
public class Board {
  private final int[][] board;
  private int N;
  private int hammingCache;
  private int manhattanCache;
  public Board(int[][] blocks) {           // construct a board from an N-by-N array of blocks
    N = blocks.length;                     // (where blocks[i][j] = block in row i, column j)
    hammingCache = -1;
    manhattanCache = -1;
    board = new int[N][N];
    for (int i = 0; i < N; i++)
      for (int j = 0; j < N; j++)
        board[i][j] = blocks[i][j];
  }

  public int dimension() {                 // board dimension N
    return N;
  }
  
  public int hamming() {                   // number of blocks out of place
    if (hammingCache > 0)
      return hammingCache;
    int count = 0;
    for (int i = 0; i < N; i++)
      for (int j = 0; j < N; j++)
        if (board[i][j] > 0 && board[i][j] != (i * N + j + 1))
          count++;
    hammingCache = count;
    return count;
  }
  
  public int manhattan() {                 // sum of Manhattan distances between blocks and goal
    if (manhattanCache > 0)
      return manhattanCache;
    int count = 0;
    for (int i = 0; i < N; i++)
      for (int j = 0; j < N; j++)
        if (board[i][j] > 0 && board[i][j] != (i * N + j + 1)) {
          int c = (board[i][j] - 1)       % N;
          int r = ((board[i][j] - c) - 1) / N;
          count += Math.abs(c - j) + Math.abs(r - i);
        }
    manhattanCache = count;
    return count;
  }
    
  public boolean isGoal() {                // is this board the goal board?
    return hamming() == 0;
  }
  public Board twin() {                  // a board that is obtained by exchanging two adjacent blocks in the same row
    int[][] otherBoard = copyBoard();
    boolean swapped = false;
    for (int i = 0; (i < N) && !swapped; i++)
      for (int j = 0; (j < N - 1) && !swapped; j++)
        if (otherBoard[i][j] > 0 && otherBoard[i][j + 1] > 0) {
          swap(otherBoard, i, j, i, j + 1);
          swapped = true;
        }
    return new Board(otherBoard);
  }
  private void swap(int[][] a, int r1, int c1, int r2, int c2) {
    int swap  = a[r1][c1];
    a[r1][c1] = a[r2][c2];
    a[r2][c2] = swap;
  }
  private int[][] copyBoard() {
    int[][] otherBoard = new int[N][N];
    for (int i = 0; i < N; i++)
      for (int j = 0; j < N; j++)
        otherBoard[i][j] = board[i][j];
    return otherBoard;
  }
      
    
    

  public boolean equals(Object y) {       // does this board equal y?
    if (y == this) return true;
    
    if (y == null) return false;
    
    if (y.getClass() != this.getClass())
      return false;

    Board that = (Board) y;
    if (this.N != that.N) return false;
    if (this.board.length != that.board.length) return false;
    for (int i = 0; i < N; i++)
      for (int j = 0; j < N; j++)
        if (this.board[i][j] != that.board[i][j]) return false;
    return true;
  }
  
  public Iterable<Board> neighbors() {     // all neighboring boards
    Queue<Board> stack = new Queue<Board>();
    int r = -1;
    int c = -1;
    for (int i = 0; i < N && r == -1; i++)
      for (int j = 0; j < N && c == -1; j++)
        if (board[i][j] == 0) {
          r = i;
          c = j;
        }
    int[][] otherBoard = copyBoard();
    if (r > 0) {
      swap(otherBoard, r, c, r - 1, c);
      stack.enqueue(new Board(otherBoard));
      swap(otherBoard, r, c, r - 1, c);
    }
    if (r < (N - 1)) {
      swap(otherBoard, r, c, r + 1, c);
      stack.enqueue(new Board(otherBoard));
      swap(otherBoard, r, c, r + 1, c);
    }
    if (c > 0) {
      swap(otherBoard, r, c, r, c - 1);
      stack.enqueue(new Board(otherBoard));
      swap(otherBoard, r, c, r, c - 1);
    }
    if (c < (N - 1)) {
      swap(otherBoard, r, c, r, c + 1);
      stack.enqueue(new Board(otherBoard));
      swap(otherBoard, r, c, r, c + 1);
    }
    return stack;
  }
    
  public String toString() {               // string representation of this board
    StringBuilder s = new StringBuilder();
    s.append(N + "\n");
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            s.append(String.format("%2d ", board[i][j]));
        }
        s.append("\n");
    }
    return s.toString();
  }
    
  public static void main(String[] args) { // unit tests (not graded)
  }
}