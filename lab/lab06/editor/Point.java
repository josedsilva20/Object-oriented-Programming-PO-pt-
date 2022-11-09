
public class Point {
  private int _x;
  private int _y;

  public Point(int x, int y) {
    _x = x;
    _y = y;
  }

  @Override
  public String toString() {
    return "(" + _x + ", " + _y + ")";
  }

  public final Point add(Point a) {
    return new Point(_x + a._x, _y + a._y);
  }
}
