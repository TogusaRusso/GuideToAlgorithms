import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item> {
  
  // first node in list
  private Node first = null;
  private int size_counter = 0;
  
  // inner class for linked list creation
  private class Node {
    Item item;
    Node next;
  }
  
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
      throw new UnsupportedOperationException("Stack class do not supports remove method");
    }
    
    public Item next() {
      if (!hasNext()) {
        throw new NoSuchElementException("No more elements in stack");
      }
      Item item = current.item;
      current = current.next;
      return item;
    }
  }
    
  // insert a new item onto stack
  public void push(Item item) {
    Node oldfirst = first;
    first = new Node();
    first.next = oldfirst;
    first.item = item;
    size_counter++;
  }
  
  // remove and return the item most recently added
  public Item pop() {
    Item item = first.item;
    first = first.next;
    size_counter--;
    return item;
  }
  
  // is the stack emplty?
  boolean isEmpty() {
    return first == null;
  }
  
  // number of items on the stack
  int size() {
    return size_counter;
  }
  
  public static void main(String[] args) {
    Stack<String> stack = new Stack<String>();
    while (!StdIn.isEmpty()) {
      String s = StdIn.readString();
      if (s.equals("-"))
        StdOut.print(stack.pop());
      else
        stack.push(s);
    }
  }
}