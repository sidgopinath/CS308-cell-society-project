package model.gridrules;

import java.util.List;
import model.cells.Cell;

public class ToroidalGridRules extends GridRules{

    @Override
    public void handleEdges (Cell[][] grid, int x, int y, List<Cell> list) {
        // TODO Auto-generated method stub
        if(x<0){
            x = grid[0].length-1;
        }
        if(x>=grid[0].length){
            x = 0;
        }
        if(y<0){
            y=grid.length;
        }
        if(y>=grid.length){
            y=0;
        }
        list.add(grid[y][x]);
    }

}
