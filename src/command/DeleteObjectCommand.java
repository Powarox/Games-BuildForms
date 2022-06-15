package command;

import model.Model;
import view.GUI;
import view.ViewObject;

public class DeleteObjectCommand implements OperationCommand {
	ViewObject object;
	Model model;

	public DeleteObjectCommand(ViewObject object, Model model) {
		this.object = object;
		this.model = model;
	}

	//enleve forme dans la liste de formes du jeu
	@Override
	public void operate() {
		this.model.removeForm(this.object.getObject());
		GUI.objects.remove(this.object);
	}

	//ajoute  forme dans la liste de formes du jeu
	@Override
	public void compensate() {
		this.model.addForme(this.object.getObject());
		GUI.objects.add(this.object);
	}
}

