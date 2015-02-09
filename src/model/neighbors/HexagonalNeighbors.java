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
        topRight(grid, x, y, rules, neighbors);
        topLeft(grid, x,y, rules, neighbors);
        Right(grid,x,y,rules,neighbors);
        Left(grid,x,y,rules,neighbors);
        bottomRight(grid,x,y,rules,neighbors);
        bottomLeft(grid,x,y,rules,neighbors);
        return neighbors;
    }

}
