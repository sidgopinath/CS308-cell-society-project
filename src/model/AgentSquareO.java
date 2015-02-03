package model;

import javafx.scene.paint.Color;

/**
 * This is a square that holds an "O agent" for Segregation
 * @author Sunjeev
 *
 */

public class AgentSquareO extends AgentSquare{

	Color myColor = Color.BLUE;
	
	public AgentSquareO(double mySatisfaction) {
		super(mySatisfaction);
	}

	public AgentSquareO(){
	}
	
	@Override
	protected int returnCount() {
		int sameCount = 0;
		for(AgentSquare neighbor: myNeighbors){
			sameCount += neighbor.getCountO();
		}
		return sameCount;
	}

	@Override
	protected int getCountX() {
		return 0;
	}

	@Override
	protected int getCountO() {
		return 1;
	}

	@Override
	protected boolean isEmpty() {
		return false;
	}

	@Override
	public Color getColor() {
		return myColor;
	}

}
