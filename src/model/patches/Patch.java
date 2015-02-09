package model.patches;

import model.cells.Cell;
import java.util.List;

public class Patch {
    private Cell myCell;
    private List<Patch> myNeighbors;
    
    
    public void setCell(Cell cell){
        myCell = cell;
    }
    
    public Cell getCell(){
        return myCell;
    }
    
    public void setNeighbors(List<Patch> neighbors){
        myNeighbors = neighbors;
    }
    
}
