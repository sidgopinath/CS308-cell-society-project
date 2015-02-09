package model.cells;

import java.util.List;

import javafx.scene.paint.Color;

/**
 * This is a square that holds a square with a tree for the fire sim.
 * @author Sunjeev
 */

public class FireCellTree extends FireCell{

	public FireCellTree(List<FireCell> neighbors) {
		super(neighbors);
		myColor = Color.GREEN;
	}

	public FireCellTree(){
		myColor = Color.GREEN;
	}
	@Override
	public FireCell checkStatus() {
		for(FireCell neighbor: myNeighbors){
			FireCell checkedNeighbor = neighbor.checkNeighbor();
			if(checkedNeighbor != null){
				return checkedNeighbor;
			}
		}
		return new FireCellTree();
	}

	@Override
	public FireCell checkNeighbor() {
		return null;
	}

	@Override
	public Color getColor() {
		return myColor;
	}

}
