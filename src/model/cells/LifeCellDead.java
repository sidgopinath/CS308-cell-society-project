package model.cells;


import javafx.scene.paint.Color;

/**
 * This square holds a dead square for the Life simulation.
 * @author Sunjeev
 */

public class LifeCellDead extends LifeCell{

	private Color myColor = Color.WHITE;
	
	public LifeCellDead() {
	    myPropertyMap.put(returnCount, (double) 0);
	}

	@Override
	protected LifeCell checkStatus(int alive) {
		if(alive == 3){
			return new LifeCellAlive();
		}
		else return this;
	}

	@Override
	public Color getColor() {
		return myColor;
	}


}
