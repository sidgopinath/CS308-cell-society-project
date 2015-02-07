package model.cells;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

/**
 * This is the square superclass for Fire simulation.
 * It holds the properties (probCatch specifically) for the Fire sim.
 * @author Sunjeev
 *
 */

public abstract class FireCell extends Cell{

	protected List<FireCell> myNeighbors;
	protected Color myColor;
	
	/**
	 * @param neighbors is a list of neighbors passed in by its simulation class
	 */
	public FireCell(List<FireCell> neighbors){
		myNeighbors = neighbors;
	}
	
	public FireCell(){
	}
	public void setNeighbors(ArrayList<FireCell> neighbors){
		myNeighbors = neighbors;
	}
	
	/**
	 * @return the current state of the tree after checking with neighbors determined by making a new SquareFire 
	 * class
	 */
	public FireCell update(){
		return this.chechStatus();
	}
	
	public abstract FireCell chechStatus();
	public abstract FireCell checkNeighbor();
	public abstract Color getColor();
}
