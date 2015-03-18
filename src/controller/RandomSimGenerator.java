// This entire file is part of my masterpiece.
// Sidharth Gopinath (sdg23)

package controller;

/**
 * This generator creates a fully random grid
 * Each cell's state is random
 * @author Sid
 *
 */

public class RandomSimGenerator extends SimGenerator {

	/**
	 * Constructor for the random generator
	 * Puts default values into paramMap for later use
	 * Constructor could be refactored out but decided against it due to time constraints
	 * @param gridWidth
	 * @param gridHeight
	 * @param simName
	 */
	public RandomSimGenerator(int gridWidth, int gridHeight, String simName) {
		super(gridHeight, gridHeight, simName, null, "random");
		createGrid(myGrid);
	}

	/**
	 * Create grid based on purely random values
	 * Grid created depending on type of simulation
	 * @param grid
	 */
	protected void createGrid(Integer[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (mySimName.equals(myProperties
						.getObject("fire_simulation_name"))) {
					grid[i][j] = myRandom.nextInt(3);
				} else if (mySimName.equals(myProperties
						.getObject("segregation_simulation_name"))) {
					grid[i][j] = myRandom.nextInt(3);
				} else if (mySimName.equals(myProperties
						.getObject("life_simulation_name"))) {
					grid[i][j] = myRandom.nextInt(2);
				} else if (mySimName.equals(myProperties
						.getObject("predator_simulation_name"))) {
					grid[i][j] = myRandom.nextInt(3);
				}
			}
		}
	}
}