package model.simulations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.cells.Cell;
import model.cells.LifeCell;
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
    
    void addNeighbors(Cell[][] myGrid, List<Cell> neighbors){
        for(int j = 0; j < gridWidth; j++){
            for(int i = 0 ; i < gridLength; i++){
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
    public abstract void updateGrid();


    /**
     * this method takes myGrid and turns it into a grid that is readable for the view
     */
    abstract void updateColorGrid();

}


