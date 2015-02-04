package model.cells;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.paint.Color;

public class PredatorCellFish extends PredatorCell{

    /**
     * Subclass of SquarePredator representing a square with fish
     * @param breedingPeriod
     * @param x
     * @param y
     */

    public PredatorCellFish (int breedingPeriod,int x, int y) {

        super(breedingPeriod, x, y);
    }

    @Override
    public void updateSquare () {
        super.decrementBreeding();
    }
    
    @Override
    public PredatorCell moveSquareTo () {
        List<PredatorCell> neighborList = super.getMyNeighbors();
        List<PredatorCell> movableNeighbors = new ArrayList<PredatorCell>();
        for(PredatorCell square: neighborList){
            if(square.isMovable()){
                movableNeighbors.add(square);
            }
        }
        if(movableNeighbors.isEmpty()){
            return this;
        }
        Random squareChoice = new Random();
        PredatorCell moveTo = 
                movableNeighbors.get(squareChoice.nextInt(movableNeighbors.size()));
        return moveTo;
    }

    @Override
    public PredatorCell getChildSquare (int x, int y, int breedingPeriod) {
        return new PredatorCellFish(breedingPeriod, x, y);
    }
    
    @Override
    public boolean isEdible () {
        return true;
    }

    @Override
    public boolean isMovable () {
        return false;
    }

    @Override
    public Color getColor () {
        return Color.GREEN;
    }




}
