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
				myGrid[j][i] = new AgentSquareO(null);
			}
		}
		//pass neighbors to all agentSquares
		updateNeighbors();
	}

	@Override
	void updateGrid() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void updateNeighbors() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void runSim(Map<String, String> paramMap) {
		myGrid = new AgentSquare[5][5];
		fillGrid(paramMap);
		updateGrid();
	}

	@Override
	void updateView(Color[][] grid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void updateColorGrid() {
		// TODO Auto-generated method stub
		
	}
}
