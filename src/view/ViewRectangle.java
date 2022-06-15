package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;

import model.Form;
import model.Point;
import model.Rectangle;

public class ViewRectangle  implements ViewObject {
    Rectangle rectangle;
    Rectangle2D points;
    Rectangle2D viewRectangle;
   
    public ViewRectangle(Rectangle rectangle){
        this.rectangle = rectangle;
   	}
    
    @Override
    public void draw(Graphics g) {
    	System.out.println(rectangle);
    	int xStart = rectangle.getX1();
    	int yStart= rectangle.getY1();
    	
    	int xEnd = rectangle.getX2();
    	int yEnd = rectangle.getY2();

    	Graphics2D g2 = (Graphics2D) g;
    	this.points = new Rectangle2D.Double(xEnd, yEnd,5, 5);
    	g2.fill(points);
    	g2.draw(makeRectangle(xStart, yStart, xEnd, yEnd));
    }

    public Float makeRectangle(int x1, int y1, int x2, int y2) {
    	this.viewRectangle = new Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
    	return (Float) this.viewRectangle;
    }

	public Rectangle2D getPoints() {
		return this.points;
	}

	@Override
	public Form getObject() {
		// TODO Auto-generated method stub
		return this.rectangle;
	}

	@Override
	public Rectangle2D getViewObject() {
		// TODO Auto-generated method stub
		return (Float) this.viewRectangle;
	}

	@Override
	public ViewRectangle getObjectCopy() {
		Rectangle rectangle = new Rectangle(new Point(this.rectangle.getX1(), this.rectangle.getY1()), new Point(this.rectangle.getX2(), this.rectangle.getY2()));
		return new ViewRectangle(rectangle);
	}
}
