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
        myPropertyMap.put(isMovable, (double) 0);
        myPropertyMap.put(isEdible, (double) 0);
    }


    @Override
    public Cell update () {
        myCurrentLife--;
        super.decrementBreeding();
        if(myCurrentLife == 0){
            Cell emptyCell = new PredatorCellEmpty(-1);
            emptyCell.setCoords(myX, myY);
            return emptyCell;
        }
        return moveSquareTo();
    }

    @Override
    public Cell moveSquareTo () {
        Random squareGenerator = new Random();
        List<Cell> edibleList = new ArrayList<Cell>();
        List<Cell> movableList = new ArrayList<Cell>();

        for(Cell square:myNeighbors){
            if(square.viewProperties().get(isEdible)==1){
                edibleList.add(square);
            }
            if(square.viewProperties().get(isMovable) == 1){
                movableList.add(square);
            }
        }
        Cell moveTo;
        if(!edibleList.isEmpty()){
            myCurrentLife = myLifePeriod;
            System.out.println("nomnomnomnom");
            moveTo = edibleList.get(squareGenerator.nextInt(edibleList.size()));
        } else if(!movableList.isEmpty()){
            moveTo = movableList.get(squareGenerator.nextInt(movableList.size()));
        } else{
            return this;
        }
        if(myPropertyMap.get(myCurrentBreeding)!=0){
            this.setCoords(moveTo.getX(), moveTo.getY());
            return this;
        } else{
            Cell newShark = new PredatorCellShark(myPropertyMap.get(myBreedingPeriod).intValue(), myLifePeriod);
            newShark.setCoords(myX, myY);
            this.setCoords(moveTo.getX(), moveTo.getY());
            return newShark;
        }
    }


    @Override
    public Color getColor () {
        return Color.YELLOW;
    }

}
