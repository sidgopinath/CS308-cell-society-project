package model.patches;

import model.cells.Cell;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Patch {
    protected Cell myCell;
    protected List<Patch> myNeighbors;
    protected Map<String,Double> myPatchPropertyMap;
    protected int myX;
    protected int myY;
    
    public Patch(int x, int y){
        myX = x;
        myY = y;
        myPatchPropertyMap = new HashMap<String,Double>();
    }
    
    
    public void setCell(Cell cell){
        myCell = cell;
    }
    
    public Cell getCell(){
        return myCell;
    }
    
    public void setNeighbors(List<Patch> neighbors){
        myNeighbors = neighbors;
    }
    
    public List<Patch> getNeighbors(){
        return myNeighbors;
    }
    
}
