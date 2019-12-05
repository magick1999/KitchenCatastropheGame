package group44.entities.collectableItems;

import group44.game.Level;

/**
 * Represents {@link Flippers} in the game.
 *
 * @author Tomas Svejnoha, Rowan Aldean, Amy Mason
 * @version 1.0
 */
public class Flippers extends CollectableItem {
    /**
     * Creates a new instance of {@link Flippers} with position, and image.
     *
     * @param level     - The {@link Level} where the object is located.
     * @param positionX - Position X in the game.
     * @param positionY - Position Y in the game.
     * @param imagePath - Path to the Image representing {@link Flippers} in the
     *                  game.
     */
    public Flippers(Level level, int positionX, int positionY, String imagePath) {
        super(level, "Flippers", positionX, positionY, imagePath);
    }
}
