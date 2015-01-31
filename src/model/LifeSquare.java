package model;

import java.util.List;

public abstract class LifeSquare {

	private List<LifeSquare> myNeighbors;

	public LifeSquare(List<LifeSquare> neighbors){
		myNeighbors = neighbors;
	}
	
	public LifeSquare update(){
		int liveCount = 0;
		for(LifeSquare neighbor: myNeighbors){
			liveCount += neighbor.returnCount();
		}
		//if alive...
		if(this.getState() == 1){
			if(liveCount < 2){
				//return dead cell
				return null;
			}
			else if(liveCount <= 3){
				//return live cell
				return null;
			}
			else if(liveCount > 3){
				//return dead cell
				return null;
			}
		}
		//if dead...
		else{
			if(liveCount == 3){
				//return live cell
				return null;
			}
		}
		
		return null;
	}
	
	protected abstract int getState();
	protected abstract int returnCount();
	
}
