package model;

import javafx.scene.paint.Color;

public class SquarePredatorEmpty extends SquarePredator{

    public SquarePredatorEmpty (int breedingPeriod, int x, int y) {
        super(breedingPeriod,x,y);
    }
    
    @Override
    public void updateSquare () {
    }
    
    @Override
    public SquarePredator moveSquareTo () {
        return this;
    }

    @Override
    public SquarePredator getChildSquare (int x, int y, int breedingPeriod) {
        return null;
    }

    @Override
    public boolean isEdible () {
        return false;
    }

    @Override
    public boolean isMovable () {
        return true;
    }

    @Override
    public Color getColor () {
        return Color.AQUA;
    }

}
