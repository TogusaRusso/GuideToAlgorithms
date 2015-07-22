private class Node {
  private Key key;
  private Value val;
  private Node left, right;
  
  public Node(Key key, Value val)
  {
    this.key = key;
    this.val = val;
  }
}