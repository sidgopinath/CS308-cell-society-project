package model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import view.SimulationScreen;
import javafx.scene.paint.Color;

public class SimulationSegregation extends Simulation {

	private static final int SAFE_GUARD = 1000;
	private AgentSquare[][] myGrid;
	private Random myRandom;

	public SimulationSegregation(Map<String, String> paramMap, Integer[][] grid, SimulationScreen simScreen) {
		super(simScreen);
		myRandom = new Random();
		runSim(paramMap, grid);
	}

	@Override
	void runSim(Map<String, String> paramMap, Integer[][] grid) {
		myGrid = new AgentSquare[grid.length][grid[0].length];
		myView.initSimView(myGrid.length, myGrid[0].length);
		fillGrid(paramMap, grid);
		
	}

	@Override
	void fillGrid(Map<String, String> paramMap, Integer[][] grid) {
		//double mySatisfaction = Double.parseDouble(paramMap.get("satisfaction"));
		double mySatisfaction = 0.5;
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
		updateColorGrid();
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
				if(i - 1 >= 0){
					neighbors.add(myGrid[j][i - 1]);
				}
				if(j + 1 < myGrid.length){
					neighbors.add(myGrid[j + 1][i]);
				}
				if(j - 1 >= 0){
					neighbors.add(myGrid[j - 1][i]);
				}
				if(i+1 < myGrid[0].length && j+1 < myGrid.length){
					neighbors.add(myGrid[j+1][i+1]);
				}
				if(i+1 < myGrid[0].length && j-1 >= 0){
					neighbors.add(myGrid[j-1][i+1]);
				}
				if(i-1 >= 0  && j+1 < myGrid.length){
					neighbors.add(myGrid[j+1][i-1]);
				}
				if(i-1 >= 0 && j-1 >= 0){
					neighbors.add(myGrid[j-1][i-1]);
				}
				myGrid[j][i].setNeighbors(neighbors);
			}
		}
	}

	@Override
	public void updateGrid() {
		updateNeighbors();
		AgentSquare[][] clone = getMyGridClone();
		for (int j = 0; j < clone.length; j++) {
			for (int i = 0; i < clone[0].length; i++) {
				if (!clone[j][i].isSatisfied()) {
					System.out.println("Unsatisfied Agent!");
					moveAgent(j, i, clone[j][i]);
				}
			}
		}
		updateColorGrid();
	}

	private void moveAgent(int j, int i, AgentSquare agent) {
		// could be dangerous, might want to add safeguard
		int count = 0;
		while (true) {
			int rColumn = myRandom.nextInt(myGrid.length);
			int rRow = myRandom.nextInt(myGrid[0].length);
			if (myGrid[rColumn][rRow].isEmpty()) {
//				System.out.println("rColumn: " + rColumn);
//				System.out.println("rRow: " + rRow);
//				System.out.println("moved one agent");
				myGrid[rColumn][rRow] = agent;
				myGrid[j][i] = new AgentSquareEmpty();
				break;
			}
			if (count > SAFE_GUARD) {
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
		myView.updateScreen(colorGrid);

	}
}
