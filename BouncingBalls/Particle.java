public class Particle {
  private double       rx, ry; // position
  private double       vx, vy; // velocity
  private final double radius; // radius
  private final double mass;   // mass
  private int          count;  // number of collisions 
  private final double INFINITY = Double.POSITIVE_INFINITY;
  
  public Particle() {
    radius = .01 * StdRandom.uniform();
    mass = radius * radius;
    rx = StdRandom.uniform();
    ry = StdRandom.uniform();
    vx = StdRandom.uniform() - 0.5;
    vy = StdRandom.uniform() - 0.5;
    count = 0;
  }
  
  public void move(double dt) {
    //check collisions with walls
    //if ((rx + vx * dt < radius) || (rx + vx * dt > 1.0 - radius)) { vx = - vx; }
    //if ((ry + vy * dt < radius) || (ry + vy * dt > 1.0 - radius)) { vy = - vy; }
    rx += vx * dt;
    ry += vy * dt;
  }
  
  public void draw() {   
    StdDraw.filledCircle(rx, ry, radius);
  }
  
  public double timeToHit(Particle that)  {
    if (this == that) return INFINITY;
    double dx  = that.rx - this.rx, dy  = that.ry - this.ry;
    double dvx = that.vx - this.vx, dvy = that.vy - this.vy;
    double dvdr = dx * dvx + dy * dvy;
    if (dvdr > 0) return INFINITY;
    double dvdv = dvx * dvx + dvy * dvy;
    double drdr = dx * dx + dy * dy;
    double sigma = this.radius + that.radius;
    double d = (dvdr * dvdr) - dvdv * (drdr - sigma * sigma);
    if (d < 0) return INFINITY;
    return -(dvdr + Math.sqrt(d)) / dvdv;
  }
  public double timeToHitVerticalWall()   {
    if (vx == 0) {return INFINITY;}
    if (vx > 0)  {return (1 - rx) / vx;}
    return  - rx / vx;
  }
  public double timeToHitHorizontalWall() {
    if (vy == 0) {return INFINITY;}
    if (vy > 0)  {return (1 - ry) / vy;}
    return  - ry / vy;
  }                        
  
  public void BounceOff(Particle that)  {
    double dx  = that.rx - this.rx, dy  = that.ry - this.ry;
    double dvx = that.vx - this.vx, dvy = that.vy - this.vy;
    double dvdr = dx * dvx + dy * dvy;
    double dist = this.radius + that.radius;
    double J = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist);
    double Jx = J * dx / dist;
    double Jy = J * dy / dist;
    this.vx += Jx / this.mass;
    this.vy += Jy / this.mass;
    that.vx -= Jx / that.mass; 
    that.vy -= Jy / that.mass;
    this.count ++;
    that.count ++;
  }
  public void BounceOffVerticalWall()   {vx = -vx;}
  public void BounceOffHorizontalWall() {vy = -vy;}
  
  public int getCount() { return count; }
                         
}
    