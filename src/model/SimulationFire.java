package model;

import java.util.ArrayList;
import java.util.Map;

public class SimulationFire extends Simulation {

	private FireSquare[][] myGrid;

	@Override
	void fillGrid(Map<String, String> paramMap) {
		for(int j = 0; j < myGrid.length; j++){
			for(int i = 0 ; i < myGrid[0].length; i++){
				//this value will vary depending on paramMap
				myGrid[j][i] = new FireSquareEmpty(null);
			}
		}
		//all squares passed in first, then passed their neighbors
		updateNeighbors();
	}

	@Override
	void updateGrid() {
		for(int j = 0; j < myGrid.length; j++){
			for(int i = 0 ; i < myGrid[0].length; i++){
				myGrid[j][i] = myGrid[j][i].update();
			}
		}
		updateView();
		updateNeighbors();
	}

	@Override
	void updateNeighbors() {
		for(int j = 0; j < myGrid.length; j++){
			for(int i = 0 ; i < myGrid[0].length; i++){
				ArrayList<FireSquare> neighbors = new ArrayList<>();
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
				myGrid[j][i].setNeighbors(neighbors);
			}
		}
		updateGrid();
	}

	@Override
	public void runSim(Map<String, String> paramMap) {
		//grid size determined by paramMap or something
		myGrid = new FireSquare[5][5];
		fillGrid(paramMap);
		updateGrid();
		
	}

	@Override
	void updateView() {
		// TODO Auto-generated method stub
		
	}

	
}
