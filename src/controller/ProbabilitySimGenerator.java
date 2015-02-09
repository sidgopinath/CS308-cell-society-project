package controller;

import java.util.HashMap;
import java.util.Random;

public class ProbabilitySimGenerator {

	private Integer[][] myGrid;
	private HashMap<String, String> myParameters = new HashMap<String, String>();
	private Random myRandom = new Random();
	private HashMap<Integer, Integer> myProbabilities;

	public ProbabilitySimGenerator(int gridHeight, int gridWidth,
			String simName, HashMap<Integer, Integer> probabilities) {
		myParameters.put("simName", simName);
		myParameters.put("gridWidth", gridWidth + "");
		myParameters.put("gridHeight", gridHeight + "");
		myGrid = new Integer[gridHeight][gridWidth];
		

		// for testing
		 myProbabilities = new HashMap<Integer, Integer>();
		 myProbabilities.put(0, 10);
		 myProbabilities.put(1, 50);
		 myProbabilities.put(2, 40);
		 
		 createGrid(myGrid);

	}

	public Integer[][] getGrid() {
		return myGrid;
	}
	
	public HashMap<String, String> getParameters(){
		return myParameters;
	}

	private void createGrid(Integer[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				generateCellValues(i, j);
			}
		}
	}

	private void generateCellValues(int i, int j) {
		Integer random = myRandom.nextInt(100);
		Integer currentCheck = 0;
		while (currentCheck < 100) {
			for (int key : myProbabilities.keySet()) {
				if (random <= (currentCheck + myProbabilities.get(key))) {
					myGrid[i][j] = key;
					currentCheck = 100;
					break;
				} else {
					currentCheck += myProbabilities.get(key);
				}
			}
		}
	}
}