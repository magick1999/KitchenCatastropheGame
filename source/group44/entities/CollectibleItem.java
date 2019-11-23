package group44.entities;

import javafx.scene.canvas.GraphicsContext;

/**
 * Abstract class for all collectible items in the game.
 * 
 * @author Rowan Aldean
 * @version 1.0
 */
public abstract class CollectibleItem extends LevelObject {

    /**
     * Draws collectible using drawImage.
     * @param gc The graphics context that is being drawn to.
     */
    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(this.getImage(), this.getPositionX(), this.getPositionY());
    }
}
