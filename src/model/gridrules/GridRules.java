package model.gridrules;
import java.util.List;
import model.patches.Patch;

public abstract class GridRules {

    public abstract void handleEdges(Patch[][] grid, int x, int y, List<Patch> list);
}
