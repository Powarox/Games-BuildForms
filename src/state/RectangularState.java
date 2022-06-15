package state;

import command.AddObjectCommand;
import command.OperationCommand;
import model.Model;
import model.Point;
import model.Rectangle;
import controlleur.Controlleur;
import view.JpanelJeu;
import view.ViewObject;
import view.ViewRectangle;

public class RectangularState implements State{
	Model model;
	
	public RectangularState(Model model) {
		this.model = model;
	}

	@Override
	public void mouseDragged(Point p) {
		JpanelJeu.endDrag = p;
	}

	// methode qui cree le rectangle en creant sa forme et le passe a AddObjectCommand
	@Override
	public void mouseReleased(Point p1, Point p2) {
		Rectangle r1 = new Rectangle(p1, p2);
        ViewRectangle r = new ViewRectangle(r1);
        
    	OperationCommand command = new AddObjectCommand(r,model);
	    Controlleur.handler.handle(command);
	}

	@Override
	public void mousePressed(Point p) {
		// TODO Auto-generated method stub
	}

	//Cree et retourne ViewRectangle
	public ViewObject make() {
		Rectangle rectangle = new Rectangle( JpanelJeu.startDrag, JpanelJeu.endDrag);
	    ViewRectangle r = new ViewRectangle(rectangle);
	    return r;
	}
}



