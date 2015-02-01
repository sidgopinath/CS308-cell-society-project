package model;

import java.util.Map;

import javafx.scene.paint.Color;

public class SimulationSegregation extends Simulation {

	private AgentSquare[][] myGrid;

	public SimulationSegregation(Map<String,String> paramMap){
		super();
		runSim(paramMap);
	}
	
	@Override
	void fillGrid(Map<String, String> paramMap) {
		for(int j = 0; j < myGrid.length; j++){
			for(int i = 0; i < myGrid[0].length; i++){
				//to be determined by paramMap if it's O or X, or blank
				myGrid[j][i] = new AgentSquareEmpty(null);
			}
		}
		//pass neighbors to all agentSquares
		updateNeighbors();
	}

	@Override
	void updateGrid() {
		AgentSquare[][] clone = getMyGridClone();
		for(int j = 0; j < clone.length; j++){
			for(int i = 0; i < clone[0].length; i++){
				if(!clone[j][i].isSatisfied()){
					//agent not satisfied
					//move to random empty agent square
				}
			}
		}
		updateColorGrid();
		updateNeighbors();
	}

	@Override
	void updateNeighbors() {
		for(int j = 0; j < myGrid.length; j++){
			for(int i = 0; i < myGrid[0].length; i++){
				//pass all neighbors by indices
			}
		}
		updateGrid();
	}

	@Override
	void runSim(Map<String, String> paramMap) {
		myGrid = new AgentSquare[5][5];
		fillGrid(paramMap);
		myView.initSimView(myGrid.length, myGrid[0].length);
		updateGrid();
	}

	@Override
	void updateView(Color[][] colorGrid) {
		myView.updateScreen(colorGrid);
		
	}

	@Override
	void updateColorGrid() {
		//create color grid based off of agent square's state
		updateView(null);
		
	}
	
	//http://stackoverflow.com/questions/1686425/copy-a-2d-array-in-java
	private AgentSquare[][] getMyGridClone(){
		AgentSquare[][] clone = new AgentSquare[myGrid.length][];
		for(int j = 0; j < myGrid.length; j++){
			clone[j] = myGrid[j].clone();
		}
		return clone;
	}
}
