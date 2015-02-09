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

	protected Color myColor;
	/**
	 * @return the current state of the tree after checking with neighbors determined by making a new SquareFire 
	 * class
	 */
	public FireCell update(){
		return this.checkStatus();
	}
	
	public abstract FireCell checkStatus();
	public abstract FireCell checkNeighbor();
}
