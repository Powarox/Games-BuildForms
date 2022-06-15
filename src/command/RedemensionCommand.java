package command;

import model.*;
import view.GUI;
import view.ViewObject;

public class RedemensionCommand implements OperationCommand{
	ViewObject object;
	ViewObject redemensionObject;
	Model model;

	public RedemensionCommand(ViewObject object, ViewObject redemensionObject, Model model) {
		this.object = object;
		this.model = model;
		this.redemensionObject = redemensionObject;
	}

	// met la frome ajuster dans la liste des formes et enleve la version anterieure
	@Override
	public void operate() {
		GUI.objects.add(this.redemensionObject);
		this.model.addForme(this.redemensionObject.getObject());
		
		GUI.objects.remove(this.object);
		this.model.removeForm(this.object.getObject());
	}

	// enleve la frome ajuster dans la liste des formes et enleve la version anterieure
	@Override
	public void compensate() {
		GUI.objects.add(this.object);
		this.model.addForme(this.object.getObject());
		
		GUI.objects.remove(this.redemensionObject);
		this.model.removeForm(this.redemensionObject.getObject());
	}
}
