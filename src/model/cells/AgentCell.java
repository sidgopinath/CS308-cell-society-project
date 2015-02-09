package model.cells;


/**
 * This is the AgentSquare superclass.
 * It holds the properties for the Segregation simulation
 * @author Sunjeev
 *
 */

public abstract class AgentCell extends Cell{

	private double mySatisfaction;

	public AgentCell(double satisfaction){
		mySatisfaction = satisfaction;
	}

	public boolean isSatisfied(){
		double sameCount = this.returnCount();
		return ((sameCount / myNeighbors.size()) >= mySatisfaction);
	}
	
	protected abstract int returnCount();
}
