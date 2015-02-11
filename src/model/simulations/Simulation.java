package model.simulations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.scene.paint.Color;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import model.cells.Cell;
import model.gridrules.FiniteGridRules;
import model.gridrules.GridRules;
import model.gridrules.ToroidalGridRules;
import model.neighbors.HexagonalNeighbors;
import model.neighbors.Neighbors;
import model.neighbors.SquareTriangleNeighbors;
import model.patches.Patch;
import view.SimulationScreen;

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
    protected Patch[][] myPatchGrid;
    protected AbstractCellFactory myCellFactory;
    protected Neighbors myNeighbors;
    protected GridRules myGridRules;
    protected String directions;

    public Simulation(Map<String,String> paramMap, Map<String,String>
    styleMap,Integer[][] cellGrid, SimulationScreen simScreen) throws ValueException{
        
    	parseMap(paramMap);
        parseStyleMap(styleMap);
        myView = simScreen;
        gridLength = cellGrid[0].length;
        gridWidth = cellGrid.length;
        myCellFactory = getCellFactory();
        myView.initSimView(gridWidth, gridLength);
        fillPatchGrid();
        setupGrid(cellGrid);

        try{
        	myView = simScreen;
        	gridLength = cellGrid[0].length;
        	gridWidth = cellGrid.length;
        	parseMap(paramMap);
        	parseStyleMap(styleMap);
        	myCellFactory = getCellFactory();
        	myView.initSimView(gridWidth, gridLength);
        	fillPatchGrid();
        	setupGrid(cellGrid);
        }
        catch(ValueException e){
        	throw new ValueException("Invalid cell state values given.");
        }
    }

    /**
     * Parses through a map for styling options that can be applied to all
     * simulations
     */
    void parseStyleMap(Map<String,String> styleMap){
        // set styles here
        String neighbors = styleMap.get("cellShape");
        String gridRules = styleMap.get("edgeType");
        directions = styleMap.get("neighbors");
        if(neighbors.equals("square") || neighbors.equals("triangle")){
            myNeighbors = new SquareTriangleNeighbors();
        } else{
            myNeighbors = new HexagonalNeighbors();
        }
        if(gridRules.equals("finite")){
            myGridRules = new FiniteGridRules();
        } else{
            myGridRules = new ToroidalGridRules();
        }
    }

    abstract AbstractCellFactory getCellFactory() throws ValueException;

    void fillPatchGrid(){
        PatchFactory factory = new PatchFactory();
        myPatchGrid = new Patch[gridWidth][gridLength];
        for(int i=0; i<gridWidth; i++){
            for(int j=0; j<gridLength; j++){
                myPatchGrid[i][j] = factory.getPatch(patchType(), j, i);
            }
        }
        for(int i=0; i<gridWidth; i++){
            for(int j=0; j<gridLength; j++){
                if(directions.equals("all")){
                    myPatchGrid[i][j].setNeighbors(myNeighbors.getAllNeighbors
                                                   (myPatchGrid, j, i, myGridRules));
                } else if(directions.equals("cardinal")){
                    myPatchGrid[i][j].setNeighbors(myNeighbors.getCardinalNeighbors
                                                   (myPatchGrid, j, i, myGridRules));
                } else{
                    myPatchGrid[i][j].setNeighbors(myNeighbors.getDiagonalNeighbors
                                                   (myPatchGrid, j, i, myGridRules));
                }
            }
        }
    }

    public String patchType(){
        return "";
    }

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
        for (int j = 0; j < gridWidth; j++) {
            for (int i = 0; i < gridLength; i++) {
                myPatchGrid[j][i].setCell(myCellFactory.getCell(grid[j][i]));
            }
        }
        updateColorGrid();
    }

    /**
     * Passes parameter names and values to view to allow the user to interactively
     * change them
     */
     void setupParameterControl(){
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
                colorGrid[j][i] = myPatchGrid[j][i].getCell().getColor();
            }
        }
        myView.updateScreen(colorGrid);
    }

    /*
     * Update each cell's record of it's neighbors
     */
    void updateNeighbors(){
        for(int j = 0; j < gridWidth; j++){
            for(int i = 0 ; i < gridLength; i++){
                updateNeighborSquare(j,i);
            }
        }
    }
    
    public void updateNeighborSquare(int row, int column){
        List<Cell> neighbors = new ArrayList<>();
        for(Patch neighbor: myPatchGrid[row][column].getNeighbors()){
            neighbors.add(neighbor.getCell());
        }
        myPatchGrid[row][column].getCell().setNeighbors(neighbors);
    }
    
    public void updatePatches(){
        
    }
}


