package model;

import java.util.List;

public class AgentSquareX extends AgentSquare{

	public AgentSquareX(List<AgentSquare> neighbors) {
		super(neighbors);
		// TODO Auto-generated constructor stub
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

}
