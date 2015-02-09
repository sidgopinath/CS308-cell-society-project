package model.gridrules;

import java.util.List;
import model.cells.Cell;
import model.patches.Patch;

public class FiniteGridRules extends GridRules {

    @Override
    public void handleEdges (Patch[][] grid, int x, int y, List<Patch> list) {
        if(x<0 || x >= grid[0].length-1 || y <0 || y>=grid.length-1){
            return;
        }
    }

}
