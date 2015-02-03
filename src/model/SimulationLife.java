package model;

import java.util.ArrayList;
import java.util.Map;

import view.SimulationScreen;
import javafx.scene.paint.Color;

public class SimulationLife extends Simulation {

	private LifeSquare[][] myGrid;

	public SimulationLife(Map<String, String> paramMap, Integer[][] grid, SimulationScreen simScreen) {
		super(paramMap, grid, simScreen);
	}
	
	@Override
	public void runSim(Map<String, String> paramMap, Integer[][] grid) {
		myGrid = new LifeSquare[gridWidth][gridLength];
		myView.initSimView(gridWidth, gridLength);
		fillGrid(grid);
	}

	@Override
	void fillGrid(Integer[][] grid) {
		for(int j = 0; j < gridWidth; j++){
			for(int i = 0 ; i < gridLength; i++){
				if(grid[j][i] == 0){
					myGrid[j][i] = new LifeSquareDead();
				}
				else{
					myGrid[j][i] = new LifeSquareAlive();
				}
			}
		}
		updateColorGrid();
	}

	/**
	 * This code is pretty awful as far as if statements go
	 * REFACTOR! 
	 */
	void updateNeighbors() {
		for(int j = 0; j < gridWidth; j++){
			for(int i = 0 ; i < gridLength; i++){
				ArrayList<LifeSquare> neighbors = new ArrayList<>();
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
				if(i-1 >= 0 && j+1 < gridWidth){
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
		for(int j = 0; j < gridWidth; j++){
			for(int i = 0 ; i < gridLength; i++){
				myGrid[j][i] = myGrid[j][i].update();
			}
		}
		updateColorGrid();
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
