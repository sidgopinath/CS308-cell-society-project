package model.neighbors;

import java.util.ArrayList;
import java.util.List;
import model.cells.Cell;
import model.gridrules.GridRules;

public class SquareTriangleNeighbors extends Neighbors{

    @Override
    public List<Cell> getCardinalNeighbors (Cell[][] grid, int x, int y, GridRules rules) {
        List<Cell> neighbors = new ArrayList<Cell>();
        int gridHeight = grid.length;
        int gridWidth = grid[0].length;
        
        return null;
    }

    @Override
    public List<Cell> getDiagonalNeighbors (Cell[][] grid, int x, int y, GridRules rules) {
        List<Cell> neighbors = new ArrayList<Cell>();
        int gridHeight = grid.length;
        int gridWidth = grid[0].length;
        
        return null;
    }

    @Override
    public List<Cell> getAllNeighbors (Cell[][] grid, int x, int y, GridRules rules) {
        // TODO Auto-generated method stub
        return null;
    }

}
