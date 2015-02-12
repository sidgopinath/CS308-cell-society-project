package model.simulations;

import java.util.Map;
import java.util.Random;
import model.cells.AgentCellEmpty;
import model.cells.Cell;
import view.SimulationScreen;

/**
 * Segregation simulation
 * Extension of simulation superclass
 * @author Sunjeev and Sid
 *
 */

public class SimulationSegregation extends Simulation {

    private static final int SAFE_GUARD = 1000;
    private Random myRandom;
	private double mySatisfaction;

    public SimulationSegregation(Map<String, String> paramMap,
                                 Map<String,String> styleMap,
                                 Integer[][] grid, SimulationScreen simScreen) {
        super(paramMap, styleMap,grid, simScreen);
        myRandom = new Random();
    }

    /**
     * Looks through paramMap and puts values into appropriate instance variables
     */
    @Override
    void parseMap (Map<String, String> paramMap) {
    	try{
    		mySatisfaction = Double.parseDouble(paramMap.get("satisfaction"));
    	}
    	catch(Exception e){
    		paramMap.put("satisfaction", "0.3");
    		System.out.println("No satisfaction value. Using default.");
    		parseMap(paramMap);	
    	}      
    }
    
    @Override
    void setupParameterControl () {
        String[] paramNames = {"mySatisfaction"};
        double[] currentValues = {mySatisfaction};
        double [] minValues = {0};
        double [] maxValues = {1};
        myView.createOptionsPanel(paramNames,currentValues, minValues, maxValues);
    }
    @Override
    void setParameter (String parameter, double value){
        String cellProperty = "mySatisfaction";
        for(int i=0;i<gridWidth;i++){
            for(int j=0;j<gridLength;j++){
                myPatchGrid[i][j].getCell().setProperty(cellProperty, value);
            }
        }
    };
    
    /**
     * Updates the grid by looking at each cell and whether it should move
     */
    @Override
    public void updateGrid() {
        updateNeighbors();
        for (int j = 0; j < myPatchGrid.length; j++) {
            for (int i = 0; i < myPatchGrid[0].length; i++) {
            	myPatchGrid[j][i].getCell().update();
                if ((myPatchGrid[j][i].getCell().viewProperties().get("satisfied").intValue()) != 1) {
                    moveAgent(j, i, myPatchGrid[j][i].getCell());
                }
            }
        }
        updateColorGrid();
    }

    /**
     * Moves a cell that isn't satisfied
     * @param j
     * @param i
     * @param clone
     */
    private void moveAgent(int j, int i, Cell clone) {
        int count = 0;
        while (true) {
            int rColumn = myRandom.nextInt(gridWidth);
            int rRow = myRandom.nextInt(gridLength);
            if (myPatchGrid[rColumn][rRow].getCell().viewProperties().get("empty").intValue() == 1) {
                myPatchGrid[rColumn][rRow].setCell(clone);
                myPatchGrid[j][i].setCell(new AgentCellEmpty(myPatchGrid[rColumn][rRow].getCell().
                                                             viewProperties().get("satisfactionRate")));
                break;
            }
            if (count > SAFE_GUARD) {
                System.out.println("agent unable to move");
                break;
            }
            count++;
        }
    }

    @Override
    AbstractCellFactory getCellFactory () {
        return new AgentCellFactory(mySatisfaction);
    }
}
