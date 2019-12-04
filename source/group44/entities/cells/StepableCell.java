package group44.entities.cells;

import group44.entities.movableObjects.MovableObject;
import group44.entities.movableObjects.Player;
import group44.game.CollisionCheckResult;
import group44.game.Level;
import group44.game.CollisionCheckResult.CollisionCheckResultType;

/**
 * An abstract class for cells on which can {@link MovableObject} step.
 *
 * @author Tomas Svejnoha
 * @version 1.0
 */
public abstract class StepableCell extends Cell {
    private MovableObject movableObject; // MovableObject standing on the cell

    /**
     * Creates a new {@link StepableCell}.
     *
     * @param level     - The {@link Level} where the object is located.
     * @param title     - Title of the object.
     * @param positionX - Position X in the game.
     * @param positionY - Position Y in the game.
     * @param imagePath - Image path of the instance.
     */
    public StepableCell(Level level, String title, int positionX, int positionY, String imagePath) {
        super(level, title, positionX, positionY, imagePath);
    }

    /**
     * Places {@link MovableObject} on the {@link StepableCell}.
     *
     * @param object - {@link MovableObject} that steps on the cell.
     *
     * @return the {@link CollisionCheckResult} with information about the action result.
     */
    public CollisionCheckResult stepOn(MovableObject object) {
    	if (this.getMovableObject() == null) {
            this.setMovableObject(object);
            this.onStepped(object);
            return new CollisionCheckResult(CollisionCheckResultType.Successful);
        } else {
        	if (this.getMovableObject() instanceof Player) {
        		return new CollisionCheckResult(CollisionCheckResultType.Player, this.getMovableObject());
        	} else {
        		return new CollisionCheckResult(CollisionCheckResultType.Enemy, this.getMovableObject());
        	}
        }
    }

    /**
     * Removes {@link MovableObject} from the {@link StepableCell}.
     */
    public void stepOff() {
        this.movableObject = null;
    }

    /**
     * Indicates whether something currently stands on the cell.
     *
     * @return true if there is some movable object on the cell, otherwise false.
     */
    public Boolean isSteppedOn() {
        return this.movableObject != null;
    }

    /**
     * Returns the {@link MovableObject} which stepped on the {@link StepableCell}.
     *
     * @return the {@link MovableObject}.
     */
    public MovableObject getMovableObject() {
        return this.movableObject;
    }

    /**
     * Sets the {@link MovableObject} on the cell.
     *
     * @param object - the new {@link MovableObject}.
     */
    protected void setMovableObject(MovableObject object) {
    	this.movableObject = object;
    }

    /**
     * Interacts with {@link MovableObject} that stepped on the cell.
     *
     * @param object - The {@link MovableObject} that stepped on cell.
     */
    protected abstract void onStepped(MovableObject object);
}