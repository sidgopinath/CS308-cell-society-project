package model.cells;

import javafx.scene.paint.Color;

    
    /**
     * Subclass of SquarePredator representing an empty square
     * @author Janan
     * @param breedingPeriod
     * @param x
     * @param y
     */

public class PredatorCellEmpty extends PredatorCell{

    public PredatorCellEmpty (int breedingPeriod) {
        super(breedingPeriod);
    }
    
    @Override
    public void updateSquare () {
    }
    
    @Override
    public PredatorCell moveSquareTo () {
        return this;
    }

    @Override
    public PredatorCell getChildSquare (int breedingPeriod) {
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
