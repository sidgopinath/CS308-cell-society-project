package model;

import java.util.List;

public class AgentSquareEmpty extends AgentSquare{

	public AgentSquareEmpty(List<AgentSquare> neighbors) {
		super(neighbors);
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

}
