package model.cells;

import java.util.List;

import javafx.scene.paint.Color;

/**
 * This square holds a living square for the Life simulation.
 * @author Sunjeev
 */

public class LifeCellAlive extends LifeCell{

	private Color myColor = Color.GREEN;
	
	public LifeCellAlive(List<LifeCell> neighbors) {
		super(neighbors);
	}

	public LifeCellAlive(){
	}
	
	@Override
	protected int returnCount() {
		return 1;
	}

	@Override
	protected LifeCell checkStatus(int alive) {
		if(alive == 2 || alive == 3){
			return this;
		}
		else return new LifeCellDead(myNeighbors);
		
	}

	@Override
	public Color getColor() {
		return myColor;
	}

	

}
