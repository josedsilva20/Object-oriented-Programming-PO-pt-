

public abstract class Form {

  private Point _origin;

  public Form(Point o) {
    _origin = o;
  }

  public abstract void draw();

  public void move(Point d ) {
    _origin = _origin.add(d);
  }

  public abstract boolean containsPoint(Point p);

  protected Point getOrigin() {
    return _origin;
  }
}
