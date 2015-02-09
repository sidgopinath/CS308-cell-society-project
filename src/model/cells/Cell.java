package model.cells;

import java.util.List;
import javafx.scene.paint.Color;

public abstract class Cell {
    protected List<Cell> myNeighbors;
    public abstract Color getColor();
    public void setNeighbors(List<Cell> neighbors){
        myNeighbors = neighbors;
    }
    
    
}
