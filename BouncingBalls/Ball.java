public class Ball {
  private double       rx, ry; // position
  private double       vx, vy; // velocity
  private final double radius; // radius
  public Ball() {
    radius = .01;
    rx = StdRandom.uniform();
    ry = StdRandom.uniform();
    vx = StdRandom.uniform() - 0.5;
    vy = StdRandom.uniform() - 0.5;
  }
  
  public void move(double dt) {
    //check collisions with walls
    if ((rx + vx * dt < radius) || (rx + vx * dt > 1.0 - radius)) { vx = - vx; }
    if ((ry + vy * dt < radius) || (ry + vy * dt > 1.0 - radius)) { vy = - vy; }
    rx += vx * dt;
    ry += vy * dt;
  }
  
  public void draw() {   
    StdDraw.filledCircle(rx, ry, radius);
  }
}
    