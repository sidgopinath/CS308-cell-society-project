package model.cells;

import java.util.List;

import javafx.scene.paint.Color;

/**
 * This is the AgentSquare superclass.
 * It holds the properties for the Segregation simulation
 * @author Sunjeev
 *
 */

public abstract class AgentCell extends Cell{

	private static final double NUM_NEIGHBORS = 8;
	private double mySatisfaction;
	

	public AgentCell(double satisfaction){
		super();
		mySatisfaction = satisfaction;
	}

	public AgentCell(){
	}
	
	
	public boolean isSatisfied(){
		double sameCount = this.returnCount();
		return ((sameCount / NUM_NEIGHBORS) >= mySatisfaction);
	}
	
	public abstract Color getColor();
	
	protected abstract int returnCount();
	protected abstract int getCountX();
	protected abstract int getCountO();
	public abstract boolean isEmpty();
}
