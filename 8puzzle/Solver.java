public class Solver {
  
  private class SearchNode implements Comparable<SearchNode> {
    private Board      board;
    private int        moves;
    private SearchNode prev;
    private boolean    isMain;
    SearchNode(Board b, int m, SearchNode p, boolean ismn) {
      board = b;
      moves = m;
      prev = p;
      isMain = ismn;
    }
    
    public Board board() {
      return board;
    }
    public int moves() {
      return moves;
    }
    public SearchNode prev() {
      return prev;
    }
    public boolean isMain() {
      return isMain;
    }


    
    public int compareTo(SearchNode that) { 
      int priorityP = this.board.manhattan() + this.moves;
      int priorityQ = that.board.manhattan() + that.moves;
      if      (priorityP < priorityQ)
        return -1;
      else if (priorityP > priorityQ)
        return 1;
      else {
        priorityP = this.board.hamming() + this.moves;
        priorityQ = that.board.hamming() + that.moves;
        if      (priorityP < priorityQ)
          return -1;
        else if (priorityP > priorityQ)
          return 1;
        else
          return 0;
      }
    }
  }
  private SearchNode current;
  
  public Solver(Board initial) {           // find a solution to the initial board (using the A* algorithm)
    if (initial == null)
      throw new java.lang.NullPointerException();

    MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
    
    pq.insert(new SearchNode(initial,        0, null, true));
    pq.insert(new SearchNode(initial.twin(), 0, null, false));
    while (true) {
      current = pq.delMin();
      if (current.board().isGoal())
        break;
      Iterable<Board> neighbors = current.board().neighbors();
      for (Board neighbor : neighbors) {
        if (current.prev() != null)
          if (neighbor.equals(current.prev().board()))
            continue;
        pq.insert(new SearchNode(neighbor, current.moves() + 1, current, current.isMain()));
      }
    }
  }
  
  public boolean isSolvable() {            // is the initial board solvable?
    return current.isMain();
  }
  
  public int moves() {                     // min number of moves to solve initial board; -1 if unsolvable
    if (isSolvable()) 
      return current.moves();
    else
      return -1;
  }
  
  public Iterable<Board> solution() {      // sequence of boards in a shortest solution; null if unsolvable
    if (!isSolvable()) 
      return null;
    Stack<Board> sol = new Stack<Board>();
    SearchNode position = current;
    while (position != null) {
      sol.push(position.board());
      position = position.prev();
    }
    return sol;
  }
  
  // solve a slider puzzle (given below)
  public static void main(String[] args) {
    // create initial board from file
    In in = new In(args[0]);
    int N = in.readInt();
    int[][] blocks = new int[N][N];
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            blocks[i][j] = in.readInt();
    Board initial = new Board(blocks);

    // solve the puzzle
    Solver solver = new Solver(initial);

    // print solution to standard output
    if (!solver.isSolvable())
        StdOut.println("No solution possible");
    else {
        StdOut.println("Minimum number of moves = " + solver.moves());
        for (Board board : solver.solution())
            StdOut.println(board);
    }
  }
}