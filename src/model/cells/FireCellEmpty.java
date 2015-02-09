package model.cells;

import javafx.scene.paint.Color;

/**
 * This is a square that holds a square that is empty for the fire sim.
 * @author Sunjeev and Sid
 *
 */

public class FireCellEmpty extends FireCell{

	private Color myColor = Color.YELLOW;
	
	public FireCellEmpty(){
		myPropertyMap.put("probCatch", (double) -1);
	}
	@Override
	public FireCell checkStatus() {
		return this;
	}

	@Override
	public Color getColor() {
		return myColor;
	}	
}
