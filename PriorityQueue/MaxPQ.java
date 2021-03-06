public class MaxPQ <Key extends Comparable<Key>> {
  private Key[] pq;
  private int N;
  
  @SuppressWarnings("unchecked")
  public MaxPQ(int capacity)  {
    pq = (Key[]) new Comparable[capacity + 1];
  }
  
  public boolean isEmpty() {
    return N == 0;
  }
  
  public void insert(Key x) {
    pq[++N] = x;
    swim(N);
  }
  
  public Key delMax() {
    Key max = pq[1];
    exch(1, N--);
    sink(1);
    pq[N + 1] = null;
    return max;
  }
  
  private void swim(int k) {
    while (k > 1 && less(k / 2, k)) {
      exch(k, k / 2);
      k = k / 2;
    }
  }
  
  private void sink(int k) {
    while (2 * k <= N) {
      int j = 2 * k;
      if (j < N && less(j, j + 1)) j++;
      if (!less(k, j)) break;
      exch(k, j);
      k = j;
    }
  }

  private void exch(int i, int j) {
    Key swap = pq[i];
    pq[i] = pq[j];
    pq[j] = swap;
  }

  private boolean less(int i, int  j) {
    return  pq[i].compareTo(pq[j]) < 0;
  }
  
  private void print() {
    String out = "";
    for (int i = 1; i <= N; i++)
      out += pq[i] + " ";
    StdOut.println("[" + out + "]");
  }

  public static void main(String[] args)    // test client (described below)
  {
    Integer[] a = {93, 60, 67, 41, 29, 34, 47, 25, 35, 20};
    MaxPQ<Integer> pq = new MaxPQ<Integer>(20);
    for (int k : a) {
      pq.insert(k);
    }
    pq.print();
    pq.insert(94);
    pq.insert(97);
    pq.insert(74);
    pq.print();
    
    Integer[] b= {95, 93, 74, 70, 49, 55, 64, 17, 32, 41};
    MaxPQ<Integer> pq2 = new MaxPQ<Integer>(20);
    for (int k : b) {
      pq2.insert(k);
    }
    pq2.print();
    pq2.delMax();
    pq2.delMax();
    pq2.delMax();
    pq2.print();
  }

}
  