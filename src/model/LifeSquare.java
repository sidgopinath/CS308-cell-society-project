package model;

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

public abstract class LifeSquare {

	protected List<LifeSquare> myNeighbors;

	public LifeSquare(List<LifeSquare> neighbors){
		myNeighbors = neighbors;
	}
	
	public LifeSquare(){
	}
	
	public LifeSquare update(){
		int liveCount = 0;
		for(LifeSquare neighbor: myNeighbors){
			liveCount += neighbor.returnCount();
		}
		return this.checkStatus(liveCount);
	}
	
	public void setNeighbors(ArrayList<LifeSquare> neighbors){
		myNeighbors = neighbors;
	}
	
	protected abstract int returnCount();
	protected abstract LifeSquare checkStatus(int alive);
	protected abstract Color getColor();
	
}
