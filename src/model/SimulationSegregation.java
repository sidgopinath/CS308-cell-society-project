package model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import javafx.scene.paint.Color;

public class SimulationSegregation extends Simulation {

	private AgentSquare[][] myGrid;
	private Random myRandom;
	private int mySatisfaction;

	public SimulationSegregation(Map<String, String> paramMap, Integer[][] grid) {
		super();
		runSim(paramMap, grid);
	}

	@Override
	void runSim(Map<String, String> paramMap, Integer[][] grid) {
		mySatisfaction = Integer.parseInt(paramMap.get("satisfaction"));
		myGrid = new AgentSquare[grid.length][grid[0].length];
		fillGrid(paramMap, grid);
		myView.initSimView(myGrid.length, myGrid[0].length);
		updateGrid();
	}

	@Override
	void fillGrid(Map<String, String> paramMap, Integer[][] grid) {
		for (int j = 0; j < myGrid.length; j++) {
			for (int i = 0; i < myGrid[0].length; i++) {
				if(grid[j][i] == 0){
					myGrid[j][i] = new AgentSquareEmpty(mySatisfaction);
				}
				else if(grid[j][i] == 1){
					myGrid[j][i] = new AgentSquareO(mySatisfaction);
				}
				else if(grid[j][i] == 2){
					myGrid[j][i] = new AgentSquareX(mySatisfaction);
				}
			}
		}
		updateNeighbors();
	}

	/**
	 * Bade code. Duplicated from SimulationLife also.
	 * REFACTOR
	 */
	@Override
	void updateNeighbors() {
		for (int j = 0; j < myGrid.length; j++) {
			for (int i = 0; i < myGrid[0].length; i++) {
				ArrayList<AgentSquare> neighbors = new ArrayList<>();
				if(i + 1 < myGrid[0].length){
					neighbors.add(myGrid[j][i + 1]);
				}
				if(i - 1 > 0){
					neighbors.add(myGrid[j][i - 1]);
				}
				if(j + 1 < myGrid.length){
					neighbors.add(myGrid[j + 1][i]);
				}
				if(j - 1 > 0){
					neighbors.add(myGrid[j - 1][i]);
				}
				if(i+1 < myGrid[0].length && j+1 < myGrid.length){
					neighbors.add(myGrid[j+1][i+1]);
				}
				if(i+1 < myGrid[0].length && j-1 < myGrid.length){
					neighbors.add(myGrid[j-1][i+1]);
				}
				if(i-1 < myGrid[0].length && j+1 < myGrid.length){
					neighbors.add(myGrid[j+1][i-1]);
				}
				if(i-1 < myGrid[0].length && j+1 < myGrid.length){
					neighbors.add(myGrid[j+1][i-1]);
				}
				myGrid[j][i].setNeighbors(neighbors);
			}
		}
		updateGrid();
	}

	@Override
	public void updateGrid() {
		AgentSquare[][] clone = getMyGridClone();
		for (int j = 0; j < clone.length; j++) {
			for (int i = 0; i < clone[0].length; i++) {
				if (!clone[j][i].isSatisfied()) {
					moveAgent(j, i, clone[j][i]);
				}
			}
		}
		updateColorGrid();
		updateNeighbors();
	}

	private void moveAgent(int j, int i, AgentSquare agent) {
		myGrid[j][i] = new AgentSquareEmpty();
		// could be dangerous, might want to add safeguard
		int count = 0;
		while (true) {
			int rColumn = myRandom.nextInt(myGrid.length);
			int rRow = myRandom.nextInt(myGrid[0].length);
			if (myGrid[rColumn][rRow].isEmpty()) {
				myGrid[j][i] = agent;
				break;
			}
			if (count > 100) {
				System.out.println("agent unable to move");
				break;
			}
			count++;
		}
	}

	// http://stackoverflow.com/questions/1686425/copy-a-2d-array-in-java
	private AgentSquare[][] getMyGridClone() {
		AgentSquare[][] clone = new AgentSquare[myGrid.length][];
		for (int j = 0; j < myGrid.length; j++) {
			clone[j] = myGrid[j].clone();
		}
		return clone;
	}

	@Override
	void updateColorGrid() {
		Color[][] colorGrid = new Color[myGrid.length][myGrid[0].length];
		for(int j = 0; j < myGrid.length; j++){
			for(int i = 0 ; i < myGrid[j].length; i++){
				colorGrid[j][i] = myGrid[j][i].getColor();
			}
		}
		updateView(colorGrid);

	}

	@Override
	void updateView(Color[][] colorGrid) {
		myView.updateScreen(colorGrid);
	
	}

}
