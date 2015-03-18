// This entire file is part of my masterpiece.
// Sidharth Gopinath (sdg23)

package controller;

import java.util.HashMap;

/**
 * This generator creates a random grid
 * Based on probabilities that it is passed in a map
 * Each cell's state is generated based on a probability
 * @author Sid
 *
 */

public class ProbabilitySimGenerator extends SimGenerator {

	private HashMap<Integer, Integer> myProbabilities;

	/**
	 * Sets up grid generator
	 * Puts basic values into parameter map
	 * @param gridHeight
	 * @param gridWidth
	 * @param simName
	 * @param probabilities
	 */
	public ProbabilitySimGenerator(int gridHeight, int gridWidth,
			String simName, HashMap<Integer, Integer> probabilities) {
		super(gridWidth, gridWidth, simName, probabilities, "probability");
		myProbabilities = probabilities;
		createGrid(myGrid);

	}

	/**
	 * Iterates through grid array
	 * Calls generate cell value on each cell
	 * @param grid
	 */
	protected void createGrid(Integer[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				generateCellValue(i, j);
			}
		}
	}

	/**
	 * Checks a random number against a cell state probability
	 * @param i
	 * @param j
	 */
	private void generateCellValue(int i, int j) {
		Integer random = myRandom.nextInt(100);
		Integer currentCheck = 0;
		while (currentCheck < 100) {
			for (int key : myProbabilities.keySet()) {
				if (random <= (currentCheck + myProbabilities.get(key))) {
					myGrid[i][j] = key;
					currentCheck = 100;
					break;
				}
				currentCheck += myProbabilities.get(key);
			}
		}
	}
}