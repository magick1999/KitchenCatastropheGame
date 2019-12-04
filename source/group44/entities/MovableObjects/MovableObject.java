package group44.entities.MovableObjects;

import group44.entities.LevelObject;
import group44.entities.Cells.Cell;
import group44.entities.Cells.StepableCell;
import group44.entities.Cells.Wall;
import group44.game.CollisionCheckResult;
import group44.game.Level;

/**
 * Abstract classes from which inherits all objects able to move.
 *
 * @author Tomas Svejnoha
 * @version 1.0
 */
public abstract class MovableObject extends LevelObject {
    private int velocityX;
    private int velocityY;

    /**
     * Creates a new {@link MovableObject}.
     *
     * @param level     - The {@link Level} where the {@link MovableObject} is
     *                  located.
     * @param title     - Title of the object.
     * @param positionX - Position X in the game.
     * @param positionY - Position Y in the game.
     * @param velocityX - Velocity X of the instance.
     * @param velocityY - Velocity Y of the instance.
     * @param imagePath - Path to the Image representing {@link MovableObject} in
     *                  the game.
     */
    public MovableObject(Level level, String title, int positionX, int positionY, int velocityX, int velocityY,
            String imagePath) {
        super(level, title, positionX, positionY, imagePath);

        this.setVelocityX(velocityX);
        this.setVelocityY(velocityY);
    }

    /**
     * Returns the velocity of the object on the X axis.
     *
     * @return velocity X.
     */
    public int getVelocityX() {
        return this.velocityX;
    }

    /**
     * Sets the velocity on the X axis.
     *
     * @param vx - velocity X.
     */
    public void setVelocityX(int vx) {
        this.velocityX = vx;
    }

    /**
     * Returns the velocity of the object on the Y axis.
     *
     * @return velocity Y.
     */
    public int getVelocityY() {
        return this.velocityY;
    }

    /**
     * Sets the velocity on the Y axis.
     *
     * @param vy - velocity Y.
     */
    public void setVelocityY(int vy) {
        this.velocityY = vy;
    }

    /**
     * Kills the {@link MovableObject} and removes it from the grid.
     *
     * @param object - {@link LevelObject} trying to kill the {@link MovableObject}.
     */
    public void die(LevelObject object) {
        // Remove MovableObject from the grid
        ((StepableCell) this.getLevel().getGrid()[this.getPositionX()][this.getPositionY()]).stepOff();
    }

    /**
     * Moves the object.
     */
    public abstract void move();

    /**
     * Returns the {@link StepableCell} on which the {@link MovableObject} is
     * located.
     *
     * @param object - the {@link MovableObject}
     * @return if found, the {@link StepableCell} where the object is located,
     *         otherwise null
     */
    protected StepableCell getStepableCellAtMovableObjectPosition(MovableObject object) {
        Cell[][] grid = this.getLevel().getGrid();
        if (grid[object.getPositionX()][object.getPositionY()] instanceof StepableCell) {
            return (StepableCell) grid[object.getPositionX()][object.getPositionY()];
        }
        return null;
    }

    /**
     * Returns the next {@link StepableCell} the {@link MovableObject} will step on.
     *
     * @param object    - The instance of {@link MovableObject}.
     * @param velocityX - velocity X of the object.
     * @param velocityY - velocity Y of the object.
     * @return the next {@link StepableCell} the {@link MovableObject} will step on;
     *         null if {@link Wall} or out of range.
     */
    protected StepableCell getNextStepableCellInVelocity(MovableObject object, int velocityX, int velocityY) {
    	Cell[][] grid = this.getLevel().getGrid();
        StepableCell cell = this.getStepableCellAtMovableObjectPosition(object);

        // Next position based on current position and velocity
        int nextCellIndexX = cell.getPositionX() + velocityX;
        int nextCellIndexY = cell.getPositionY() + velocityY;

        // Return StepableCell if the next step is in the boundaries of the grid;
        // otherwise null
        if (
        		0 <= nextCellIndexX && nextCellIndexX < this.getLevel().getGridWidth() &&
        		0 <= nextCellIndexY && nextCellIndexY < this.getLevel().getGridHeight() &&
        		grid[nextCellIndexX][nextCellIndexY] instanceof StepableCell) {
            return (StepableCell) grid[nextCellIndexX][nextCellIndexY];
        }
        return null;
    }

    /**
     * Method executed when the {@link MovableObject} object collides with some {@link LevelObject}.
     *
     * @param object - the {@link CollisionCheckResult} with the collision status.
     */
    protected abstract void onCollided(CollisionCheckResult result);
}
