package model;

import java.util.List;

import javafx.scene.paint.Color;

public class FireSquareTree extends FireSquare{

	public FireSquareTree(List<FireSquare> neighbors) {
		super(neighbors);
		// TODO Auto-generated constructor stub
	}

	public FireSquareTree(){
		myColor = Color.GREEN;
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

	@Override
	public Color getColor() {
		return myColor;
	}

}
