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
		myPropertyMap.put("empty", (double) 0);
		myPropertyMap.put("satisfactionRate", mySatisfaction);
		myPropertyMap.put("countX", (double) 0);
		myPropertyMap.put("countO", (double) 1);	
	}

	/**
	 * Possible future refactoring spot
	 * Contains very similar code to AgentCellX
	 */
	@Override
	protected int returnCount() {
		int sameCount = 0;
		for(Cell neighbor: myNeighbors){
			sameCount += neighbor.viewProperties().get("countO");
		}
		return sameCount;
	}

	@Override
	public Color getColor() {
		return myColor;
	}

	@Override
	public Cell update() {
		if (this.isSatisfied()) {
			myPropertyMap.put("satisfied", (double) 1);
		} else {
			myPropertyMap.put("satisfied", (double) 0);
		}
		return null;
	}
}
