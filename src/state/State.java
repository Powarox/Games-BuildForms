package state;

import model.Point;
import view.ViewObject;

public interface State {
	public void mouseDragged(Point p);
	public void mousePressed(Point p);
	void mouseReleased(Point p1, Point p2);
	ViewObject make();
}
