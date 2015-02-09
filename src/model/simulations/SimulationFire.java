package model.simulations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.cells.Cell;
import view.SimulationScreen;

public class SimulationFire extends Simulation {

    private int myProbCatch;

    public SimulationFire(Map<String,String> paramMap,Map<String,String> styleMap,
                          Integer[][] grid, SimulationScreen simScreen){
        super(paramMap,styleMap, grid,simScreen);
    }

    @Override
    void parseMap (Map<String, String> paramMap) {
    	try{
    		myProbCatch = Integer.parseInt(paramMap.get("probCatch"));
    	}
    	catch(Exception e){
    		paramMap.put("probCatch", "50");
    		System.out.println("No prob catch value. Using default.");
    		parseMap(paramMap);	
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

    void updateNeighbors() {
        for(int j = 0; j < gridWidth; j++){
            for(int i = 0 ; i < gridLength; i++){
                List<Cell> neighbors = new ArrayList<>();
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
                myGrid[j][i].setNeighbors(neighbors);
            }
        }
    }

    @Override
    AbstractCellFactory getCellFactory () {
    	return new FireCellFactory(myProbCatch);
    }
}
