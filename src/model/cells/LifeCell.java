package model.cells;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

/**
 * This is the square superclass for the life simulation
 * It holds relevant information about the square
 * This includes life status and neighbors
 * @author Sunjeev
 *
 */

public abstract class LifeCell extends Cell{


	public LifeCell(){
	}
	
	public LifeCell update(){
		int liveCount = 0;
		for(LifeCell neighbor: myNeighbors){
			liveCount += neighbor.returnCount();
		}
		return this.checkStatus(liveCount);
	}
	
	public void setNeighbors(ArrayList<LifeCell> neighbors){
		myNeighbors = neighbors;
	}
	
	protected abstract int returnCount();
	protected abstract LifeCell checkStatus(int alive);
	
}
