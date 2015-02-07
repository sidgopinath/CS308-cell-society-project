package model.simulations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.cells.PredatorCell;
import model.cells.PredatorCellEmpty;
import model.cells.PredatorCellFish;
import model.cells.PredatorCellShark;
import view.SimulationScreen;
import javafx.scene.paint.Color;

/**
 * Subclass of Simulation for the Predator-Prey CA simulation.
 * Contains methods and data necessary to run simulation
 * @author Janan
 *
 */
public class SimulationPredator extends Simulation {
    private PredatorCell grid[][];
    private int sharkLife;
    private int breedingPeriod;
    private Set<PredatorCell> alreadyMoved = new HashSet<PredatorCell>();


    public SimulationPredator(Map<String,String> paramMap,Integer[][] initGrid,
                              SimulationScreen simscreen){
        super(paramMap, initGrid, simscreen);
    }

    public void parseMap(Map<String,String> paramMap){
        breedingPeriod = Integer.parseInt(paramMap.get("breedingPeriod"));
        sharkLife = Integer.parseInt(paramMap.get("sharkLife"));
    }

    public void move(PredatorCell square,int x, int y){
        PredatorCell childSquare = square.breedSquare();
        PredatorCell moveTo = square.moveSquare();
        if(square.equals(moveTo)){
            return;
        }
        grid[moveTo.getY()][moveTo.getX()] = square;
        if(!square.isBreeding()){
            grid[y][x] = new PredatorCellEmpty(-1,x, y);
        } else{
            grid[y][x] = childSquare;
        }
        alreadyMoved.add(square);
        alreadyMoved.add(grid[y][x]);
    }

    public void setupGrid(){
        grid = new PredatorCell[gridWidth][gridLength];
    }


    /*
     * Update grid at step and updates corresponding view
     */
    public void updateGrid(){
        for(int row = 0; row < gridWidth; row++){
            for(int column=0;column<gridLength;column++){
                PredatorCell currentSquare = grid[row][column];
                if(!alreadyMoved.contains(currentSquare)){
                    currentSquare.updateSquare();

                    //Check if need to starve shark
                    if(currentSquare.hasStarved()){
                        grid[row][column] = new PredatorCellEmpty(-1, column,row);
                        continue;
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

    public void updateNeighborSquare(PredatorCell square){
        PredatorCell up;
        PredatorCell down;
        PredatorCell left;
        PredatorCell right;
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
        List<PredatorCell> neighborList = new ArrayList<PredatorCell>();
        neighborList.add(up);
        neighborList.add(down);
        neighborList.add(left);
        neighborList.add(right);
        square.setNeighbors(neighborList);
    }

    @Override
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
    void fillGrid (Integer[][] initGrid) {
        for(int i=0;i<initGrid.length;i++){
            for(int j=0;j<initGrid[0].length;j++){
                int squareValue = initGrid[i][j];
                if(squareValue == 0){
                    grid[i][j] = new PredatorCellEmpty(-1, j, i);
                } else if(squareValue == 1){
                    grid[i][j] = new PredatorCellFish(breedingPeriod, j, i);
                } else{
                    grid[i][j] = new PredatorCellShark(breedingPeriod, sharkLife, j, i);
                }
            }
        }
        updateColorGrid();
    }

}

