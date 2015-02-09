package model.cells;

import javafx.scene.paint.Color;

/**
 * This is a square that holds an "X agent" for Segregation
 * 
 * @author Sunjeev
 *
 */

public class AgentCellX extends AgentCell {

	Color myColor = Color.RED;

	public AgentCellX(double mySatisfaction) {
		super(mySatisfaction);
		myPropertyMap.put("empty", (double) 0);
		myPropertyMap.put("satisfactionRate", mySatisfaction);
		myPropertyMap.put("countO", (double) 0);
		myPropertyMap.put("countX", (double) 1);		
	}

	@Override
	protected int returnCount() {
		int sameCount = 0;
		for (Cell neighbor : myNeighbors) {
			sameCount += neighbor.viewProperties().get("countX");
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