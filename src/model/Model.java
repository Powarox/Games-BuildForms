package model;

import java.util.ArrayList;
import playerStrategy.NaiveStrategy;
import playerStrategy.PlayerStrategy;

import controlleur.*;

public class Model implements Observable{
	public static final double PANEL_SURFACE = 200000;
	public boolean solve;
	public boolean isPainting = false;
	public PlayerStrategy kmeans;
	public NaiveStrategy nv;
    public ArrayList<Form> forms;
	public ArrayList<Point> initialPoints;
	public ArrayList<Observer> observers = new ArrayList<Observer>();
	public ArrayList<Rectangle> rectangles;

    public Model(){
    	forms = new ArrayList<Form>();
        initialPoints = new ArrayList<Point>();
        makePlayerStrategy();
    }

    public void setIsPainting(boolean b) {
    	this.isPainting = b;
    	notifyObserver(observers);
    }

    // methode pour verifier si le joueur a clique sur solve
    public boolean getSolve() {
    	return this.solve;
    }

    public void setSolve(boolean solve) {
    	this.solve = solve;
    	notifyObserver(observers);
    }

    public ArrayList<Rectangle> getRectangles(){
    	return this.rectangles;
    }

    // methode qui ajoute une forme a la liste des formes
    public void addForme(Form form){
        this.forms.add(form);
        this.notifyObserver(observers);
    }

	// methode qui enleve une forme a la liste des formes
    public void removeForm(Form form) {
    	this.forms.remove(form);
    	this.notifyObserver(observers);
    }

  	// retourne toutes les formes
    public ArrayList<Form> getForms(){
        return this.forms;
    }

	@Override
	public void addObserver(Observer obs) {
		this.observers.add(obs);
		
	}

	@Override
	public void removeObserver(Observer obs) {
		this.observers.remove(obs);
		
	}

	@Override
	public void notifyObserver(Object obj) {
		for(Observer obs: this.observers) {
			obs.update(obj);
		}
		
	}

	// methode qui creer la strategie du joueur
	public void makePlayerStrategy() {
		this.nv = new NaiveStrategy(this);
		this.nv.init();
		this.nv.solveGame();
		this.rectangles = nv.getRectangles();
	}

	public ArrayList<Point> getPoints() {
		return this.initialPoints;
	}

	// methode pour verifier si le joueur peut ou pas dessiner sachant quil peut dessiner que 4 formes
	public boolean isDrawingAllowed() {
		if(this.forms.size()<4){
			return true;
		}
		return false;
	}

	// retourne la somme totale des surfaces des formes
	public double getFormsSurface(ArrayList<Form> forms) {
    	int sum = 0;
	    for(Form form: forms) {
	    	sum += form.getSurface();
	    }
	    return sum;
    }

	 // retourne le score:  somme des surfaces des 4 formes divisée par la surface du panel.
	public double getScore() {
		return getFormsSurface(this.forms)/PANEL_SURFACE;
	}

	// retourne la strategie du joueur dans la resulution du jeu
	public PlayerStrategy getPlayerStrategy() {
		return this.nv;
	}
}
