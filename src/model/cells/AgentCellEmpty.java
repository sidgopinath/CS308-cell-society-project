package model.cells;

import javafx.scene.paint.Color;

/**
 * This is an empty square for the Segregation simulation
 * @author Sunjeev
 */

public class AgentCellEmpty extends AgentCell{

	Color myColor = Color.WHITE;
	
	public AgentCellEmpty(double mySatisfaction) {
		super(mySatisfaction);
		myPropertyMap.put("empty", (double) 1);
		myPropertyMap.put("satisfactionRate", mySatisfaction);
		myPropertyMap.put("satisfied", (double) 1);
		myPropertyMap.put("countO", (double) 0);
		myPropertyMap.put("countX", (double) 0);	
	}

	@Override
	protected int returnCount() {
		return 0;
	}

	@Override
	public Color getColor() {
		return myColor;	
	}

	@Override
	public Cell update() {
		return null;
	}
}
