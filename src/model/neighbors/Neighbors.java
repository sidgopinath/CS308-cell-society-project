package model.neighbors;
import java.util.List;
import model.patches.Patch;
import model.gridrules.GridRules;

public abstract class Neighbors {

    //will contain methods to construct neighbor list and check neighbors, etc.
    //extended by different type of neighbor functions?
    //or perhaps different neighbor functions will all be in this class?
    public abstract List<Patch> getCardinalNeighbors(Patch[][] grid, int x,
                                                    int y, GridRules rules);
    public abstract List<Patch> getDiagonalNeighbors(Patch[][] grid, int x, int y,
                                                    GridRules rules);
    public abstract List<Patch> getAllNeighbors(Patch[][] grid, int x, int y,
                                               GridRules rules);

    public void topRight(Patch[][]grid, int x, int y, GridRules rules,
                         List<Patch> neighbors){
        if(x == grid[0].length || y == 0){
            rules.handleEdges(grid, x+1, y-1, neighbors);
        } else{
            neighbors.add(grid[y-1][x+1]);
        }
    }

    public void topLeft(Patch[][]grid, int x, int y, GridRules rules, 
                        List<Patch> neighbors){
        if(x == 0 || y == 0){
            rules.handleEdges(grid, x-1, y-1, neighbors);
        } else{
            neighbors.add(grid[y-1][x-1]);
        }
    }

    public void Right(Patch[][] grid, int x, int y, GridRules rules,
                      List<Patch> neighbors){
        if(x== grid[0].length){
            rules.handleEdges(grid, x+1, y, neighbors);
        } else{
            neighbors.add(grid[y][x+1]);
        }
    }

    public void Left(Patch[][] grid, int x, int y, GridRules rules,
                     List<Patch> neighbors){
        if(x==0){
            rules.handleEdges(grid, x-1, y, neighbors);
        } else{
            neighbors.add(grid[y][x-1]);
        }
    }

    public void bottomRight(Patch[][] grid, int x, int y, GridRules rules,
                            List<Patch> neighbors){
        if(x==grid[0].length || y == grid.length){
            rules.handleEdges(grid, x+1, y+1, neighbors);   
        }else{
            neighbors.add(grid[y+1][x+1]);
        }
    }

    public void bottomLeft(Patch[][] grid, int x, int y, GridRules rules,
                            List<Patch> neighbors){
        if(x==0 || y == grid.length){
            rules.handleEdges(grid, x-1, y+1, neighbors);
        }else{
            neighbors.add(grid[y+1][x-1]);
        }
    }
    
    public void Up(Patch[][] grid, int x, int y, GridRules rules, 
                   List<Patch> neighbors){
        if(y==0){
            rules.handleEdges(grid, x, y-1, neighbors);
        } else{
            neighbors.add(grid[y-1][x]);
        }
    }
    
    public void Down(Patch[][] grid, int x, int y, GridRules rules,
                     List<Patch> neighbors){
        if(y==grid.length){
            rules.handleEdges(grid, x, y+1, neighbors);
        } else{
            neighbors.add(grid[y+1][x]);
        }
    }
}
