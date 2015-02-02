package model;

import javafx.scene.paint.Color;


public class AgentSquareO extends AgentSquare{

	Color myColor = Color.BLUE;
	
	public AgentSquareO(int satisfaction) {
		super(satisfaction);
	}

	public AgentSquareO(){
	}
	
	@Override
	protected int returnCount() {
		int sameCount = 0;
		for(AgentSquare neighbor: myNeighbors){
			sameCount += neighbor.getCountO();
		}
		return sameCount;
	}

	@Override
	protected int getCountX() {
		return 0;
	}

	@Override
	protected int getCountO() {
		return 1;
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
