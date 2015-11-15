import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Queue;

public class SAP {
  
  private Digraph G;
  private SET<Integer> changed, vOld, wOld;
  private int ancestor, length;
  private int[] distToV;      // distToV[a] = length of shortest v->a path
  private int[] distToW;      // distToW[a] = length of shortest w->a path

   // constructor takes a digraph (not necessarily a DAG)
  public SAP(Digraph G) {
    if (G == null) 
        throw new NullPointerException("Graph is empty");
    this.G = new Digraph(G);
    distToV = new int[G.V()];
    distToW = new int[G.V()];
    for (int i = 0; i < G.V(); i++) {
      distToV[i] = -1;
      distToW[i] = -1;
    }
    changed = new SET<Integer>();
    vOld = new SET<Integer>();
    wOld = new SET<Integer>();
    ancestor = -1;
    length = -1;
  }

   // length of shortest ancestral path between v and w; -1 if no such path
  public int length(int v, int w) {
    bfs(v, w);
    return length;
  }
     

   // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
  public int ancestor(int v, int w) {
    bfs(v, w);
    return ancestor;
  }
     

   // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
  public int length(Iterable<Integer> v, Iterable<Integer> w) {
    bfs(v, w);
    return length;
  }

   // a common ancestor that participates in shortest ancestral path; -1 if no such path
  public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
    bfs(v, w);
    return ancestor;
  }
  
  private void bfs(int v, int w) {
    Queue<Integer> vq = new Queue<Integer>();
    Queue<Integer> wq = new Queue<Integer>();
    vq.enqueue(v);
    wq.enqueue(w);
    bfs(vq, wq);
  }
  
  private void validateVertex(int v) {
      if (v < 0 || v >= G.V())
          throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (G.V()-1));
  }
  
  private void bfs(Iterable<Integer> sv, Iterable<Integer> sw) {
    if (sv == null) 
        throw new NullPointerException("v is empty");
    if (sw == null) 
        throw new NullPointerException("w is empty");
    SET<Integer> vSet = new SET<Integer>();
    SET<Integer> wSet = new SET<Integer>();
    for (int v : sv) {
      validateVertex(v);
      vSet.add(v);
    }
    for (int w : sw) {
      validateVertex(w);
      wSet.add(w);
    }
    if (vSet.equals(vOld) && wSet.equals(wOld))
      return;
    if (wSet.equals(vOld) && vSet.equals(wOld))
      return;
    vOld = vSet;
    wOld = wSet;
    for (int c: changed) {
      distToV[c] = -1;
      distToW[c] = -1;
    }
    changed = new SET<Integer>();
    length = -1;
    ancestor = -1;
    Queue<Integer> q = new Queue<Integer>();
    for (int s : sv) {
      distToV[s] = 0;
      q.enqueue(s);
      changed.add(s);
    }
    for (int s : sw) {
      distToW[s] = 0;
      q.enqueue(s);
      changed.add(s);
    }
    while (!q.isEmpty()) {
      int v = q.dequeue();
      if (length > -1 && (distToV[v]  > length || distToW[v] > length))
          continue;
      if (distToV[v] > -1 && distToW[v] > -1) {
          if (distToV[v] + distToW[v] < length || length == -1) {
              ancestor = v;
              length = distToV[v] + distToW[v];
          }
      }      
      for (int w : G.adj(v)) {
        if (distToV[v] > -1 && distToV[w] == -1) {
                    distToV[w] = distToV[v] + 1;
                    changed.add(w);
                    q.enqueue(w);
        }
        if (distToW[v] > -1 && distToW[w] == -1) {
                    distToW[w] = distToW[v] + 1;
                    changed.add(w);
                    q.enqueue(w);
        }
      }
    }
  }
    


   // do unit testing of this class
     
   public static void main(String[] args) {
     In in = new In(args[0]);
     Digraph G = new Digraph(in);
     SAP sap = new SAP(G);
     while (!StdIn.isEmpty()) {
       int v = StdIn.readInt();
       int w = StdIn.readInt();
       int length   = sap.length(v, w);
       int ancestor = sap.ancestor(v, w);
       StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
    }
   }
}