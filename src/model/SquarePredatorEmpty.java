package model;

public class SquarePredatorEmpty extends SquarePredator{

    public SquarePredatorEmpty (int breedingPeriod, int x, int y) {
        super(breedingPeriod,x,y);
    }
    
    @Override
    public void updateSquare () {
    }
    
    @Override
    public SquarePredator moveSquare () {
        return this;
    }

    @Override
    public SquarePredator getChildSquare (int x, int y, int breedingPeriod) {
        return null;
    }

    @Override
    public int getState () {
        return 0;
    }

    @Override
    public boolean isEdible () {
        return false;
    }

    @Override
    public boolean isMovable () {
        return true;
    }

}
