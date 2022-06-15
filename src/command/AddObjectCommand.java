package command;

import model.*;
import view.GUI;
import view.ViewObject;

public class AddObjectCommand implements OperationCommand{
	ViewObject object;
	Model model;

    // l'objet qui va etre ajoute et le model du jeu
	public AddObjectCommand(ViewObject object, Model model) {
		this.object = object;
		this.model = model;
	}

	// methode qui va mettre l'objet dans la liste des formes et sa vueComponent
	@Override
	public void operate() {
		GUI.objects.add(this.object);
		this.model.addForme(this.object.getObject());
	}

	// Enleve qui enleve l'objet dans les deux listes
	@Override
	public void compensate() {
		GUI.objects.remove(this.object);
		this.model.removeForm(this.object.getObject());
	}
}
