package model.neighbors;

import java.util.ArrayList;
import java.util.List;
import model.cells.Cell;
import model.gridrules.GridRules;

public class SquareTriangleNeighbors extends Neighbors{

    @Override
    public List<Cell> getCardinalNeighbors (Cell[][] grid, int x, int y, GridRules rules) {
        List<Cell> neighbors = new ArrayList<Cell>();
        Up(grid, x, y, rules, neighbors);
        Down(grid,x,y,rules,neighbors);
        Left(grid,x,y,rules,neighbors);
        Right(grid,x,y,rules,neighbors);
        return neighbors;
    }

    @Override
    public List<Cell> getDiagonalNeighbors (Cell[][] grid, int x, int y, GridRules rules) {
        List<Cell> neighbors = new ArrayList<Cell>();
        topLeft(grid,x,y,rules,neighbors);
        topRight(grid,x,y,rules,neighbors);
        bottomLeft(grid,x,y,rules,neighbors);
        bottomRight(grid,x,y,rules,neighbors);
        return neighbors;
    }

    @Override
    public List<Cell> getAllNeighbors (Cell[][] grid, int x, int y, GridRules rules) {
        // TODO Auto-generated method stub
        List<Cell> neighbors = getCardinalNeighbors(grid,x,y,rules);
        neighbors.addAll(getDiagonalNeighbors(grid,x,y,rules));
        return neighbors;
    }

}
