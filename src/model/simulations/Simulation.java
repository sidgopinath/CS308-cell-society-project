// This entire file is part of my masterpiece.
// JANAN ZHU
package model.simulations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
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


/**
 * Simulation superclass encompassing methods and data shared by
 * all simulations and that will be used in updating the state
 * of the simulation 
 * @author Janan
 */
public abstract class Simulation {

    protected SimulationScreen myView;
    protected int gridLength;
    protected int gridWidth;
    protected Patch[][] myPatchGrid;
    protected AbstractCellFactory myCellFactory;
    protected Neighbors myNeighbors;
    protected GridRules myGridRules;
    protected String directions;
    private static ResourceBundle myProperties;

    /**
     * Initializes new simulation
     * @param paramMap
     * @param styleMap
     * @param cellGrid
     * @param simScreen
     * @throws ValueException
     */
    public Simulation(Map<String,String> paramMap, Map<String,String>
    styleMap,Integer[][] cellGrid, SimulationScreen simScreen) throws ValueException{

        try{
            myProperties = ResourceBundle.getBundle("resources/resources");
            this.myView = simScreen;
            this.gridLength = cellGrid[0].length;
            this.gridWidth = cellGrid.length;
            parseMap(paramMap);
            parseStyleMap(styleMap);
            this.myCellFactory = getCellFactory();
            this.myView.initSimView(this.gridWidth, this.gridLength);
            fillPatchGrid();
            setupGrid(cellGrid);
            setupParameterControl();
        }
        catch(ValueException e){
            throw new ValueException("Invalid cell state values given.");
        }
    }

    /**
     * Parses through a map for styling options that can be applied to all
     * simulations
     * @param styleMap
     */
    void parseStyleMap(Map<String,String> styleMap){
        String neighbors = styleMap.get(myProperties.getString
                                        ("style_parameter_cellshape"));
        String gridRules = styleMap.get(myProperties.getString
                                        ("style_parameter_edgetype"));
        this.directions = styleMap.get(myProperties.getString
                                       ("style_parameter_neighbors"));
        if(neighbors.equals(myProperties.getString("grid_shape_square")) 
                || neighbors.equals(myProperties.getString("grid_shape_triangle"))){
            this.myNeighbors = new SquareTriangleNeighbors();
        } else{
            this.myNeighbors = new HexagonalNeighbors();
        }
        if(gridRules.equals(myProperties.getString("grid_edge_finite"))){
            this.myGridRules = new FiniteGridRules();
        } else{
            this.myGridRules = new ToroidalGridRules();
        }
    }

    /**
     * Fills myGrid with patches and sets their neighbors
     * 
     */
    void fillPatchGrid(){
        PatchFactory factory = new PatchFactory();
        this.myPatchGrid = new Patch[gridWidth][gridLength];
        for(int i=0; i < gridWidth; i++){
            for(int j=0; j < gridLength; j++){
                this.myPatchGrid[i][j] = factory.getPatch(patchType(), j, i);
            }
        }
        for(int i=0; i < gridWidth; i++){
            for(int j=0; j < gridLength; j++){
                List<Patch> neighborList;
                if(directions.equals(myProperties.getString("direction_all_flag"))){
                    neighborList =myNeighbors.getAllNeighbors(this.myPatchGrid,
                                                              j, i, this.myGridRules);
                } else if(directions.equals(myProperties.getString("direction_cardinal_flag"))){
                    neighborList =myNeighbors.getCardinalNeighbors(this.myPatchGrid, 
                                                                   j, i, this.myGridRules);
                } else{
                    neighborList =myNeighbors.getDiagonalNeighbors(this.myPatchGrid,
                                                                   j, i, this.myGridRules);
                }
                this.myPatchGrid[i][j].setNeighbors(neighborList);
            }
        }
    }

    /**
     * fill grid patches with cells that have the right values given the parameters
     * initialize grid, and fill colorGrid
     * @param grid 
     */
    void setupGrid(Integer[][] grid){
        for (int j = 0; j < gridWidth; j++) {
            for (int i = 0; i < gridLength; i++) {
                Cell newCell = myCellFactory.getCell(grid[j][i]);
                this.myPatchGrid[j][i].setCell(newCell);
                newCell.setPatch(this.myPatchGrid[j][i]);
            }
        }
        updateColorGrid();
    }

    /**
     * Updates each cells record of it's neighbors for all cells in the grid
     */
    void updateNeighbors(){
        for(int j = 0; j < gridWidth; j++){
            for(int i = 0 ; i < gridLength; i++){
                updateNeighborCell(j,i);
            }
        }
    }

    /**
     * Updates the neighbors for the cell at row, column
     * @param row
     * @param column
     */
    public void updateNeighborCell(int row, int column){
        List<Cell> neighbors = new ArrayList<>();
        for(Patch neighbor: this.myPatchGrid[row][column].getNeighbors()){
            neighbors.add(neighbor.getCell());
        }
        this.myPatchGrid[row][column].getCell().setNeighbors(neighbors);
    }

    /**
     * this method takes myGrid and turns it into a Colorgrid that is readable for the view
     */
    void updateColorGrid() {
        Color[][] colorGrid = new Color[gridWidth][gridLength];
        Color[][] patchColorGrid = new Color[gridWidth][gridLength];
        for(int j = 0; j < gridWidth; j++){
            for(int i = 0 ; i < gridLength; i++){
                colorGrid[j][i] = this.myPatchGrid[j][i].getCell().getColor();
                patchColorGrid[j][i] = this.myPatchGrid[j][i].getColor();
            }
        }
        myView.updateScreen(colorGrid);
        //myView.updateScreen(patchColorGrid);
        //Function to pass patch grid to view here
    }
    
    /**
     * Returns appropriate AbstractCellFactory for simulation
     * @return AbstractCellFactory
     * @throws ValueException
     */
    abstract AbstractCellFactory getCellFactory() throws ValueException;
    /**
     * Sets the value of a simulation wide parameter to value
     * @param parameter
     * @param value
     */
    public void setParameter(String parameter, double value){
    };


    /**
     * Reads parameter map from XML file and sets instance variables accordingly
     * @param paramMap
     */
    abstract void parseMap(Map<String,String> paramMap);


    /**
     * Returns the type of Patch for this simulation
     */
    public String patchType(){
        return "";
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

}


