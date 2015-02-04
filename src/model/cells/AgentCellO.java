package model.cells;

import javafx.scene.paint.Color;

/**
 * This is a square that holds an "O agent" for Segregation
 * @author Sunjeev
 *
 */

public class AgentCellO extends AgentCell{

	Color myColor = Color.BLUE;
	
	public AgentCellO(double mySatisfaction) {
		super(mySatisfaction);
	}

	public AgentCellO(){
	}
	
	@Override
	protected int returnCount() {
		int sameCount = 0;
		for(AgentCell neighbor: myNeighbors){
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
	public boolean isEmpty() {
		return false;
	}

	@Override
	public Color getColor() {
		return myColor;
	}

}
