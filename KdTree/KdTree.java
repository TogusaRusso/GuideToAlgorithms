import java.util.TreeSet;
public class KdTree {
  
  private int size;
  private Node root;
  
  private class Node {
    private Point2D point;
    private Node left, right;
    
    Node(Point2D pnt) {
      this.point = pnt;
      this.left  = null;
      this.right = null;
    }
    Point2D point() { return this.point; }
    Node  left() { return this.left; }
    Node right() { return this.right; }
    void  setLeft(Node p) { this.left  = p; }
    void setRight(Node p) { this.right = p; }
    private int compareTo(Point2D p,  boolean vertical) {
      if (this.point.equals(p)) { return 0; }
      if (vertical) {
        if (p.x() < this.point.x()) { return -1; }
        else                        { return  1; }
      }
      else {
        if (p.y() < this.point.y()) { return -1; }
        else                        { return  1; }
      }
    }
  }
  
   
   // construct an empty set of points public         
   public KdTree() {
     root = null; 
     size = 0;
   }
   
   // is the set empty? 
   public boolean isEmpty() {
     return size == 0;
   }
   
   // number of points in the set 
   public int size() {
     return size;
   }
   
   // add the point to the set (if it is not already in the set)
   public void insert(Point2D p) {
     root = insert(root, p, true);
   }
   private Node insert(Node node, Point2D p, boolean vertical) {
     if (node == null) {
       size += 1;
       return new Node(p);
     }
     int comp = node.compareTo(p, vertical);
     if      (comp < 0) {  
       node.setLeft(insert(node.left(),  p, !vertical)); 
       return node; 
     }
     else if (comp > 0) { 
       node.setRight(insert(node.right(), p, !vertical)); 
       return node; 
     }
     else               { 
       return node; 
     }
   }
   
   // does the set contain point p? 
   public boolean contains(Point2D p) {
     return contains(root, p, true);
   }
   private boolean contains(Node node, Point2D p, boolean vertical) {
     if (node == null) { return false; }
     int comp = node.compareTo(p, vertical);
     if      (comp < 0) { return contains(node.left(),  p, !vertical); }
     else if (comp > 0) { return contains(node.right(), p, !vertical); }
     else               { return true; }
   }
   
   // draw all points to standard draw 
   public void draw() {
     RectHV rect = new RectHV(0.0, 0.0, 1.0, 1.0);
     draw(root, rect, true);
   }
   private void draw(Node node, RectHV rect, boolean vertical) {
     if (node == null) { return; }
     StdDraw.setPenColor(StdDraw.BLACK);
     StdDraw.setPenRadius(.01);  
     node.point().draw();
     RectHV rectLeft, rectRight;
     if (vertical) {
       rectLeft  = new RectHV(rect.xmin(), rect.ymin(), node.point().x(),  rect.ymax());
       rectRight = new RectHV(node.point().x(), rect.ymin(), rect.xmax(),  rect.ymax());
       StdDraw.setPenColor(StdDraw.RED);
       StdDraw.setPenRadius();
       StdDraw.line(node.point().x(), rect.ymin(), node.point().x(),  rect.ymax());
     }
     else {
       rectLeft  = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.point().y());
       rectRight = new RectHV(rect.xmin(), node.point().y(), rect.xmax(), rect.ymax());
       StdDraw.setPenColor(StdDraw.BLUE);
       StdDraw.setPenRadius();
       StdDraw.line(rect.xmin(), node.point().y(), rect.xmax(), node.point().y());
     }
     draw(node.left(),  rectLeft,  !vertical);
     draw(node.right(), rectRight, !vertical);
   }
   
   // all points that are inside the rectangle 
   public Iterable<Point2D> range(RectHV rect) {
     TreeSet<Point2D> inside = new TreeSet<Point2D>();
     RectHV rectCurrent = new RectHV(0.0, 0.0, 1.0, 1.0);
     range(rect, rectCurrent, inside, root, true);
     return inside;
   }
   private void range(RectHV rect, RectHV rectCurrent, 
                      TreeSet<Point2D> inside, Node node, boolean vertical) {
     if (node == null)                  { return; }
     if (!rect.intersects(rectCurrent)) { return; }
     if (rect.contains(node.point()))   { inside.add(node.point()); }
     RectHV rectLeft, rectRight;
     if (vertical) {
       rectLeft  = new RectHV(rectCurrent.xmin(), rectCurrent.ymin(), node.point().x(),  rectCurrent.ymax());
       rectRight = new RectHV(node.point().x(), rectCurrent.ymin(), rectCurrent.xmax(),  rectCurrent.ymax());
     }
     else {
       rectLeft  = new RectHV(rectCurrent.xmin(), rectCurrent.ymin(), rectCurrent.xmax(), node.point().y());
       rectRight = new RectHV(rectCurrent.xmin(), node.point().y(), rectCurrent.xmax(), rectCurrent.ymax());
     }
     range(rect, rectLeft,  inside, node.left(),  !vertical);
     range(rect, rectRight, inside, node.right(), !vertical);
   }

   // a nearest neighbor in the set to point p; null if the set is empty 
   public Point2D nearest(Point2D p) {
     Nearest nearest = new Nearest();
     RectHV rectCurrent = new RectHV(0.0, 0.0, 1.0, 1.0);
     nearest(p, rectCurrent, nearest, root, true);
     return nearest.point;
   }
   private class Nearest {
     private Point2D point;
     private double  distance;
     Nearest() {
       point    = null;
       distance = Double.POSITIVE_INFINITY;
     }
     void candidate(Point2D p, Point2D candidate) {
       double dist = p.distanceSquaredTo(candidate);
       if (dist < this.distance) {
         this.point    = candidate;
         this.distance = dist;
       }
     }
     boolean check(Point2D p, RectHV rect) {
       return rect.distanceSquaredTo(p) > this.distance;
     }
   }
   private void nearest(Point2D p, RectHV rect, Nearest nearest, Node node, boolean vertical) {
     if (node == null)           { return; }
     if (nearest.distance == 0)  { return; }
     if (nearest.check(p, rect)) { return; }
     nearest.candidate(p, node.point());
     RectHV rectLeft, rectRight;
     int comp = node.compareTo(p, vertical);
     if (comp == 0) { return; }
     if (vertical) {
       rectLeft  = new RectHV(rect.xmin(), rect.ymin(), node.point().x(), rect.ymax());
       rectRight = new RectHV(node.point().x(), rect.ymin(), rect.xmax(), rect.ymax());
     }
     else {
       rectLeft  = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.point().y());
       rectRight = new RectHV(rect.xmin(), node.point().y(), rect.xmax(), rect.ymax());
     }
     if (comp < 0) {
       nearest(p, rectLeft,  nearest, node.left(),  !vertical);
       nearest(p, rectRight, nearest, node.right(), !vertical);
     }
     else {
       nearest(p, rectRight, nearest, node.right(), !vertical);
       nearest(p, rectLeft,  nearest, node.left(),  !vertical);
     }
   }
   
   // unit testing of the methods (optional) 
   public static void main(String[] args) {
   }
}