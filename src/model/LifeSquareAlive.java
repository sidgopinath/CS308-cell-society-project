package model;

import java.util.List;

import javafx.scene.paint.Color;

/**
 * This square holds a living square for the Life simulation.
 * @author Sunjeev
 */

public class LifeSquareAlive extends LifeSquare{

	private Color myColor = Color.GREEN;
	
	public LifeSquareAlive(List<LifeSquare> neighbors) {
		super(neighbors);
	}

	public LifeSquareAlive(){
	}
	
	@Override
	protected int returnCount() {
		return 1;
	}

	@Override
	protected LifeSquare checkStatus(int alive) {
		if(alive == 2 || alive == 3){
			return this;
		}
		else return new LifeSquareDead(myNeighbors);
		
	}

	@Override
	protected Color getColor() {
		return myColor;
	}

	

}
