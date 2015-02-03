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
		super(paramMap, grid, simScreen);
		myRandom = new Random();
	}

	@Override
	void runSim(Map<String, String> paramMap, Integer[][] grid) {
		myGrid = new AgentSquare[gridWidth][gridLength];
		myView.initSimView(gridWidth, gridLength);
		fillGrid(grid);
		
	}

	@Override
	void fillGrid(Integer[][] grid) {
		//double mySatisfaction = Double.parseDouble(paramMap.get("satisfaction"));
		double mySatisfaction = 0.5;
		for (int j = 0; j < gridWidth; j++) {
			for (int i = 0; i < gridLength; i++) {
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
	 * Bad code. Duplicated from SimulationLife also.
	 * REFACTOR
	 */
	void updateNeighbors() {
		for (int j = 0; j < gridWidth; j++) {
			for (int i = 0; i < gridLength; i++) {
				ArrayList<AgentSquare> neighbors = new ArrayList<>();
				if(i + 1 < gridLength){
					neighbors.add(myGrid[j][i + 1]);
				}
				if(i - 1 >= 0){
					neighbors.add(myGrid[j][i - 1]);
				}
				if(j + 1 < gridWidth){
					neighbors.add(myGrid[j + 1][i]);
				}
				if(j - 1 >= 0){
					neighbors.add(myGrid[j - 1][i]);
				}
				if(i+1 < gridLength && j+1 < gridWidth){
					neighbors.add(myGrid[j+1][i+1]);
				}
				if(i+1 < gridLength && j-1 >= 0){
					neighbors.add(myGrid[j-1][i+1]);
				}
				if(i-1 >= 0  && j+1 < gridWidth){
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
			int rColumn = myRandom.nextInt(gridWidth);
			int rRow = myRandom.nextInt(gridLength);
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
		AgentSquare[][] clone = new AgentSquare[gridWidth][];
		for (int j = 0; j < gridWidth; j++) {
			clone[j] = myGrid[j].clone();
		}
		return clone;
	}

	@Override
	void updateColorGrid() {
		Color[][] colorGrid = new Color[gridWidth][gridLength];
		for(int j = 0; j < gridWidth; j++){
			for(int i = 0 ; i < gridLength; i++){
				colorGrid[j][i] = myGrid[j][i].getColor();
			}
		}
		myView.updateScreen(colorGrid);

	}
}
