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
     * Opens the door.
     */
    public abstract void open(); // TODO: Takes Key

    /**
     * Returns open state of the door.
     * 
     * @return true if the door is open, otherwise false.
     */
    public boolean isOpen() {
        return this.isOpen;
    }
}