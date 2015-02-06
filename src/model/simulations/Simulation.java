package model.simulations;

import java.util.List;
import java.util.Map;

import model.cells.Cell;
import view.SimulationScreen;

public abstract class Simulation {

    /**
     * Simulation superclass encompassing methods and data shared by
     * all simulations and that will be used in updating the state
     * of the simulation 
     * 
     * @author Janan
     */
    protected SimulationScreen myView;
    protected int gridLength;
    protected int gridWidth;
    protected Cell[][] myGrid;

    public Simulation(Map<String,String> paramMap, Integer[][] grid,
                      SimulationScreen simScreen){
        myView = simScreen;
        gridLength = grid[0].length;
        gridWidth = grid.length;
        parseMap(paramMap);
        runSim(grid);
    }

    /**
     * first fill grid with appropriate square types
     * then pass squares their appropriate neighbors
     * Then update squares
     */
    void runSim(Integer[][] grid){
        myView.initSimView(gridWidth, gridLength);
        setupGrid();
        fillGrid(grid);
    }
    /**
     * Reads parameter map from XML file and sets instance variables accordingly
     */
    abstract void parseMap(Map<String,String> paramMap);
    
    /**
     * Sets up appropriate 2D array of squares in each Simulation subclass
     */
    abstract void setupGrid();
    
    /**
     * fill grid with squares that have the right values given the parameters and the type of each space
     * initialize grid, and fill
     * @param paramMap 
     */
    abstract void fillGrid(Integer[][] grid);

    /**
     * Updates the grid by one frame
     */
    public void updateGrid(){
    	updateNeighbors();
        for(int j = 0; j < gridWidth; j++){
            for(int i = 0 ; i < gridLength; i++){
                myGrid[j][i] = myGrid[j][i].update();
            }
        }
        updateColorGrid();
    }


    /**
     * this method takes myGrid and turns it into a grid that is readable for the view
     */
    abstract void updateColorGrid();
    abstract void updateNeighbors();
}


