package group44.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Abstract class for all collectible items in the game.
 * 
 * @author Tomas Svejnoha, Rowan Aldean
 * @version 1.0
 */
public abstract class CollectibleItem extends LevelObject {

    /**
     * Constructs a basic collectible object and sets image to a basic star icon.
     */
    public CollectibleItem() {
        //THIS IS NOT STRICTLY NEEDED AS JAVA GIVES US AN EMPTY CONSTRUCTOR
    }

    /**
     * Draws collectible using drawImage.
     * @param gc The graphics context that is being drawn to.
     */
    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(this.getImage(), this.getPositionX(), this.getPositionY());
    }
}
