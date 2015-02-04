package model.cells;

import javafx.scene.paint.Color;

/**
 * This is an empty square for the Segregation simulation
 * @author Sunjeev
 *
 */

public class AgentCellEmpty extends AgentCell{

	Color myColor = Color.WHITE;
	
	public AgentCellEmpty(double mySatisfaction) {
		super(mySatisfaction);
	}

	public AgentCellEmpty(){
	}
	
	@Override
	public boolean isSatisfied(){
		return true;
	}
	
	@Override
	protected int returnCount() {
		return 0;
	}

	@Override
	protected int getCountX() {
		return 0;
	}

	@Override
	protected int getCountO() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public Color getColor() {
		return myColor;
		
	}

	
}
