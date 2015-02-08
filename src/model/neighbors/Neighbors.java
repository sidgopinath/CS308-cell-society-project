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

}
