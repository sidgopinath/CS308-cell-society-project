package model.gridrules;

import java.util.List;
import model.patches.Patch;

public class ToroidalGridRules extends GridRules{

    @Override
    public void handleEdges (Patch[][] grid, int x, int y, List<Patch> list) {
        // TODO Auto-generated method stub
        if(x<0){
            x = grid[0].length-1;
        }
        if(x>=grid[0].length-1){
            x = 0;
        }
        if(y<0){
            y=grid.length-1;
        }
        if(y>=grid.length-1){
            y=0;
        }
        list.add(grid[y][x]);
    }

}
