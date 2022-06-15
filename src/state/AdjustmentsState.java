package state;

import command.AdjustmentCommand;
import model.*;
import controlleur.Controlleur;
import view.GUI;
import view.JpanelJeu;
import view.ViewCercle;
import view.ViewObject;

public class AdjustmentsState implements State {
	ViewObject object;
	ViewObject adjustObject;
    Model model;

	public AdjustmentsState(Model model) {
		this.model = model;
	}

	// methode qui verifie si l objet est un cercle ou rectangle
	@Override
	public void mouseDragged(Point p) {
		JpanelJeu.endDrag = p;
		if(this.adjustObject!=null) {
			if(this.adjustObject instanceof ViewCercle) {
				cercleAdjusting(p);
			}
			else{
				rectangleAdjusting(p);
			}
		}
	}

	// methode qui repositionne les points du cercle et son centre
	public void cercleAdjusting(Point p ) {
		this.adjustObject.getObject().setX2((int) (this.adjustObject.getObject().getX2()+ p.getX() - ((Cercle) this.adjustObject.getObject()).getCenterX()));
		this.adjustObject.getObject().setY2((int) (this.adjustObject.getObject().getY2()+ p.getY() - ((Cercle) this.adjustObject.getObject()).getCenterY()));
		
		this.adjustObject.getObject().setX1((int) (this.adjustObject.getObject().getX1()+ p.getX() - ((Cercle) this.adjustObject.getObject()).getCenterX()) );
		this.adjustObject.getObject().setY1((int) (this.adjustObject.getObject().getY1()+ p.getY() - ((Cercle) this.adjustObject.getObject()).getCenterY()));
		
		((Cercle) this.adjustObject.getObject()).setCenterX( p.getX());
		((Cercle) this.adjustObject.getObject()).setCenterY( p.getY());
	}

	// methode qui repositionne les points du rectangles
	public void rectangleAdjusting(Point p) {
		int x1 = p.getX()-this.adjustObject.getObject().getX2();
		int y1 = p.getY()-this.adjustObject.getObject().getY2();
		
		this.adjustObject.getObject().setX2(p.getX());
		this.adjustObject.getObject().setY2(p.getY());
		
		this.adjustObject.getObject().setX1(this.adjustObject.getObject().getX1()+x1);
		this.adjustObject.getObject().setY1(this.adjustObject.getObject().getY1()+y1);
	}

	@Override
	public void mouseReleased(Point p1, Point p2) {
		this.adjustObject = null;
	}

	@Override
	public void mousePressed(Point p) {
		for(ViewObject object: GUI.objects ) {
			if(object.getPoints().contains(p.getX(), p.getY())) {
				this.object = object;
				this.adjustObject = object.getObjectCopy();
				AdjustmentCommand adjustCommand = new AdjustmentCommand(this.object,this.adjustObject ,this.model);
				Controlleur.handler.handle(adjustCommand);
			}
			break;
		}
	}

	@Override
	public ViewObject make() {
		return null;
	}
	
	public String toString() {
		return "adjustement";
		
	}
}
