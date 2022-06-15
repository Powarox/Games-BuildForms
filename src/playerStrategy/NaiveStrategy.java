package playerStrategy;

import java.util.ArrayList;
import model.Model;
import model.Point;
import model.Rectangle;
import view.JpanelJeu;

public class NaiveStrategy implements PlayerStrategy{
	public Model model;
	public ArrayList<DoublePoint> points;
	public JpanelJeu jpanelJeu =new JpanelJeu();
	private ArrayList<Rectangle> rectangles= new ArrayList<Rectangle>();
	private ArrayList<Point> points2d;

	public NaiveStrategy(Model model) {
		this.model=model;
		this.points = new ArrayList<DoublePoint>();
		//panelClass=new PanelClass(model);
	}

	@Override
	public void init() {
		points2d = this.model.getPoints();
    	for(int i = 0; i<points2d.size(); i++) {
    		points.add(new DoublePoint((int) points2d.get(i).getX(), (int) points2d.get(i).getY()));
    	}
	}

	@Override
	public void solveGame() {
		int minX = 10000;
    	int minY = 10000;
    	int maxX = 0;
    	int maxY = 0;
    	/*
		System.out.println("taille liste " +liste.size());
		for (Shape s : liste){
			System.out.println(s.getBounds());
		}
		System.out.println(panelClass/);
    	for(int i =0; i<this.points.size(); i++) {
    		
    		if ( this.points.get(i).getX() < minX) {
    			minX= (int) this.points.get(i).getX();}
    		if ( this.points.get(i).getY() < minY) {
    			minY= (int) this.points.get(i).getY() ;
    		}
    	
    			
    		if (this.points.get(i).getX() > maxX ){
    			maxX= (int) this.points.get(i).getX();}
    		if( this.points.get(i).getY() > maxY) {
    			maxY= (int) this.points.get(i).getY() ;
    		}
    	}
    	*/

    	Point startPoint = new Point(10, 10);
    	Point endPoint = new Point(211, 211);
    	Rectangle rectangle = new Rectangle(startPoint, endPoint);
		/*
		System.out.println("dsqd sd = " +panelClass.liste());
    	PanelClass p = new PanelClass(this.model);
    	for (Shape r: this.panelClass.liste()){
			System.out.println(r.getBounds());
		}
		*/
    	this.rectangles.add(rectangle);
	}

	@Override
	public ArrayList<Rectangle> getRectangles() {
		return this.rectangles;
	}

	@Override
	public double getScore() {
		int sum = 0;
    	for(Rectangle form: this.rectangles) {
    		if(form !=null) {
    		sum+= form.getSurface();}
    	}
    	return  (sum/Model.PANEL_SURFACE);
	}
}

/*
	public void IA(){
		int minX = 10000;
		int minY = 10000;
		int maxX= 0;
		int maxY=0;

    	for(int i =0; i<800; i++) {
    		for (int j =0; j<650; j++){
				for (Shape s : listeDesDessinsDeBase){
					if (s.getBounds().contains(i,j));
					System.out.println("-*-*-*-*-*-*-*-");
				}
			}
		}
   		if ( this.points.get(i).getX() < minX) {
   			minX= (int) this.points.get(i).getX();}
   		if ( this.points.get(i).getY() < minY) {
    		minY= (int) this.points.get(i).getY() ;
    	}
    	if (this.points.get(i).getX() > maxX ){
    		maxX= (int) this.points.get(i).getX();}
   		if( this.points.get(i).getY() > maxY) {
   			maxY= (int) this.points.get(i).getY() ;
   		}
    }
*/

