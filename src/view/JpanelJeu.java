package view;

import model.*;
import model.Point;
import model.Rectangle;
import controlleur.Controlleur;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.JPanel;

public class JpanelJeu extends JPanel {
	private ArrayList<Rectangle> rectangless= new ArrayList<Rectangle>();
	public static Point startDrag;
	public static Point endDrag;
	final int dim = 5;

	public ArrayList<Point> points;
	public ArrayList<Point> coveredPoints = new ArrayList<Point>();
	public Model model;
	public boolean solve = false;
	public ArrayList<ViewRectangle> rectangles = new ArrayList<ViewRectangle>();
	public ArrayList<Shape> lesDessinsDeBases = new ArrayList<Shape>();
	public List<Shape> listeDesDessinsDeBase;
	Random random = new Random();

	private int xRectangle1 = random.nextInt(460);
	private int yRectangle1 = random.nextInt(360);
	private int xRectangle2 = random.nextInt(460);
	private int yRectangle2 = random.nextInt(360);

	private int xCercle1 = random.nextInt(460);
	private int yCercle1 = random.nextInt(360);
	private int xCercle2 = random.nextInt(460);
	private int yCercle2 = random.nextInt(360);
	private int taille = 65;

	public JpanelJeu(Model model) {
		this.setBackground(Color.red);
		this.model=model;
		this.setPreferredSize(new Dimension(200, 200));
	    this.setVisible(true);
	    this.points = model.getPoints();
		listeDesDessinsDeBase=this.liste();
	}

	public JpanelJeu(){

	}

	//Méthode qui peint l'état initial du Jpanel.
	private void paintBackground(Graphics2D g2){
		this.coveredPoints.clear();
		paintInitialPoints(g2);
		if(GUI.objects.size()>=0) {
			// getCoveredPoints();
		  	paintCoveredPoints(g2);
		}
	 }

	//Méthode qui peint les points initiaux de la stratégie
	// cette methode devait etre implemente dans une classe specifique (RandomStrategy)
	public void paintInitialPoints(Graphics2D g2) {
		Rectangle2D rectangle = new Rectangle2D.Double(xRectangle1, yRectangle1, taille, taille);
		g2.setPaint(Color.black);

		lesDessinsDeBases.add(rectangle);
		Rectangle2D rectangle2 = new Rectangle2D.Double(xRectangle2, yRectangle2, taille, taille);
		// Intersection des deux rectangles
		if(this.estEnIntersection(rectangle,rectangle2)) {
			//System.out.println("intersect");
			g2.clearRect(xRectangle1, yRectangle2, taille, taille );
			xRectangle2 =random.nextInt(460);
			yRectangle2 =random.nextInt(360);
			rectangle2=new Rectangle2D.Double(xRectangle1, yRectangle2, taille, taille);
			g2.fill(rectangle2);
			lesDessinsDeBases.add(rectangle2);
		}
		//else{
		//	g2.fill(rectangle2);
		//	lesDessinsDeBases.add(rectangle2);
		//}
		// creation des deux cercles
		Ellipse2D cercle1 = new Ellipse2D.Double(xCercle1, yCercle1, taille, taille);//on crée le Cercle à afficher
		Ellipse2D cercle2 = new Ellipse2D.Double(xCercle2, yCercle2, taille, taille);
		g2.fill(cercle1);
		g2.fill(cercle2);
		// Intersection entre les deux cercles
		if(this.estEnIntersection(cercle1,cercle2)){
			//	System.out.println("method march bien ");
			xCercle2 =random.nextInt(460);
			yCercle2 =random.nextInt(360);
			cercle2 = new Ellipse2D.Double(xCercle2, yCercle2, taille, taille);
			g2.fill(cercle2);
			lesDessinsDeBases.add(cercle2);
		}

		// Intersection premier rectangle et le cercle 2
		if (rectangle.intersects(cercle1.getBounds2D())) {
			// System.out.println("intersection rec et cer ");
			xCercle1 =random.nextInt(460);
			yCercle1 =random.nextInt(360);
			cercle1 = new Ellipse2D.Double(xCercle1, yCercle1, taille, taille);
			g2.fill(cercle1);
			lesDessinsDeBases.add(cercle1);
		}

		if (rectangle.intersects(cercle2.getBounds2D())) {
			xCercle2 =random.nextInt(460);
			yCercle2 =random.nextInt(360);
			cercle2 = new Ellipse2D.Double(xCercle2, yCercle2, taille, taille);
			g2.fill(cercle2);
			lesDessinsDeBases.add(cercle2);
		}

		// Intersection deuxieme rectangle et le cercle 2
		if (rectangle2.intersects(cercle2.getBounds2D())) {
			//System.out.println("intersection rec et cer ");
			xCercle2 =random.nextInt(460);
			yCercle2 =random.nextInt(360);
			cercle2 = new Ellipse2D.Double(xCercle2, yCercle2, taille, taille);
			g2.fill(cercle2);
			lesDessinsDeBases.add(cercle2);
		}

		if (rectangle2.intersects(cercle1.getBounds2D())) {
			xCercle1 =random.nextInt(460);
			yCercle1 =random.nextInt(360);
			cercle1 = new Ellipse2D.Double(xCercle1, yCercle1, taille, taille);
			g2.fill(cercle1);
			lesDessinsDeBases.add(cercle1);
		}

		lesDessinsDeBases.add(rectangle2);
		lesDessinsDeBases.add(cercle1);
		lesDessinsDeBases.add(cercle2);
		g2.fill(rectangle);g2.fill(rectangle2);g2.fill(cercle2);g2.fill(cercle1);
		// ces deux prochaines lignes sont faites pour eliminer les doublons
		// Créer une liste de contenu unique basée sur les éléments de ArrayList
		Set<Shape> mySet = new HashSet<Shape>(lesDessinsDeBases);
		// Créer une Nouvelle ArrayList à partir de Set
		listeDesDessinsDeBase = new ArrayList<Shape>(mySet);
		System.out.println("taille liste " + listeDesDessinsDeBase.size());

		//	for (Shape s : listeDesDessinsDeBase){
			//	System.out.println(s.getBounds());
		//	}

		int minx = 100000;
		for (int i =0 ; i<listeDesDessinsDeBase.size() ; i++){
			if (minx > listeDesDessinsDeBase.get(i).getBounds().x){
				minx = listeDesDessinsDeBase.get(i).getBounds().x;
			}
		}

		int maxx = 0;
		for (int i =0 ; i<listeDesDessinsDeBase.size() ; i++){
			if (maxx < listeDesDessinsDeBase.get(i).getBounds().x){
				maxx = listeDesDessinsDeBase.get(i).getBounds().x;
			}
		}

		int miny = 100000;
		for (int i =0 ; i<listeDesDessinsDeBase.size() ; i++){
			if (miny > listeDesDessinsDeBase.get(i).getBounds().y){
				miny = listeDesDessinsDeBase.get(i).getBounds().y;
			}
		}

		int maxy = 0;
		for (int i =0 ; i<listeDesDessinsDeBase.size() ; i++){
			if (maxy < listeDesDessinsDeBase.get(i).getBounds().y){
				maxy = listeDesDessinsDeBase.get(i).getBounds().y;
			}
		}

		Rectangle2D rsolve1 = new Rectangle2D.Double(0, 0, minx-2, 650);
		Rectangle2D rsolve2 = new Rectangle2D.Double(maxx +taille +2, 0, 800-maxx, 650);
		Rectangle2D rsolve4 = new Rectangle2D.Double(0, 0, 800, miny-2);
		Rectangle2D rsolve3 = new Rectangle2D.Double(0, maxy +taille +2, 800, 650-maxy);
		//g2.fill(rsolve1);
		//g2.fill(rsolve2);
		//g2.fill(rsolve3);
		//g2.fill(rsolve4);
	}

