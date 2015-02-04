package model.cells;

import java.util.List;

import javafx.scene.paint.Color;

/**
 * This is the AgentSquare superclass.
 * It holds the properties for the Segregation simulation
 * @author Sunjeev
 *
 */

public abstract class AgentCell {

	private static final double NUM_NEIGHBORS = 8;
	private double mySatisfaction;
	protected List<AgentCell> myNeighbors;

	public AgentCell(double satisfaction){
		mySatisfaction = satisfaction;
	}

	public AgentCell(){
	}
	
	public void setNeighbors(List<AgentCell> neighbors){
		myNeighbors = neighbors;
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
