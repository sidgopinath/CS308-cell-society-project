package model.simulations;

import java.util.Map;
import view.SimulationScreen;

/**
 * Fire simulation class that extends simulation superclass
 * @author Sid
 *
 */

public class SimulationFire extends Simulation {

    private int myProbCatch;

    public SimulationFire(Map<String,String> paramMap,Map<String,String> styleMap,
                          Integer[][] grid, SimulationScreen simScreen){
        super(paramMap,styleMap, grid,simScreen);
    }

    /**
     * Looks through parameters and puts them into instance variables
     */
    @Override
    void setupParameterControl () {
        String[] paramNames = {"probCatch"};
        double[] currentValues = {myProbCatch};
        double [] minValues = {0};
        double [] maxValues = {100};
        myView.createOptionsPanel(paramNames,currentValues, minValues, maxValues);
    }
    
    @Override
	public void setParameter (String parameter, double value){
        String cellProperty = "probCatch";
        for(int i=0;i<gridWidth;i++){
            for(int j=0;j<gridLength;j++){
                myPatchGrid[i][j].getCell().setProperty(cellProperty, value);
            }
        }
    };

    
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
    
    /**
     * Updates grid based on cell's update methods
     */
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
