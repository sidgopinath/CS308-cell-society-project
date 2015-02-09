package model.cells;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.paint.Color;

public class PredatorCellShark extends PredatorCell{
    
    /**
     * Subclass of squarePredator representing a square with a shark.
     * @author Janan
     */
    private int myLifePeriod;
    private int myCurrentLife;

    public PredatorCellShark (int breedingPeriod, int lifePeriod) {
        super(breedingPeriod);
        myLifePeriod = lifePeriod;
        myCurrentLife = lifePeriod;
        
    }
    
    @Override
    public boolean hasStarved(){
        return myCurrentLife == 0;
    }
    
    @Override
    public void updateSquare () {
        myCurrentLife--;
        super.decrementBreeding();
    }
    
    @Override
    public PredatorCell moveSquareTo () {
        Random squareGenerator = new Random();
        List<PredatorCell> neighborList = super.getMyNeighbors();
        List<PredatorCell> edibleList = new ArrayList<PredatorCell>();
        List<PredatorCell> movableList = new ArrayList<PredatorCell>();
        
        for(PredatorCell square:neighborList){
            if(square.isEdible()){
                edibleList.add(square);
            }
            if(square.isMovable()){
                movableList.add(square);
            }
        }
        if(!edibleList.isEmpty()){
            myCurrentLife = myLifePeriod;
            return edibleList.get(squareGenerator.nextInt(edibleList.size()));
        }
        if(!movableList.isEmpty()){
            return movableList.get(squareGenerator.nextInt(movableList.size()));
        }
        return this;
    }

    @Override
    public PredatorCell getChildSquare (int breedingPeriod) {
        return new PredatorCellShark(breedingPeriod, myLifePeriod);
    }

    @Override
    public boolean isEdible () {
        return false;
    }

    @Override
    public boolean isMovable () {
        return false;
    }

    @Override
    public Color getColor () {
        return Color.YELLOW;
    }

}
