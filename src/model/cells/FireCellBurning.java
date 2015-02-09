package model.cells;

import javafx.scene.paint.Color;

/**
 * This is a square that holds a square that is on fire for the fire sim.
 * @author Sunjeev and Sid
 *
 */

public class FireCellBurning extends FireCell{
	

	private Color myColor = Color.RED;
	
	public FireCellBurning(Integer probCatch){
		myPropertyMap.put("probCatch", (double) probCatch);
	}
	
	@Override
	public FireCell checkStatus() {
		return new FireCellEmpty();
	}
	@Override
	public Color getColor() {
		return myColor;
	}

	
}
