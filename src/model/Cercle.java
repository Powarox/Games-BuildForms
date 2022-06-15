package model;

public class Cercle extends Form {
	public int centerX;
	public int centerY;

	public Cercle(Point startPoint, Point endPoint) {
		super(startPoint, endPoint);
		 setCenterX((int) ((startPoint.getX()+endPoint.getX())/2));
		 setCenterY((int) ((startPoint.getY()+endPoint.getY())/2));
	}

	public int getCenterX() {
		return centerX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	@Override
	public double getSurface() {
		double r = Math.abs(this.x1-this.x2)/2;
		double surface = (int) (Math.PI * Math.pow(r, 2));
		return surface;
	}

	@Override
	protected String getName() {
		return "Cercle";
	}
}
