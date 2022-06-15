package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Model;
import state.InitialState;
import controlleur.Controlleur;
import controlleur.Observer;

public class JpanelBoutons extends JPanel implements Observer{
	private JButton cercle;
    private JButton rectangle;
    private JButton ajustement;
    private JButton redimensionner;
    private JButton delete;
    private JButton undo;
    private JButton redo;
    private JButton solve;
    private Model model;

    public JpanelBoutons(Model model) {
    	this.setBackground(Color.RED);
    	this.model = model;
    	this.model.addObserver(this);
    	cercle = createButton("cercle");
        rectangle = createButton("rectangle");
        ajustement = createButton("adjustment");
        redimensionner = createButton("redemension");
        delete = createButton("delete");
        undo = createButton("undo");
        redo = createButton("redo");
        solve = createButton("solve");
        this.setLayout(new FlowLayout());
        this.add(cercle);
        this.add(rectangle);
        this.add(ajustement);
        this.add(redimensionner);
        this.add(delete);
        this.add(undo);
        this.add(redo);
        this.add(solve);
	}

	public void addListener(ActionListener a) {
		this.cercle.addActionListener(a);
		this.rectangle.addActionListener(a);
		this.delete.addActionListener(a);
		this.redimensionner.addActionListener(a);
		this.ajustement.addActionListener(a);
		this.undo.addActionListener(a);
		this.redo.addActionListener(a);
		this.solve.addActionListener(a);
	}

	//La méthode  qui vérifie d'abord si isDrawingAllowed afin qu'il puisse activer ou désactiver le bouton cercle et rectangle.
	@Override
	public void update(Object obj) {
		if(!this.model.isDrawingAllowed()) {
			this.rectangle.setEnabled(false);
			this.cercle.setEnabled(false);
			Controlleur.state.setState(new InitialState());
		}
		else {
			this.rectangle.setEnabled(true);
			this.cercle.setEnabled(true);
		}
	}

	// methode qui cree les boutons
	public JButton createButton(String s) {
		JButton button = new JButton(s);
        button.setSize(25,25);
        button.setBackground(Color.RED);
        button.setForeground(Color.white);        
		return button;
	}
}