	public List<Shape> liste(){
		return listeDesDessinsDeBase;
	}

	// Methode pour voir si deux shapes s'intersectent ou pas
	public boolean estEnIntersection( Shape s1,Shape s2){
		if(s1.intersects(s2.getBounds()) ){
			return true;
		}
		return false;
	}

	public void paintCoveredPoints(Graphics2D g2) {}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		paintForms(g2);

		if(this.model.getSolve()){
			paintSolvedGameObject(g2);
		}
		paintBackground(g2);

		// s'il ya un objet a dessiner la methode paint est fait en motion
		if (startDrag != null && endDrag != null &&!(Controlleur.state.toString()=="initial") && !(Controlleur.state.toString()=="adjustement") && !(Controlleur.state.toString()=="redemension")  && !(Controlleur.state.toString()=="delete")) { // TUka treba da napravish ako ne e vo adjustement state da moze da se crta sredi go toa i plus sredi redimension na site !
	        g2.setPaint(Color.LIGHT_GRAY);
	       	ViewObject r = Controlleur.state.make();
	        r.draw(g2);
		}
	}

	// methode qui dessine les formes
	public void paintForms(Graphics2D g2) {
	      g2.setStroke(new BasicStroke(2));
	      g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.50f));
	      for (ViewObject s : GUI.objects) {
	        g2.setPaint(Color.RED);
	        s.draw(g2);
	      }
	 }

	// methode qui dessine les formes calculer par l ordi
	public void paintSolvedGameObject(Graphics2D g2) {
		 for(int i = 0 ; i < this.model.getRectangles().size(); i++ ) {
			 if(this.model.rectangles.get(i) != null) {
				 this.rectangles.add( new ViewRectangle( this.model.rectangles.get( i ) ) );
			 }
		 }
		 g2.setStroke(new BasicStroke(2));
	     g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.50f));

	     for (ViewObject s : this.rectangles) {
	     	g2.setPaint(Color.GREEN);
	     	s.draw(g2);
	     }
	}
}
	        

