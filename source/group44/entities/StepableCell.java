package group44.entities;

import group44.game.CollisionCheckResult;

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
     * @param title     - Title of the object
     * @param positionX - Position X in the game
     * @param positionY - Position Y in the game
     * @param imagePath - Image path of the instance
     */
    public StepableCell(String title, int positionX, int positionY, String imagePath) {
        super(title, positionX, positionY, imagePath);
    }

    /**
     * Places object on the {@link StepableCell} if it's free and returns empty
     * {@link CollisionCheckResult}. If there is already some {@link MovableObject}
     * on the {@link StepableCell}, a {@link CollisionCheckResult} with colliding
     * object is returned.
     * 
     * @param object - {@link MovableObject} that steps on the cell
     * 
     * @return the {@link CollisionCheckResult} with the colliding
     *         {@link MovableObject}
     */
    public CollisionCheckResult stepOn(MovableObject object) {
        if (this.movableObject == null) {
            this.movableObject = object;
            return new CollisionCheckResult(null);
        } else {
            return new CollisionCheckResult(this.movableObject);
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
}