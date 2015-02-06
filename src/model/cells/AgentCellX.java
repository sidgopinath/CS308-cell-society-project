package model.cells;

import javafx.scene.paint.Color;

/**
 * This is a square that holds an "X agent" for Segregation
 * @author Sunjeev
 *
 */

public class AgentCellX extends AgentCell{

	Color myColor = Color.RED;
	
	public AgentCellX(double mySatisfaction) {
		super(mySatisfaction);
	}

	public AgentCellX(){
	}
	
	@Override
	protected int returnCount() {
		int sameCount = 0;
		for(Cell neighbor: myNeighbors){
			sameCount += ((AgentCell) neighbor).getCountX();
		}
		return sameCount;
	}

	@Override
	protected int getCountX() {
		return 1;
	}

	@Override
	protected int getCountO() {
		return 0;
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
