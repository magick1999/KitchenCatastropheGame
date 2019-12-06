package group44.entities.collectableItems;

import group44.entities.cells.Ground;
import group44.game.Level;

/**
 * Represents a {@link Token} in the game.
 *
 * @author Tomas Svejnoha, Rowan Aldean, Amy Mason
 * @version 1.0
 */
public class Token extends CollectableItem {
    /**
     * Creates a new instance of {@link Token} with position, and image.
     *
     * @param level     - The {@link Level} where the {@link Ground} is located.
     * @param imagePath - Path to the Image representing the {@link Token} in the
     *                  game.
     */
    public Token(Level level, String imagePath) {
        super(level, "Token", imagePath);
    }
}
