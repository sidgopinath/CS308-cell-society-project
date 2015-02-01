package model;

import java.util.List;

public abstract class AgentSquare {

	protected static final int SATISFACTION = 30;
	protected List<AgentSquare> myNeighbors;

	public AgentSquare(List<AgentSquare> neighbors){
		myNeighbors = neighbors;
	}
	
	public boolean isSatisfied(){
		int sameCount = this.returnCount();
		
		if(sameCount / myNeighbors.size() >= SATISFACTION){
			return true;
		}
		else return false;
	}
	
	protected abstract int returnCount();
	protected abstract int getCountX();
	protected abstract int getCountO();
}
