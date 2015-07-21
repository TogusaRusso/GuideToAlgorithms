public class CollisionSystem {
  private MinPQ<event> pq;
  private double t = 0.0;
  private Particle[] particles;
  
  public CollisionSystem(Particle[] particles) {}
  
  private void predict(Particle a) {
    if (a == null) return;
    for (int  i = 0; i < N; i++) {
      double dt = a.timeToHit(particles[i]);
      pq.insert(new Event(t + dt, a, particles[i]));
    }
    pq.insert(new Event(t + a.timeToHitVerticalWall(), a, null));
    pq.insert(new Event(t + a.timeToHitHorizontalWall(), null, a));
  }
  
  private void redraw() {}
  
  public void simulate {
    pq = new MinPQ<Event>();
    for (int i = 0; i < N; i++) predict(particles[i]);
    pq.insert(new Event(0, null, null));
    while (!pq.isEmpty()) {
      Event event = pq.delMin();
      if(!event.isValid()) continue;
      Particle a = event.a;
      Particle b = event.b;
      
      for (int i = 0; i < N; i++)
        particles[i].move(event.time - t);
      t = event.time;
      if      (a !=  null && b != null) a.BounceOff(b);
      else if (a !=  null && b == null) a.bounceOffVerticalWall();
      else if (a ==  null && b != null) a.bounceOffHorizontalWall();
      else if (a ==  null && b == null) redraw();
      
      predict(a);
      predict(b);
  }
}