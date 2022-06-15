package model;

public class Point {
	public int x = 0;
	public int y = 0;

	public Point(int x, int y) {
		this.setX(x);
	    this.setY(y);
	}
	    
	public void setX(int x) {
		this.x = x;
	}
	    
	public int getX()  {
		return this.x;
	}
	    
	public void setY(int y) {
		this.y = y;
	}
	    
	public int getY() {
		return this.y;
	}
}
