package view;

import controller.CellSocietyController;
import javafx.scene.control.Button;

public abstract class SimScreenButton extends Button{
	
	protected CellSocietyController myController;
	
	public SimScreenButton(String name, CellSocietyController csc){
		super(name);
		myController = csc;
		this.setOnAction(e -> {
			action();
		});
	}

	public abstract void action();
}
