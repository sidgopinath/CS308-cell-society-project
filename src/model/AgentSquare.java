package model;

import java.util.List;

import javafx.scene.paint.Color;

public abstract class AgentSquare {

	private static final double NUM_NEIGHBORS = 8;
	private double mySatisfaction;
	protected List<AgentSquare> myNeighbors;

	public AgentSquare(double satisfaction){
		mySatisfaction = satisfaction;
	}

	public AgentSquare(){
	}
	
	public void setNeighbors(List<AgentSquare> neighbors){
		myNeighbors = neighbors;
	}
	
	
	public boolean isSatisfied(){
		double sameCount = this.returnCount();
		return ((sameCount / NUM_NEIGHBORS) >= mySatisfaction);
	}
	
	public abstract Color getColor();
	
	protected abstract int returnCount();
	protected abstract int getCountX();
	protected abstract int getCountO();
	protected abstract boolean isEmpty();
}
