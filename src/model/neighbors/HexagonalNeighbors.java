package model.neighbors;

import java.util.ArrayList;
import java.util.List;
import model.cells.Cell;
import model.gridrules.GridRules;

public class HexagonalNeighbors extends Neighbors{

    @Override
    public List<Cell> getCardinalNeighbors (Cell[][] grid, int x, int y, GridRules rules) {
        return getAllNeighbors(grid,x,y,rules);
    }

    @Override
    public List<Cell> getDiagonalNeighbors (Cell[][] grid, int x, int y, GridRules rules) {
        return getAllNeighbors(grid,x,y,rules);
    }

    @Override
    public List<Cell> getAllNeighbors (Cell[][] grid, int x, int y, GridRules rules) {
        List<Cell> neighbors = new ArrayList<Cell>();
        int gridHeight = grid.length;
        int gridWidth = grid[0].length;
        //TopRight
        if(x == gridWidth || y == 0){
            rules.handleEdges(grid, x+1, y-1, neighbors);
        } else{
            neighbors.add(grid[x+1][y-1]);
        }
        //TopLeft
        if(x == 0 || y == 0){
            rules.handleEdges(grid, x-1, y-1, neighbors);
        } else{
            neighbors.add(grid[x-1][y-1]);
        }
        //Right
        if(x== gridWidth){
            rules.handleEdges(grid, x+1, y, neighbors);
        } else{
            neighbors.add(grid[x+1][y]);
        }
        //Left
        if(x==0){
            rules.handleEdges(grid, x-1, y, neighbors);
        } else{
            neighbors.add(grid[x-1][y]);
        }
        //BottomRight
        if(x==gridWidth || y == gridHeight){
         rules.handleEdges(grid, x+1, y+1, neighbors);   
        }else{
            neighbors.add(grid[x+1][y+1]);
        }
        //BottomLeft
        if(x==0 || y == gridHeight){
            rules.handleEdges(grid, x-1, y+1, neighbors);
        }else{
            neighbors.add(grid[x-1][y+1]);
        }
        return neighbors;
    }

}
