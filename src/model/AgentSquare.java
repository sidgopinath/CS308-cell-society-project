package model;

import java.util.List;

import javafx.scene.paint.Color;

public abstract class AgentSquare {

	private int mySatisfaction;
	protected List<AgentSquare> myNeighbors;

	public AgentSquare(int satisfaction){
		mySatisfaction = satisfaction;
	}

	public AgentSquare(){
	}
	
	public void setNeighbors(List<AgentSquare> neighbors){
		myNeighbors = neighbors;
	}
	
	
	public boolean isSatisfied(){
		int sameCount = this.returnCount();
		return (sameCount / myNeighbors.size() >= mySatisfaction);
	}
	
	public abstract Color getColor();
	
	protected abstract int returnCount();
	protected abstract int getCountX();
	protected abstract int getCountO();
	protected abstract boolean isEmpty();
}
