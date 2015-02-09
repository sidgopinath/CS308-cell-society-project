package model.cells;


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
		mySatisfaction = satisfaction;
	}

	
	public boolean isSatisfied(){
		double sameCount = this.returnCount();
		return ((sameCount / NUM_NEIGHBORS) >= mySatisfaction);
	}
	
	protected abstract int returnCount();
}
