package model;

import java.util.List;

public class AgentSquareO extends AgentSquare{

	public AgentSquareO(List<AgentSquare> neighbors) {
		super(neighbors);
		// TODO Auto-generated constructor stub
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

}
