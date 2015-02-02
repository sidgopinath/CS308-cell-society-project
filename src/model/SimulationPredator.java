package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import view.SimulationScreen;
import javafx.scene.paint.Color;

public class SimulationPredator extends Simulation {
    private SquarePredator grid[][];
    private int gridLength;
    private int gridWidth;
    private int sharkLife;
    private int breedingPeriod;
    private Set<SquarePredator> alreadyMoved = new HashSet<SquarePredator>();


    public SimulationPredator(SimulationScreen simscreen, Map<String,String> paramMap,
                              Integer[][] initGrid){
        super(simscreen);
        gridLength = initGrid[0].length;
        gridWidth = initGrid.length;
        runSim(paramMap,initGrid);
    }

    public void move(SquarePredator square,int x, int y){
        SquarePredator childSquare = square.breedSquare();
        SquarePredator moveTo = square.moveSquare();
        if(square.equals(moveTo)){
            return;
        }
        grid[moveTo.getY()][moveTo.getX()] = square;
        if(!square.isBreeding()){
            grid[y][x] = new SquarePredatorEmpty(-1,x, y);
        } else{
            grid[y][x] = childSquare;
        }
        alreadyMoved.add(square);
        alreadyMoved.add(grid[y][x]);
    }

    /*
     * Initializes sim, parses through data passed into upon initialization
     */
    public void runSim(Map<String,String> paramMap,Integer[][] initGrid){
        breedingPeriod = 1;
        sharkLife = 200;
        grid = new SquarePredator[gridWidth][gridLength];
        myView.initSimView(gridWidth,gridLength);
        fillGrid(paramMap, initGrid);
    }


    /*
     * Update grid at step and updates corresponding view
     */
    public void updateGrid(){
        for(int row = 0; row < gridWidth; row++){
            for(int column=0;column<gridLength;column++){
                SquarePredator currentSquare = grid[row][column];
                if(!alreadyMoved.contains(currentSquare)){
                    currentSquare.updateSquare();
                    
                    //Check if need to starve shark
                    if(currentSquare.hasStarved()){
                        System.out.println("Shark has starved");
                        grid[row][column] = new SquarePredatorEmpty(-1, column,row);
                        continue;
                    }
                    SquarePredator child = currentSquare.breedSquare();
                    if(child != null){
                        grid[child.getY()][child.getX()] = child;
                    }
                    updateNeighborSquare(currentSquare);
                    move(currentSquare,column,row);
                } else{
                    continue;
                }
            }
        }
        alreadyMoved.clear();
        updateColorGrid();
        
    }
    /*
     * Method to be run in updateGrid that passes in a copy of the current state
     * of a cells neighbor and updates it's myNeighbors accordingly.
     */
    public void updateNeighbors(){
        for(int i =0; i<gridWidth;i++){
            for(int j=0;j<gridLength;j++){
                updateNeighborSquare(grid[i][j]);
            }
        }
    }


    public void updateNeighborSquare(SquarePredator square){
        SquarePredator up;
        SquarePredator down;
        SquarePredator left;
        SquarePredator right;
        int i = square.getY();
        int j = square.getX();
        if(i==0){
            up=grid[grid.length-1][j];
        }else{
            up=grid[i-1][j];
        }

        if(i==grid.length-1){
            down = grid[0][j];
        }else{
            down = grid[i+1][j];
        }

        if(j==0){
            left = grid[i][grid[0].length-1];
        }else{
            left = grid[i][j-1];
        }

        if(j==grid[0].length-1){
            right = grid[i][0];
        }else{
            right = grid[i][j+1];
        }
        List<SquarePredator> neighborList = new ArrayList<SquarePredator>();
        neighborList.add(up);
        neighborList.add(down);
        neighborList.add(left);
        neighborList.add(right);
        square.updateNeighbors(neighborList);
    }

    void updateColorGrid(){
        Color[][] myColorGrid = new Color[gridWidth][gridLength];
        for(int i=0; i<gridWidth; i++){
            for(int j=0; j< gridLength; j++){
                myColorGrid[i][j] = grid[i][j].getColor();
            }
        }
        myView.updateScreen(myColorGrid);
    }
    
    @Override
    void fillGrid (Map<String, String> paramMap,Integer[][] initGrid) {
        for(int i=0;i<initGrid.length;i++){
            for(int j=0;j<initGrid[0].length;j++){
                int squareValue = initGrid[i][j];
                if(squareValue == 0){
                    grid[i][j] = new SquarePredatorEmpty(-1, j, i);
                } else if(squareValue == 1){
                    grid[i][j] = new SquarePredatorFish(breedingPeriod, j, i);
                } else{
                    grid[i][j] = new SquarePredatorShark(breedingPeriod, sharkLife, j, i);
                }
            }
        }
        updateColorGrid();
    }

}

