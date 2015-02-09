package model.simulations;

import java.util.Map;
import view.SimulationScreen;

public class SimulationFire extends Simulation {

    private int myProbCatch;

    public SimulationFire(Map<String,String> paramMap,Map<String,String> styleMap,
                          Integer[][] grid, SimulationScreen simScreen){
        super(paramMap,styleMap, grid,simScreen);
    }

    @Override
    void parseMap (Map<String, String> paramMap) {
        if(!paramMap.containsKey("probCatch") || paramMap.get("probCatch") == null){
        	//throw exception
        	
        }
    	myProbCatch = Integer.parseInt(paramMap.get("probCatch"));
    }
    @Override
    public void updateGrid() {
        updateNeighbors();
        for(int j = 0; j < gridWidth; j++){
            for(int i = 0 ; i < gridLength; i++){
                myPatchGrid[j][i].setCell(myPatchGrid[j][i].getCell().update());
            }
        }
        updateColorGrid();
    }


    @Override
    AbstractCellFactory getCellFactory () {
    	return new FireCellFactory(myProbCatch);
    }
}
