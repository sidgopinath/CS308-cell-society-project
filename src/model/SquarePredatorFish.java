package model;

public class SquarePredatorFish extends SquarePredator{

    public SquarePredatorFish (int breedingPeriod) {
        super(breedingPeriod);
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
        return true;
    }

    @Override
    public boolean isMovable () {
        // TODO Auto-generated method stub
        return false;
    }

}
