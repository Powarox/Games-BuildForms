package view;

import model.Model;
import controlleur.Observer;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class GUI extends JFrame implements Observer{
    Model model;
    private JpanelJeu panel;
    public JpanelBoutons panelButtonclass;
    public JpanelScore table;

    public static ArrayList<ViewObject> objects = new ArrayList<ViewObject>();

    public GUI(){
        this(new Model());
    }

    public GUI(Model model){
        this.model = model;
        this.model.addObserver(this);
        panel = new JpanelJeu(this.model);
        table = new JpanelScore(model);
        panelButtonclass = new JpanelBoutons(this.model);
        // this.getContentPane().setBackground(Color.yellow);
        this.setLayout(new BorderLayout());
        this.add(panelButtonclass, BorderLayout.NORTH);
        this.add(panel, BorderLayout.CENTER);
        this.add(table, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(800,650);
        this.setVisible(true);
    }

    //retourne le Jpanel contenant l boutons
    public JpanelBoutons getButtonsPanel() {
		return panelButtonclass;
	}

	// ajoute listener au jeu
    public void addPanelListener(MouseListener m) {
		this.panel.addMouseListener(m);
	}

    public JComponent getPanel() {
	    return this.panel;
    }

    public static void addObject(ViewObject object) {
    	objects.add(object);
    }

	@Override
	public void update(Object obj) {
		repaint();
	}
}
