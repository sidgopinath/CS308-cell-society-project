package model;

import java.util.List;

public class FireSquareTree extends FireSquare{

	public FireSquareTree(List<FireSquare> neighbors) {
		super(neighbors);
		// TODO Auto-generated constructor stub
	}

	@Override
	public FireSquare chechStatus() {
		for(FireSquare neighbor: myNeighbors){
			if(neighbor.checkNeighbor() != null){
				return neighbor.checkNeighbor();
			}
		}
		return this;
	}

	@Override
	public FireSquare checkNeighbor() {
		return null;
	}

}
