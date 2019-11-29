package group44.entities;
import java.util.ArrayList;
import group44.game.Level;

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
     * @param size      - Size of the cell on the screen
     * @param imagePath - Image path of the instance
     */
    public Door(Level level, String title, int positionX, int positionY, int size, String imagePath) {
    	super(level, title, positionX, positionY, imagePath);
    }

    /**
     * Opens the door.
     * 
     * @param items - pass in every collectable item that the player has.
     */
    public abstract void open(ArrayList <CollectableItem> items);
    	
    /**
     * Returns open state of the door.
     * 
     * @return true if the door is open, otherwise false.
     */
    public boolean isOpen() {
        return this.isOpen;
    }
}