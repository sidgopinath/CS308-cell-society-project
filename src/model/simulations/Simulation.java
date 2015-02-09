package model.simulations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.scene.paint.Color;
import model.cells.AgentCellEmpty;
import model.cells.AgentCellO;
import model.cells.AgentCellX;
import model.cells.Cell;
import model.cells.LifeCell;
import view.SimulationScreen;
import model.patches.Patch;

public abstract class Simulation {

    /**
     * Simulation superclass encompassing methods and data shared by
     * all simulations and that will be used in updating the state
     * of the simulation 
     * 
     * @author Janan
     */
//    protected Patch[][] myGrid;
    protected SimulationScreen myView;
    protected int gridLength;
    protected int gridWidth;
    protected Cell[][] myGrid;
    protected AbstractCellFactory myCellFactory;

    public Simulation(Map<String,String> paramMap, Integer[][] grid,
                      SimulationScreen simScreen){
        myView = simScreen;
        gridLength = grid[0].length;
        gridWidth = grid.length;
        myCellFactory = getCellFactory();
        parseMap(paramMap);
        myView.initSimView(gridWidth, gridLength);
        setupGrid(grid);
    }

    abstract AbstractCellFactory getCellFactory();
    
//    void fillPatchGrid(){
//        myGrid = new Patch[gridWidth][gridLength];
//        for(int i=0; i<gridWidth; i++){
//            for(int j=0; j<gridLength; j++){
//                myGrid[i][j] = new Patch();
//            }
//        }
//    }
    /**
     * Reads parameter map from XML file and sets instance variables accordingly
     */
    abstract void parseMap(Map<String,String> paramMap);
    
    /**
     * fill grid with squares that have the right values given the parameters and the type of each space
     * initialize grid, and fill
     * @param paramMap 
     */
     void setupGrid(Integer[][] grid){
         myGrid = new Cell[gridWidth][gridLength];
         for (int j = 0; j < gridWidth; j++) {
             for (int i = 0; i < gridLength; i++) {
              myGrid[j][i] = myCellFactory.getCell(grid[j][i]);
             }
         }
         updateColorGrid();
     }
    

    /**
     * Updates the grid by one frame
     */
    public abstract void updateGrid();


    /**
     * this method takes myGrid and turns it into a grid that is readable for the view
     */
    void updateColorGrid() {
        Color[][] colorGrid = new Color[gridWidth][gridLength];
        for(int j = 0; j < gridWidth; j++){
            for(int i = 0 ; i < gridLength; i++){
                colorGrid[j][i] = myGrid[j][i].getColor();
            }
        }
        myView.updateScreen(colorGrid);
    }

}


