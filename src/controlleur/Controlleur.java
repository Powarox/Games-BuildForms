package controlleur;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import java.util.ArrayList;

import command.CommandHandler;
import model.Model;
import model.Point;
import state.AdjustmentsState;
import state.CercleState;
import state.CurrentState;
import state.DeleteState;
import state.InitialState;
import state.RectangularState;
import state.RedemensionState;
import view.*;

public class Controlleur implements Observable{
	GUI gameView;
	Model model;
	public ArrayList<Observer> observers;
	public static CurrentState state = new CurrentState();
	public static CommandHandler handler = new CommandHandler();

	public Controlleur(Model model, GUI game) {
		this.model = model;
     	this.gameView = game;
		addPanelListeners();
		addButtonListeners();
	}
	
	public void addPanelListeners() {
		JpanelJeu jpanelJeu = (JpanelJeu) this.gameView.getPanel();
		jpanelJeu.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	            JpanelJeu.startDrag = new Point(e.getX(), e.getY());
	            JpanelJeu.endDrag = JpanelJeu.startDrag;
	            Point p = new Point(e.getX(), e.getY());
				//System.out.println("point e ,X = " + p.getX() + "point e Y = " + p.getY());
	            Controlleur.state.mousePressed(p);
	        }

	        public void mouseReleased(MouseEvent e) {
	        	Controlleur.state.mouseReleased( JpanelJeu.startDrag, new Point( e.getX(), e.getY()));
	        	JpanelJeu.startDrag= null;
	     	   	JpanelJeu.endDrag = null;
	     	   	model.setIsPainting(true);
	        }
		});

		jpanelJeu.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
	       	Point p = new Point(e.getX(), e.getY());
	       	Controlleur.state.mouseDragged(p);
			for (Shape s : jpanelJeu.listeDesDessinsDeBase){
				if (s.getBounds().contains(p.getX(),p.getY())){
					//model.isDrawingAllowed() = false;
					Controlleur.handler.undo();
					// model.setIsPainting(false);
				}
			}
	       	model.setIsPainting(true);
	        }
		});
	}

	public void addButtonListeners() {
		JpanelBoutons jpanelBoutons = (JpanelBoutons) this.gameView.getButtonsPanel();
		jpanelBoutons.addListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String string = (String) e.getActionCommand();
				switch(string){
				 case "cercle":
					 Controlleur.state.setState(new CercleState(model));
					 break;
				 case "rectangle":
					 Controlleur.state.setState(new RectangularState(model));
					 break;
				 case "Ajustement":
					 Controlleur.state.setState(new AdjustmentsState(model));
					 break;
				 case "Redimensionner":
					 Controlleur.state.setState(new RedemensionState(model));
					 break;
				 case "delete":
					 Controlleur.state.setState(new DeleteState(model));
					 break;
				 case "undo":
					 Controlleur.handler.undo();
					 break;
				 case "redo":
					 Controlleur.handler.redo();
					 break;
				 case "solve":
					 model.setSolve(true);
					default:
						 Controlleur.state.setState(new InitialState());
				}
			}
		}); 
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
}
