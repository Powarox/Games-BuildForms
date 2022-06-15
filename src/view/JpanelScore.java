package view;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import model.*;
import controlleur.Observer;

public class JpanelScore extends JPanel implements Observer{
	public AbstractTableModel table;
	public Model model;
	JLabel scoreLabel;
	JLabel computerScoreLabel;

	public JpanelScore(Model model) {
		this.model = model;
		model.addObserver(this);
		this.table= new TableModelSurface(model);
		JTable table = new JTable((TableModel) this.table);
		table.setShowGrid(false);
		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setPreferredSize(new Dimension(200,120));
		add(scrollpane);
		this.setBackground(Color.RED);
		scoreLabel = new JLabel("Surface:");
		scoreLabel.setPreferredSize(new Dimension(200, 30));
		scoreLabel.setVisible(true);
		add(scoreLabel);
		computerScoreLabel = new JLabel("Surface resolue:");
		scoreLabel.setPreferredSize(new Dimension(200, 30));
		scoreLabel.setVisible(true);
		add(computerScoreLabel);
		setVisible(true);
	}

	//Met à jour le score chaque fois qu'un formulaire a été ajouté ou supprimé.
	//Vérifiez si l'utilisateur a cliqué sur le bouton de résolution ou non pour qu'il puisse afficher le score réalisé par l'ordinateur.
	@Override
	public void update(Object obj) {
		this.scoreLabel.setText("Surface:"+ this.model.getScore());
		if(this.model.getSolve()){
			this.computerScoreLabel.setText("Surface resolue:"+ this.model.getPlayerStrategy().getScore());
		}
		this.table.fireTableDataChanged();
	}
}
