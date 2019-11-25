package group44.entities;

import javafx.scene.canvas.GraphicsContext;

/**
 * Abstract class for all collectible items in the game.
 * 
 * @author Tomas Svejnoha, Rowan Aldean
 * @version 1.0
 */
public abstract class CollectibleItem extends LevelObject {

    /**
     * Creates a new {@link CollectibleItem}.
     * 
     * @param title     - Title of the object
     * @param positionX - Position X in the game
     * @param positionY - Position Y in the game
     * @param size      - Size of the cell on the screen
     * @param imagePath - Image path of the instance
     */
    public CollectibleItem(String title, int positionX, int positionY, int size, String imagePath) {
        super(title, positionX, positionY, size, imagePath);
    }
}
