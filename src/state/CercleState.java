package state;

import command.*;
import model.Cercle;
import model.Model;
import model.Point;
import controlleur.Controlleur;
import view.JpanelJeu;
import view.ViewCercle;
import view.ViewObject;

public class CercleState implements State{
	public Model model;

	public CercleState(Model model) {
		this.model=model;
	}

	@Override
	public void mouseDragged(Point p) {
		JpanelJeu.endDrag = p;
	}

	// methode qui cree le cercle en creant sa forme et le passe a AddObjectCommand
	@Override
	public void mouseReleased(Point p1, Point p2) {
		  int x1 = Math.min(p1.getX(), p2.getX());
		  int x2 = Math.max(p1.getX(), p2.getX());
		  int y1 = Math.min(p1.getY(), p2.getY());
		  int y2 = Math.min(p1.getY(), p2.getY()) + Math.abs(p1.getX()-p2.getX());
		  
		  Point startPoint = new Point(x1, y1);
		  Point endPoint = new Point(x2, y2);
		  Cercle c = new Cercle(startPoint, endPoint);
	      ViewCercle r = new ViewCercle(c); 
	     
	      OperationCommand command = new AddObjectCommand(r, model);
	      Controlleur.handler.handle(command);
	}

	@Override
	public void mousePressed(Point p) {
	
	}

	// Cree et retourne ViewCercle( sa vue )
	@Override
	public ViewObject make() {
		Cercle cercle = new Cercle(JpanelJeu.startDrag, JpanelJeu.endDrag);
		ViewCercle c = new ViewCercle(cercle);
		return c;
	}
}
