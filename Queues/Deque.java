import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
  
  // first and lasr node in list
  private Node first = null;
  private Node last = null;
  private int N = 0;
  
  // inner class for linked list creation
  private class Node {
    Item item;
    Node next;
    Node prev;
  }
    
  // is the deque empty?
  public boolean isEmpty() {
    return N == 0;
  }
  
  // return the number of items on the deque
  public int size() {
    return N;
  }
  
  // add the item to the front
  public void addFirst(Item item) {
    if (item == null) 
      throw new java.lang.NullPointerException();
    Node oldfirst = first;
    first = new Node();
    first.next = oldfirst;
    first.prev = null;
    first.item = item;
    if (N > 0)
      oldfirst.prev = first;
    else
      last = first;
    N++;
  }
  
  // add the item to the end
  public void addLast(Item item) {
    if (item == null) 
      throw new java.lang.NullPointerException();
    Node oldlast = last;
    last = new Node();
    last.prev = oldlast;
    last.next = null;
    last.item = item;
    if (N > 0)
      oldlast.next = last;
    else
      first = last;
    N++;
  }
  
  // remove and return the item from the front
  public Item removeFirst() {
    if (N == 0)
      throw new java.util.NoSuchElementException();
    Item item = first.item;
    if (--N == 0) {
      first = null;
      last = null;
    }
    else {
      first = first.next;
      first.prev = null;
    }
    return item;
  }

  // remove and return the item from the end
  public Item removeLast() {
    if (N == 0)
      throw new java.util.NoSuchElementException();
    Item item = last.item;
    if (--N == 0) {
      first = null;
      last = null;
    }
    else {
      last = last.prev;
      last.next = null;
    }
    return item;
  }
 
  // return an iterator over items in order from front to end
  public Iterator<Item> iterator() {
    return new ListIterator();
  }
  //iterator class
  private class ListIterator implements Iterator<Item> {
    private Node current = first;
    
    public boolean hasNext() {
      return current != null;
    }
    
    public void remove() {
      throw new UnsupportedOperationException("Deque class do not supports remove method");
    }
    
    public Item next() {
      if (!hasNext()) {
        throw new NoSuchElementException("No more elements in Deque");
      }
      Item item = current.item;
      current = current.next;
      return item;
    }
  }
  
  // unit testing
  public static void main(String[] args) {
    Deque<Integer> deque = new Deque<Integer>();
    deque.addLast(0);
    deque.addFirst(1);
    StdOut.println(deque.removeLast());
    StdOut.println(deque.removeLast());
    
  }
}
