package model.cells;

import java.util.List;

import javafx.scene.paint.Color;

/**
 * This is a square that holds a square that is empty for the fire sim.
 * @author Sunjeev
 *
 */

public class FireCellEmpty extends FireCell{

	public FireCellEmpty(){
		myColor = Color.YELLOW;
	}
	@Override
	public FireCell checkStatus() {
		return this;
	}

	@Override
	public FireCell checkNeighbor() {
		return null;
	}

	@Override
	public Color getColor() {
		return myColor;
	}

	
}
