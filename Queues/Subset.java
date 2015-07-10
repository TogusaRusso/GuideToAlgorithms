public class Subset {
  public static void main(String[] args) {
    RandomizedQueue<String> memory = new RandomizedQueue<String>();
    int k = Integer.parseInt(args[0]);
    String item;
    int N = 0;
    //Reservoir sampling
    //https://en.wikipedia.org/wiki/Reservoir_sampling
    while (!StdIn.isEmpty()) {
      item = StdIn.readString();
      N++;
      if (N <= k)
        memory.enqueue(item);
      else 
        if (StdRandom.uniform(N) < k) {
          memory.dequeue();
          memory.enqueue(item);
        }
    }
    for (int i = 0; i < k && !memory.isEmpty(); i++)
      StdOut.println(memory.dequeue());
  }
}