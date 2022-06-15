package state;

import model.Point;
import view.ViewObject;

public class InitialState implements State{

	// etat inital ou toutes les methodes sont vides
	@Override
	public ViewObject make() {
		return null;
	}
	
	public String toString() {
		return "initial";
	}

	@Override
	public void mouseDragged(Point p) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(Point p) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(Point p1, Point p2) {
		// TODO Auto-generated method stub
	}
}
