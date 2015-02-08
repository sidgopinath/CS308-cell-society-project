package model.simulations;
import model.cells.Cell;

public abstract class AbstractCellFactory {
    public abstract Cell getCell(int type);
}
