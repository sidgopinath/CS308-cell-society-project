package model;

import javafx.scene.paint.Color;


public class AgentSquareEmpty extends AgentSquare{

	Color myColor = Color.WHITE;
	
	public AgentSquareEmpty(double mySatisfaction) {
		super(mySatisfaction);
	}

	public AgentSquareEmpty(){
	}
	
	@Override
	public boolean isSatisfied(){
		return true;
	}
	
	@Override
	protected int returnCount() {
		return 0;
	}

	@Override
	protected int getCountX() {
		return 0;
	}

	@Override
	protected int getCountO() {
		return 0;
	}

	@Override
	protected boolean isEmpty() {
		return true;
	}

	@Override
	public Color getColor() {
		return myColor;
		
	}

	
}
