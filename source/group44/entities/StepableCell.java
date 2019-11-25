package group44.entities;

/**
 * An abstract class for cells on which can {@link MovableObject} step.
 * 
 * @author Tomas Svejnoha, Jordan Price
 * @version 1.1
 */
public abstract class StepableCell extends Cell {
    
	public StepableCell(String title, int positionX, int positionY, int size, String imagePath) {
		super(title, positionX, positionY, size, imagePath);
	}

	private MovableObject movableObject; // MovableObject standing on the cell

    /**
     * Places object on the {@link StepableCell}
     * 
     * @param object - {@link MovableObject} that steps on the cell.
     */
    public void stepOn(MovableObject object) {
        if (this.getMovableObject() == null) {
            this.setMovableObject(object);
        } else {
            // TODO: Implement
            throw new UnsupportedOperationException("Not implemented exception");//This would also be the exception if an enemy caught a player.
        }
    }

    /**
     * Removes {@link MovableObject} from the {@link StepableCell}.
     */
    public void stepOff() {
        this.setMovableObject(null);
    }

    /**
     * Indicates whether something currently stands on the cell.
     * 
     * @return true if there is some movable object on the cell, otherwise false.
     */
    public Boolean isSteppedOn() {
        return this.getMovableObject() != null;
    }

	
    
    public MovableObject getMovableObject() {
		return movableObject;
	}

	public void setMovableObject(MovableObject movableObject) {
		this.movableObject = movableObject;
	}
}