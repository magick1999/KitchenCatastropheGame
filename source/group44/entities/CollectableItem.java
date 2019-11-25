package group44.entities;

import javafx.scene.canvas.GraphicsContext;

/**
 * Abstract class for all collectable items in the game.
 * 
 * @author Tomas Svejnoha, Rowan Aldean
 * @version 1.0
 */
public abstract class CollectableItem extends LevelObject {

    /**
     * Creates a new {@link CollectableItem}.
     * 
     * @param title     - Title of the object
     * @param positionX - Position X in the game
     * @param positionY - Position Y in the game
     * @param size      - Size of the cell on the screen
     * @param imagePath - Image path of the instance
     */
    public CollectableItem(String title, int positionX, int positionY, int size, String imagePath) {
        super(title, positionX, positionY, size, imagePath);
    }
}
