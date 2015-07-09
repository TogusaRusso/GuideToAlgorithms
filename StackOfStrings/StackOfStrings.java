public class StackOfStrings {
  
  // first node in list
  private Node first = null;
  
  // inner class for linked list creation
  private class Node {
    String item;
    Node next;
  }
  
  // insert a new string onto stack
  void push(String item) {
    Node oldfirst = first;
    first = new Node();
    first.next = oldfirst;
    first.item = item;
  }
  
  // remove and return the string most recently added
  String pop() {
    String item = first.item;
    first = first.next;
    return item;
  }
  
  // is the stack emplty?
  boolean isEmpty() {
    return first == null;
  }
  
  // number of strings on the stack
  int size() {
    int count = 0;
    Node current = first;
    while (current != null) {
      current = current.next;
      count++;
    }
    return count;
  }
  
  public static void main(String[] args) {
    StackOfStrings stack = new StackOfStrings();
    while (!StdIn.isEmpty()) {
      String s = StdIn.readString();
      if (s.equals("-"))
        StdOut.print(stack.pop());
      else
        stack.push(s);
    }
  }
}