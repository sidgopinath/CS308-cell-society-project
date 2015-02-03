package model;

import java.util.Map;

import view.SimulationScreen;

public abstract class Simulation {

    protected SimulationScreen myView;
    protected int gridLength;
    protected int gridWidth;

    public Simulation(Map<String,String> paramMap, Integer[][] grid,
                      SimulationScreen simScreen){
        myView = simScreen;
        gridLength = grid[0].length;
        gridWidth = grid.length;
        runSim(paramMap,grid);
    }

    /**
     * first fill grid with appropriate square types
     * then pass squares their appropriate neighbors
     * Then update squares
     */
    abstract void runSim(Map<String, String> paramMap, Integer[][] grid);

    /**
     * fill grid with squares that have the right values given the parameters and the type of each space
     * initialize grid, and fill
     * @param paramMap 
     */
    abstract void fillGrid(Integer[][] grid);

    /**
     * first it will pass each square its neighbors
     *  then it will go through and call each cell to update.
     *  update view
     */
    public abstract void updateGrid();


    /**
     * this method takes myGrid and turns it into a grid that is readable for the view
     */
    abstract void updateColorGrid();

}


