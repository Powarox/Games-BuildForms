package playerStrategy;

import java.util.Random;

public class DoublePoint {
    private double x = 0;
    private double y = 0;

    public DoublePoint(double x, double y) {
        this.setX(x);
        this.setY(y);
    }
    
    public void setX(double x) {
        this.x = x;
    }
    
    public double getX()  {
        return this.x;
    }
    
    public void setY(double y) {
        this.y = y;
    }
    
    public double getY() {
        return this.y;
    }

    protected static DoublePoint createRandomPoint(int min, int max) {
    	Random r = new Random();
    	double x = min + (max - min) * r.nextDouble();
    	double y = min + (max - min) * r.nextDouble();
    	return new DoublePoint(x,y);
    }
 
    public String toString() {
    	return "("+x+","+y+")";
    }
}