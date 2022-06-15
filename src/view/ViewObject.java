package view;

import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import model.Form;

public interface ViewObject {
    public void draw(Graphics g);
	public Form getObject();
	public Rectangle2D getPoints();
	public RectangularShape getViewObject();
	public ViewObject getObjectCopy();
}
