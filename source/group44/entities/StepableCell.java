package group44.entities;

/**
 * An abstract class for cells on which can {@link MovableObject} step.
 * 
 * @author Tomas Svejnoha
 * @version 1.0
 */
public abstract class StepableCell {
    private MovableObject movableObject;

    /**
     * Places object on the {@link StepableCell}
     * 
     * @param object - {@link MovableObject} that steps on the cell.
     */
    public void stepOn(MovableObject object) {
        if (this.movableObject == null) {
            this.movableObject = object;
        } else {
            // TODO: Implement
            throw new UnsupportedOperationException("Not implemented exception");
        }
    }

    /**
     * Removes {@link MovableObject} from the {@link StepableCell}.
     */
    public void stepOff() {
        this.movableObject = null;
    }

    /**
     * Indecates whether something currently stands on the cell.
     * 
     * @return true if there is some movable object on the cell, otherwise false.
     */
    public Boolean isSteppedOn() {
        return this.movableObject != null;
    }
}