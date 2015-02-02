package model;

import javafx.scene.paint.Color;


public class AgentSquareX extends AgentSquare{

	Color myColor = Color.RED;
	
	public AgentSquareX(double mySatisfaction) {
		super(mySatisfaction);
	}

	public AgentSquareX(){
	}
	
	@Override
	protected int returnCount() {
		int sameCount = 0;
		for(AgentSquare neighbor: myNeighbors){
			sameCount += neighbor.getCountX();
		}
		return sameCount;
	}

	@Override
	protected int getCountX() {
		return 1;
	}

	@Override
	protected int getCountO() {
		return 0;
	}

	@Override
	protected boolean isEmpty() {
		return false;
	}

	@Override
	public Color getColor() {
		return myColor;
	}

}
