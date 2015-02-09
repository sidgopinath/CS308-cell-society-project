package model.cells;

import java.util.Random;


/**
 * This is the square superclass for Fire simulation.
 * It holds the method to update each cell
 * It also holds the method to calculate the probability that a cell will catch fire
 * @author Sunjeev and Sid
 *
 */

public abstract class FireCell extends Cell{
	
	protected Random myRandom;
	
	public FireCell(){
		myRandom = new Random();
	}
	
	/**
	 * @return the current state of the tree after checking with neighbors determined by making a new SquareFire 
	 * class
	 */
	public FireCell update(){
		return this.checkStatus();
	}
	
	public boolean calculateProbability(Cell cell) {
		if(cell.viewProperties().get("probCatch") != null){
			return myRandom.nextInt(100) <= cell.viewProperties().get("probCatch");	
		}
		return false;		
	}
	
	protected abstract FireCell checkStatus();
}
