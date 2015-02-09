package model.cells;

import javafx.scene.paint.Color;

/**
 * This cell is an empty cell for the fire simulation
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
