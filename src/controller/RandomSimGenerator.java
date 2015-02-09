package controller;

import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * This generator creates a fully random grid
 * Each cell's state is random
 * @author Sid
 *
 */

public class RandomSimGenerator {

	private Integer[][] myGrid;
	private HashMap<String, String> myParameters = new HashMap<String, String>();
	private String mySimName;
	private ResourceBundle myProperties;
	private Random myRandom = new Random();

	/**
	 * Constructor for the random generator
	 * Puts default values into paramMap for later use
	 * Constructor could be refactored out but decided against it due to time constraints
	 * @param gridWidth
	 * @param gridHeight
	 * @param simName
	 */
	public RandomSimGenerator(int gridWidth, int gridHeight, String simName) {
		myProperties = ResourceBundle.getBundle("resources/resources");
		myParameters.put("simName", simName);
		myParameters.put("gridWidth", gridWidth + "");
		myParameters.put("gridHeight", gridHeight + "");
		mySimName = simName;
		myGrid = new Integer[gridHeight][gridWidth];
		createGrid(myGrid);
	}

	public Integer[][] getGrid() {
		return myGrid;
	}
	
	public HashMap<String, String> getParameters(){
		return myParameters;
	}

	/**
	 * Create grid based on purely random values
	 * Grid created dependingon type of simulation
	 * @param grid
	 */
	private void createGrid(Integer[][] grid) {
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