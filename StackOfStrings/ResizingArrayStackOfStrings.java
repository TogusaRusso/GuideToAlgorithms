public class ResizingArrayStackOfStrings {
  private int N = 0;
  private String[] s;
  
  public ResizingArrayStackOfStrings() {
    s = new String[1];
  }
  
  private void resize(int capicity) {
    String[] copy = new String[capicity];
    for (int i = 0; i < N; i++)
      copy[i] = s[i];
    s = copy;
  }
      
  
  // insert a new string onto stack
  void push(String item) {
    if (N == s.length)
      resize(2 * N);
    s[N++] = item;
  }
  
  // remove and return the string most recently added
  String pop() {
    String item = s[--N];
    if (N > 0 && N == s.length / 4)
      resize(N * 2);
    return item;
  }
  
  // is the stack emplty?
  boolean isEmpty() {
    return N == 0;
  }
  
  // number of strings on the stack
  int size() {
    return N;
  }
  
  public static void main(String[] args) {
    ResizingArrayStackOfStrings stack = new ResizingArrayStackOfStrings();
    while (!StdIn.isEmpty()) {
      String s = StdIn.readString();
      if (s.equals("-"))
        StdOut.print(stack.pop());
      else
        stack.push(s);
    }
  }
}