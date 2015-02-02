package model;

import java.util.List;

import javafx.scene.paint.Color;

public class FireSquareTree extends FireSquare{

	public FireSquareTree(List<FireSquare> neighbors) {
		super(neighbors);
		myColor = Color.GREEN;
	}

	public FireSquareTree(){
		myColor = Color.GREEN;
	}
	@Override
	public FireSquare chechStatus() {
		for(FireSquare neighbor: myNeighbors){
			FireSquare checkedNeighbor = neighbor.checkNeighbor();
			if(checkedNeighbor != null){
				return checkedNeighbor;
			}
		}
		return new FireSquareTree();
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
