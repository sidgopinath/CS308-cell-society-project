package model;

public class SquarePredatorEmpty extends SquarePredator{

    public SquarePredatorEmpty (int breedingPeriod) {
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
        return false;
    }

    @Override
    public boolean isMovable () {
        // TODO Auto-generated method stub
        return true;
    }

}
