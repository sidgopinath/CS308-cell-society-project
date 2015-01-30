package model;

import java.util.List;

public class SquarePredatorShark extends SquarePredator{
    
    int myLifePeriod;

    public SquarePredatorShark (int breedingPeriod, int lifePeriod) {
        super(breedingPeriod);
        myLifePeriod = lifePeriod;
        // TODO Auto-generated constructor stub
    }

    @Override
    public int getState () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isEdible () {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isMovable () {
        // TODO Auto-generated method stub
        return false;
    }

}
