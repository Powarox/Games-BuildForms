package state;

import model.Point;
import view.ViewObject;

public class CurrentState implements State {
	State state;

	public CurrentState() {
		this.state = new InitialState();
	}
	
	public void setState( State state) {
		this.state = state;
	}
	
	public State getstate() {
		return this.state;
	}

	@Override
	public void mouseDragged(Point p) {
		this.state.mouseDragged(p);
	}

	@Override
	public void mouseReleased(Point p1, Point p2) {
		this.state.mouseReleased(p1, p2);
	}

	@Override
	public void mousePressed(Point p) {
		this.state.mousePressed(p);
	}

	@Override
	public ViewObject make() {
		ViewObject object = this.state.make();
		return object;
	}

	public String toString() {
		return this.state.toString();
	}
}
