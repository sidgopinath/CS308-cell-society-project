package model.neighbors;

import java.util.ArrayList;
import java.util.List;
import model.patches.Patch;
import model.gridrules.GridRules;

public class HexagonalNeighbors extends Neighbors{

    @Override
    public List<Patch> getCardinalNeighbors (Patch[][] grid, int x, int y, GridRules rules) {
        return getAllNeighbors(grid,x,y,rules);
    }

    @Override
    public List<Patch> getDiagonalNeighbors (Patch[][] grid, int x, int y, GridRules rules) {
        return getAllNeighbors(grid,x,y,rules);
    }

    @Override
    public List<Patch> getAllNeighbors (Patch[][] grid, int x, int y, GridRules rules) {
        List<Patch> neighbors = new ArrayList<Patch>();
        topRight(grid, x, y, rules, neighbors);
        topLeft(grid, x,y, rules, neighbors);
        Right(grid,x,y,rules,neighbors);
        Left(grid,x,y,rules,neighbors);
        bottomRight(grid,x,y,rules,neighbors);
        bottomLeft(grid,x,y,rules,neighbors);
        return neighbors;
    }

}
