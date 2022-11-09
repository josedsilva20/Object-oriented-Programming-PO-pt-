

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

public class Editor {
  private List<Form> _forms;

  public Editor() {
    _forms = new ArrayList<>();
  }

  public void drawAll() {
    for(Form f : _forms)
      f.draw();
  }

  public void remove(Form f) {
    _forms.remove(f);
  }

  public void removeContainingPoint(Point p) {
    Iterator<Form> iter = _forms.iterator();

    while (iter.hasNext()) {
      Form f = iter.next();
      
      if (f.containsPoint(p))
        iter.remove();
    }
  }

  public void bringToFront(Form f) {
    _forms.remove(f);
    _forms.add(0, f);
  }

  public void bringToLast(Form f) {
     _forms.remove(f);
    _forms.add(f);
  }
}
   
