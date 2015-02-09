package model.gridrules;
import java.util.List;
import model.cells.Cell;

public abstract class GridRules {

    public abstract void handleEdges(Cell[][] grid, int x, int y, List<Cell> list);
}
