package model.cells;

import java.util.List;

import javafx.scene.paint.Color;

/**
 * This square holds a dead square for the Life simulation.
 * @author Sunjeev
 */

public class LifeCellDead extends LifeCell{

	private Color myColor = Color.WHITE;
	
	public LifeCellDead(List<LifeCell> neighbors) {
		super(neighbors);
	}

	public LifeCellDead() {
	}

	@Override
	protected int returnCount() {
		return 0;
	}

	@Override
	protected LifeCell checkStatus(int alive) {
		if(alive == 3){
			return new LifeCellAlive(myNeighbors);
		}
		else return this;
	}

	@Override
	public Color getColor() {
		return myColor;
	}

}
