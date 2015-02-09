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
        myPropertyMap.put(isMovable, (double) 1);
        myPropertyMap.put(isEdible, (double) 0);
    }
    
    @Override
    public PredatorCell update() {
        return this;
    }
    
    @Override
    public PredatorCell moveSquareTo () {
        return this;
    }


    @Override
    public Color getColor () {
        return Color.AQUA;
    }

}
