import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {
  private int N = 0;
  private Item[] a;

  public RandomizedQueue() {                 // construct an empty randomized queue
    a = (Item[]) new Object[1];
  }
    
  public boolean isEmpty() {                 // is the queue empty?
    return N == 0;
  }
  
  public int size() {                        // return the number of items on the queue
    return N;
  }
  
  private void resize(int capicity) {
    Item[] copy = (Item[]) new Object[capicity];
    for (int i = 0; i < N; i++)
      copy[i] = a[i];
    a = copy;
  }
  
  public void enqueue(Item item) {           // add the item
    if (item == null) 
      throw new java.lang.NullPointerException();
    if (N == a.length)
      resize(2 * N);
    a[N++] = item;
  }
       
  public Item dequeue() {                    // remove and return a random item
    if (N == 0)
      throw new java.util.NoSuchElementException();
    int i = StdRandom.uniform(N);
    Item item = a[i];
    a[i] = a[--N];
    a[N] = null;
    if (N > 0 && N == a.length / 4)
      resize(N * 2);
    return item;
  }
  public Item sample() {                     // return (but do not remove) a random item
    if (N == 0)
      throw new java.util.NoSuchElementException();
    return a[StdRandom.uniform(N)];
  }
  public Iterator<Item> iterator() {         // return an independent iterator over items in random order
    return new ListIterator();
  }
  //iterator class
  private class ListIterator implements Iterator<Item> {
    private int current = 0;
    private Item[] copy;
    
    public ListIterator() {
      copy = (Item[]) new Object[N];
      for (int i = 0; i < N; i++)
        copy[i] = a[i];
      StdRandom.shuffle(copy);
    }
    
    public boolean hasNext() {
      return current < copy.length;
    }
    
    public void remove() {
      throw new UnsupportedOperationException("RandomizedEnque class do not supports remove method");
    }
    
    public Item next() {
      if (!hasNext()) {
        throw new NoSuchElementException("No more elements in RandomizedEnque");
      }
      return copy[current++];
    }
  }
  
  public static void main(String[] args) {   // unit testing
    RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
    queue.enqueue(1);
    queue.enqueue(2);
    queue.enqueue(3);
    queue.enqueue(4);
    Iterator<Integer> iter1 = queue.iterator();
    Iterator<Integer> iter2 = queue.iterator();
    StdOut.println(iter1.next());
    StdOut.println(iter2.next());
    StdOut.println(iter1.next());
    StdOut.println(iter2.next());
    StdOut.println(iter1.next());
    StdOut.println(iter2.next());
  }
}