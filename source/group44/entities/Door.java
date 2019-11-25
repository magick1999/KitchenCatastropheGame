package group44.entities;

/**
 * Super class for all Door lasses.
 * 
 * @author Tomas Svejnoha
 * @version 1.0
 */
public abstract class Door extends StepableCell {
    private Boolean isOpen;

    /**
     * Creates a new {@link Door}.
     * 
     * @param title     - Title of the object
     * @param positionX - Position X in the game
     * @param positionY - Position Y in the game
     * @param imagePath - Image path of the instance
     */
    public Door(String title, int positionX, int positionY, String imagePath) {
        super(title, positionX, positionY, imagePath);
    }

    /**
     * Opens the door.
     */
    public abstract void open(CollectableItem item);
    	// TODO: Takes Key ;

    /**
     * Returns open state of the door.
     * 
     * @return true if the door is open, otherwise false.
     */
    public boolean isOpen() {
        return this.isOpen;
    }
}