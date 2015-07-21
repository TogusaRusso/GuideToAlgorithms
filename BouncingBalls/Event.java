public class Event implements Comparable<Event> {
  private double time;         // time of event
  private Particle a, b;       // particles involved in event
  private int countA, countB;  // collision counts for a and b
  
  public Event(double t, Particle a, Particle b) {
    time = t;
    this.a = a;
    this.b = b;
    countA = a.getCount();
    countB = b.getCount();
  }
  
  public int compareTo(Event that) {
    return (int)(this.time - that.time);
  }
  
  public boolean isValid() {
    return a.getCount() == countA && b.getCount() == countB;
  }
}
  