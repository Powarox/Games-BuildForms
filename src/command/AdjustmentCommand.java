package command;

import model.*;
import view.GUI;
import view.ViewObject;

public class AdjustmentCommand implements OperationCommand{
	ViewObject object;
	ViewObject adjustObject;
	Model model;

	public AdjustmentCommand(ViewObject object, ViewObject adjustObject, Model model) {
		this.object = object;
		this.model = model;
		this.adjustObject = adjustObject;
	}

	// met la forme ajuster dans la liste des formes et enleve la version anterieure
	@Override
	public void operate() {
		GUI.objects.add(this.adjustObject);
		this.model.addForme(this.adjustObject.getObject());
		GUI.objects.remove(this.object);
		this.model.removeForm(this.object.getObject());
	}

	//enleve la forme ajuster dans la liste des formes et met la version anterieure
	@Override
	public void compensate() {
		GUI.objects.add(this.object);
		this.model.addForme(this.object.getObject());
		GUI.objects.remove(this.adjustObject);
		this.model.removeForm(this.adjustObject.getObject());
	}
}
