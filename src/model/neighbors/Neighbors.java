package model.neighbors;
import java.util.List;
import model.cells.Cell;
import model.gridrules.GridRules;

public abstract class Neighbors {

    //will contain methods to construct neighbor list and check neighbors, etc.
    //extended by different type of neighbor functions?
    //or perhaps different neighbor functions will all be in this class?
    public abstract List<Cell> getCardinalNeighbors(Cell[][] grid, int x,
                                                    int y, GridRules rules);
    public abstract List<Cell> getDiagonalNeighbors(Cell[][] grid, int x, int y,
                                                    GridRules rules);
    public abstract List<Cell> getAllNeighbors(Cell[][] grid, int x, int y,
                                               GridRules rules);

    public void topRight(Cell[][]grid, int x, int y, GridRules rules,
                         List<Cell> neighbors){
        if(x == grid[0].length || y == 0){
            rules.handleEdges(grid, x+1, y-1, neighbors);
        } else{
            neighbors.add(grid[x+1][y-1]);
        }
    }

    public void topLeft(Cell[][]grid, int x, int y, GridRules rules, 
                        List<Cell> neighbors){
        if(x == 0 || y == 0){
            rules.handleEdges(grid, x-1, y-1, neighbors);
        } else{
            neighbors.add(grid[x-1][y-1]);
        }
    }

    public void Right(Cell[][] grid, int x, int y, GridRules rules,
                      List<Cell> neighbors){
        if(x== grid[0].length){
            rules.handleEdges(grid, x+1, y, neighbors);
        } else{
            neighbors.add(grid[x+1][y]);
        }
    }

    public void Left(Cell[][] grid, int x, int y, GridRules rules,
                     List<Cell> neighbors){
        if(x==0){
            rules.handleEdges(grid, x-1, y, neighbors);
        } else{
            neighbors.add(grid[x-1][y]);
        }
    }

    public void bottomRight(Cell[][] grid, int x, int y, GridRules rules,
                            List<Cell> neighbors){
        if(x==grid[0].length || y == grid.length){
            rules.handleEdges(grid, x+1, y+1, neighbors);   
        }else{
            neighbors.add(grid[x+1][y+1]);
        }
    }

    public void bottomLeft(Cell[][] grid, int x, int y, GridRules rules,
                            List<Cell> neighbors){
        if(x==0 || y == grid.length){
            rules.handleEdges(grid, x-1, y+1, neighbors);
        }else{
            neighbors.add(grid[x-1][y+1]);
        }
    }
    
    public void Up(Cell[][] grid, int x, int y, GridRules rules, 
                   List<Cell> neighbors){
        if(y==0){
            rules.handleEdges(grid, x, y-1, neighbors);
        } else{
            neighbors.add(grid[x][y-1]);
        }
    }
    
    public void Down(Cell[][] grid, int x, int y, GridRules rules,
                     List<Cell> neighbors){
        if(y==grid.length){
            rules.handleEdges(grid, x, y+1, neighbors);
        } else{
            neighbors.add(grid[x][y+1]);
        }
    }
}
