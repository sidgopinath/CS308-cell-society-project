package model;

import java.util.List;

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
	
	protected abstract int returnCount();
	protected abstract LifeSquare checkStatus(int alive);
	
}
