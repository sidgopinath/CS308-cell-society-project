package model;

import java.util.List;

public class AgentSquareEmpty extends AgentSquare{

	public AgentSquareEmpty(List<AgentSquare> neighbors) {
		super(neighbors);
	}

	public AgentSquareEmpty(){
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

	
}
