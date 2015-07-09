public class Subset {
  public static void main(String[] args) {
    RandomizedQueue<String> memory = new RandomizedQueue<String>();
    int k = Integer.parseInt(args[0]);
    String item;
    while (!StdIn.isEmpty()) {
      item = StdIn.readString();
      memory.enqueue(item);
      if(memory.size() == k)
        memory.dequeue();
    }
    for(int i = 0; i < k && !memory.isEmpty(); i++)
      StdOut.println(memory.dequeue());
  }
}