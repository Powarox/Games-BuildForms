package command;

import java.util.ArrayList;

public class CommandHandler {
	OperationCommand operation;
	ArrayList<OperationCommand> listeUndo;
	ArrayList<OperationCommand> listeRedo;

	public CommandHandler() {
		this.listeUndo = new ArrayList<OperationCommand>();
		this.listeRedo = new ArrayList<OperationCommand>();
	}

	public void handle(OperationCommand command) {
		listeUndo.add(command);
		listeUndo.get(listeUndo.size()-1).operate();
		listeRedo.clear();
	}

	public void undo() {
		if(!(listeUndo.size() == 0)) {
			listeUndo.get(listeUndo.size()-1).compensate();
			listeRedo.add(listeUndo.get(listeUndo.size()-1));
			listeUndo.remove(listeUndo.size()-1);
		}
	}

	//methode qui undo la derniere commande ajouter au redo liste avec la methode operate
	public void redo() {
		if(!(listeRedo.size() == 0)) {
			listeRedo.get(listeRedo.size()-1).operate();
			listeUndo.add(listeRedo.get(listeRedo.size()-1));
			listeRedo.remove(listeRedo.size()-1);
		}
	}
}
