package model;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class SquarePredatorShark extends SquarePredator{
    
    private int myLifePeriod;
    private int myCurrentLife;

    public SquarePredatorShark (int breedingPeriod, int lifePeriod, int x, int y) {
        super(breedingPeriod,x,y);
        myLifePeriod = lifePeriod;
        myCurrentLife = lifePeriod;
    }

    @Override
    public SquarePredator moveSquare () {
        Random squareGenerator = new Random();
        List<SquarePredator> neighborList = super.getMyNeighbors();
        List<SquarePredator> edibleList = new ArrayList<SquarePredator>();
        List<SquarePredator> movableList = new ArrayList<SquarePredator>();
        
        for(SquarePredator square:neighborList){
            if(square.isEdible()){
                edibleList.add(square);
            }
            if(square.isMovable()){
                movableList.add(square);
            }
        }
        if(!edibleList.isEmpty()){
            return edibleList.get(squareGenerator.nextInt(edibleList.size()));
        }
        if(!movableList.isEmpty()){
            return movableList.get(squareGenerator.nextInt(movableList.size()));
        }
        return this;
    }

    @Override
    public SquarePredator getChildSquare (int x, int y, int breedingPeriod) {
        return new SquarePredatorShark(breedingPeriod, myLifePeriod, x,y);
    }

    @Override
    public int getState () {
        return 2;
    }

    @Override
    public boolean isEdible () {
        return false;
    }

    @Override
    public boolean isMovable () {
        return false;
    }

}
