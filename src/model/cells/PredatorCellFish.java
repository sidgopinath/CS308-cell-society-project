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

    public PredatorCellFish (int breedingPeriod) {
        super(breedingPeriod);
        myPropertyMap.put(isMovable, (double) 0);
        myPropertyMap.put(isEdible, (double) 1);
    }

    @Override
    public Cell update() {
        super.decrementBreeding();
        return moveSquareTo();
    }
    
    @Override
    public Cell moveSquareTo () {
        List<Cell> movableNeighbors = new ArrayList<Cell>();
        for(Cell square: myNeighbors){
            if(square.viewProperties().get(isMovable) == 1){
                movableNeighbors.add(square);
            }
        }
        if(movableNeighbors.isEmpty()){
            return this;
        }
        Random squareChoice = new Random();
        Cell moveTo = 
                movableNeighbors.get(squareChoice.nextInt(movableNeighbors.size()));
        this.setCoords(moveTo.getX(), moveTo.getY());
        return this;
    }


    @Override
    public Color getColor () {
        return Color.GREEN;
    }
}
