package state;

import command.RedemensionCommand;
import model.Cercle;
import model.Model;
import model.Point;
import controlleur.Controlleur;
import view.GUI;
import view.JpanelJeu;
import view.ViewCercle;
import view.ViewObject;

public class RedemensionState implements State {
	ViewObject object;
	ViewObject redemensionObject;
    Model model;

	public RedemensionState(Model model) {
		this.model=model;
	}

	// methode qui verifie si l objet a redimensionner est un cercle ou rectangle
	@Override
	public void mouseDragged(Point p) {
		if(this.redemensionObject!=null) {
			JpanelJeu.endDrag = p;
			if(this.redemensionObject instanceof ViewCercle) {
				cercleRedimension(p);
			}
			else {
				this.redemensionObject.getObject().setX2(p.getX());
				this.redemensionObject.getObject().setY2(p.getY());
			}
		}
	}

	// methode qui repositionne les points du cercle et son centre
   public void cercleRedimension(Point p ) {
		this.redemensionObject.getObject().setX2(this.redemensionObject.getObject().getX2()+ 2*(p.getX() - ((Cercle) this.redemensionObject.getObject()).getCenterX()));
		this.redemensionObject.getObject().setY2(this.redemensionObject.getObject().getY2()+ 2*(p.getX() - ((Cercle) this.redemensionObject.getObject()).getCenterX()));
		
		((Cercle) this.redemensionObject.getObject()).setCenterX(p.getX());
		((Cercle) this.redemensionObject.getObject()).setCenterY(p.getY());
	}

	@Override
	public void mouseReleased(Point p1, Point p2) {
		this.redemensionObject = null;
	}

	@Override
	public void mousePressed(Point p) {
		for(ViewObject object: GUI.objects ) {
			if(object.getPoints().contains(p.getX(), p.getY())) {
				this.object = object;
				this.redemensionObject = this.object.getObjectCopy();
				RedemensionCommand redemensionCommand = new RedemensionCommand(this.object,this.redemensionObject ,this.model);
				Controlleur.handler.handle(redemensionCommand);
			}
			break;
		}
	}

	@Override
	public ViewObject make() {
		return null;
	}

	public String toString() {
		return "redemension";
	}
}




