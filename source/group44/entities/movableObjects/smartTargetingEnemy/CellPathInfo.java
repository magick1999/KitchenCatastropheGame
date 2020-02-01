package group44.entities.movableObjects.smartTargetingEnemy;

import group44.entities.cells.Cell;
import group44.entities.movableObjects.SmartTargetingEnemy;

/**
 * Holds the information needed about Cells to compute path for the
 * {@link SmartTargetingEnemy}.
 *
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class CellPathInfo {
    /** Position X. */
    private int x; //
    /** Position Y. */
    private int y; //
    /** How many steps to that cell; negative number mean not reacheable. */
    private long cost;
    /** Parent cell. */
    private CellPathInfo parent;

    /**
     * Creates a new instance of {@link CellPathInfo}.
     *
     * @param cell
     *            the cell which we want to describe.
     */
    public CellPathInfo(Cell cell) {
        this.x = cell.getPositionX();
        this.y = cell.getPositionY();

        this.reset();
    }

    /**
     * Sets default cost.
     */
    public void reset() {
        this.cost = Long.MIN_VALUE;
        this.parent = null;
    }

    /**
     * Returns position X of the cell described.
     *
     * @return the position X.
     */
    public int getX() {
        return x;
    }

    /**
     * Returns position Y of the cell described.
     *
     * @return the position Y.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the cost (number of steps needed to reach the cell). Use this method
     * JUST for the FIRST cell.
     *
     * @param cost
     *            the cost.
     */
    public void setCost(long cost) {
        this.cost = cost;
    }

    /**
     * Returns the cost (number of steps needed to reach the cell).
     *
     * @return the cost.
     */
    public long getCost() {
        return cost;
    }

    /**
     * Returns the parent {@link CellPathInfo}.
     *
     * @return the parent.
     */
    public CellPathInfo getParent() {
        return parent;
    }

    /**
     * Sets parent if the path is better.
     *
     * @param newParent
     *            the possible new parent.
     * @return true if the parent was accepted; otherwise false.
     */
    public boolean setParent(CellPathInfo newParent) {
        // NO PARENT SO FAR or BETTER PATH FOUND
        if ((this.parent == null)
                || (newParent.getCost() < this.parent.getCost())) {
            this.parent = newParent;
            this.setCost(newParent.getCost() + 1);
            return true;
        }

        return false;
    }
}
