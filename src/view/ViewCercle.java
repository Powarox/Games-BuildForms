package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.Rectangle2D;
import model.*;

public class ViewCercle implements ViewObject{
    Cercle cercle;
    Rectangle2D points;
    Ellipse2D viewCircle;

    public ViewCercle(Cercle cercle){
        this.cercle = cercle;
    }

	@Override
    public void draw(Graphics g) {
    	int xStart = cercle.getX1();
    	int yStart= cercle.getY1();
    	int xEnd = cercle.getX2();
    	int yEnd = cercle.getY2();
    	Graphics2D g2 = (Graphics2D) g;
    	this.points = new Rectangle2D.Double(cercle.getCenterX() , cercle.getCenterY() , 5, 5);
    	g2.fill(points);
    	g2.draw(makeCercle(xStart, yStart, xEnd, yEnd));
    }

	private Double makeCercle(int xStart, int yStart, int xEnd, int yEnd) {
		this.viewCircle = new Double(Math.min(xStart, xEnd), Math.min(yStart, yEnd),Math.abs(xStart-xEnd), Math.abs(xStart-xEnd));
     	return (Double) this.viewCircle;
	}

	@Override
	public Form getObject() {
		// TODO Auto-generated method stub
		return this.cercle;
	}

	// Renvoie le rectangle spécifique au centre du cercle utilisé pour l'ajustement de la redimensionnement et la suppression de l'objet.
	@Override
	public Rectangle2D getPoints() {
		// TODO Auto-generated method stub
		return this.points;
	}

	@Override
	public Ellipse2D getViewObject() {
		// TODO Auto-generated method stub
		return this.viewCircle;
	}

	@Override
	public ViewCercle getObjectCopy() {
		Cercle cercle = new Cercle(new Point(this.cercle.getX1(), this.cercle.getY1()), new Point( this.cercle.getX2(), this.cercle.getY2()));
		return new ViewCercle(cercle);
	}
}
