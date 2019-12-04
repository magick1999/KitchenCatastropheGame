package group44.entities.CollectableItems;

import group44.entities.Cells.Ground;
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
     * @param positionX - Position X in the game.
     * @param positionY - Position Y in the game.
     * @param imagePath - Path to the Image representing the {@link Token} in the
     *                  game.
     */
    public Token(Level level, int positionX, int positionY, String imagePath) {
        super(level, "Token", positionX, positionY, imagePath);
    }
}
