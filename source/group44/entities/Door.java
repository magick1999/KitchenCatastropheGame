package group44.entities;

import group44.game.Level;

/**
 * Super class for all Door classes.
 * 
 * @author Tomas Svejnoha
 * @version 1.0
 */
public abstract class Door extends StepableCell {
    private boolean isOpen;

    /**
     * Creates a new {@link Door}.
     * 
     * @param level     - The {@link Level} where the {@link Door} is located
     * @param title     - Title of the {@link Door}
     * @param positionX - Position X in the game
     * @param positionY - Position Y in the game
     * @param imagePath - Path to the Image representing the {@link Door} in the
     *                  game
     */
    public Door(Level level, String title, int positionX, int positionY, String imagePath) {
        super(level, title, positionX, positionY, imagePath);
    }

    /**
     * Opens the door.
     * 
     * @param item - The opening {@link CollectableItem}
     * @return true if the door was opened; otherwise false.
     */
    public abstract boolean open(CollectableItem item);

    /**
     * Returns open state of the door.
     * 
     * @return true if the door is open, otherwise false.
     */
    public boolean isOpen() {
        return this.isOpen;
    }

    /**
     * Changes the state of the door. Set true to open the door, set false to close.
     * 
     * @param isOpen - new state of the door.
     */
    protected void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    /**
     * Interacts with {@link MovableObject} that stepped on the {@link Door}.
     * 
     * @param object - The {@link MovableObject} that stepped on cell.
     */
    @Override
    protected void onStepped(MovableObject object) {
        System.out.println("KeyDoor.onStepped(object).");
    }
}