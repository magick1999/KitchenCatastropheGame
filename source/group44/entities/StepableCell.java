package group44.entities;

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
     * Places object on the {@link StepableCell} if it's free and returns empty
     * {@link CollisionCheckResult}. If there is already some {@link MovableObject}
     * on the {@link StepableCell}, a {@link CollisionCheckResult} with colliding
     * object is returned.
     *
     * @param object - {@link MovableObject} that steps on the cell.
     *
     * @return the {@link CollisionCheckResult} with a result and colliding
     *         {@link LevelObject}.
     */
    public abstract CollisionCheckResult stepOn(MovableObject object);

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
     * Interacts with {@link MovableObject} that stepped on the cell.
     *
     * @param object - The {@link MovableObject} that stepped on cell.
     */
    protected abstract void onStepped(MovableObject object);
}