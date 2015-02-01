package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SquarePredatorFish extends SquarePredator{

    public SquarePredatorFish (int breedingPeriod,int x, int y) {
        super(breedingPeriod, x, y);
    }

    @Override
    public void updateSquare () {
        super.decrementBreeding();
    }
    
    @Override
    public SquarePredator moveSquare () {
        List<SquarePredator> neighborList = super.getMyNeighbors();
        List<SquarePredator> movableNeighbors = new ArrayList<SquarePredator>();
        for(SquarePredator square: neighborList){
            if(square.isMovable()){
                movableNeighbors.add(square);
            }
        }
        if(movableNeighbors.isEmpty()){
            return this;
        }
        Random squareChoice = new Random();
        SquarePredator moveTo = 
                movableNeighbors.get(squareChoice.nextInt(movableNeighbors.size()));
        return moveTo;
    }

    @Override
    public SquarePredator getChildSquare (int x, int y, int breedingPeriod) {
        return new SquarePredatorFish(breedingPeriod, x, y);
    }
    
    @Override
    public int getState () {
        return 1;
    }

    @Override
    public boolean isEdible () {
        return true;
    }

    @Override
    public boolean isMovable () {
        return false;
    }




}
