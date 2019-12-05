package group44.entities.collectableItems;

import group44.game.Level;

/**
 * Represents {@link FireBoots} in the game.
 *
 * @author Tomas Svejnoha, Amy Mason
 * @version 1.0
 */
public class FireBoots extends CollectableItem {
    /**
     * Creates a new instance of {@link FireBoots} with position, and image.
     *
     * @param level     - The {@link Level} where the {@link FireBoots} are located.
     * @param positionX - Position X in the game.
     * @param positionY - Position Y in the game.
     * @param imagePath - Path to the Image representing {@link FireBoots} in the
     *                  game.
     */
    public FireBoots(Level level, int positionX, int positionY, String imagePath) {
        super(level, "Fireboots", positionX, positionY, imagePath);
    }
}
