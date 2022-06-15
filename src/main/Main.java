package main;

import model.Model;
import controlleur.Controlleur;
import view.GUI;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {
    JPanel jpanel;

    private Main() {
        setTitle("Patron-Conception");
        setSize(650, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        jpanel = new JPanel();
        jpanel.setBackground(Color.red);
        jpanel.setLayout(new GridBagLayout());
        initButton();
        setContentPane(jpanel);
    }

    private void initButton() {
		JButton button = new JButton("Nouveau Jeu");
		jpanel.add(button);
		button.setVisible(true);
		button.setBackground(Color.white);
		button.setForeground(Color.red);
		button.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e) {
				newGame();
			}
		});
	}

    private void newGame() {
    	Model model = new Model();
		GUI game = new GUI(model);
		Controlleur controlleur = new Controlleur(model, game);
        dispose();
    }

    public static void main(String[] args) {
        new Main().setVisible(true);
    }
}

