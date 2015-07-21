public class BouncingBalls {
  public static void main(String[] args) {
    int N = Integer.parseInt(args[0]);
    Ball[] balls = new Ball[N];
    for (int i = 0; i < N; i++)
      balls[i] = new Ball();
    StdDraw.setXscale(0, 1);
    StdDraw.setYscale(0, 1);
    while(true) {
      StdDraw.clear();
      for(int i = 0; i < N; i++) {
        balls[i].move(0.05);
        balls[i].draw();
      }
      StdDraw.show(50);
    }
  }
}
    