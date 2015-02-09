package model.cells;

import javafx.scene.paint.Color;

/**
 * This is a square that holds a square with a tree for the fire sim.
 * @author Sunjeev and Sid
 */

public class FireCellTree extends FireCell {

	private Color myColor = Color.GREEN;

	public FireCellTree() {
		myPropertyMap.put("probCatch", (double) -1);
	}

	@Override
	public FireCell checkStatus() {
		for (Cell neighbor : myNeighbors) {
			Integer probCatch = neighbor.viewProperties().get("probCatch")
					.intValue();
			if (calculateProbability(probCatch)) {
				return new FireCellBurning(probCatch);
			}
		}
		return this;
	}

	private boolean calculateProbability(Integer probCatch) {
		if (probCatch != null) {
			return myRandom.nextInt(100) <= probCatch;
		} else {
			return false;
		}
	}

	@Override
	public Color getColor() {
		return myColor;
	}

}
