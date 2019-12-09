package group44.entities.collectableItems;

import group44.Constants;
import group44.entities.cells.Ground;
import group44.game.Level;

/**
 * Represents a {@link Token} in the game.
 *
 * @author Tomas Svejnoha.
 * @version 1.0
 */
public class Token extends CollectableItem {
    /**
     * Creates a new instance of {@link Token} with position, and image.
     *
     * @param level
     *            The {@link Level} where the {@link Ground} is located.
     * @param imagePath
     *            Path to the Image representing the {@link Token} in the game.
     */
    public Token(Level level, String imagePath) {
        super(level, Constants.TITLE_TOKEN, imagePath);
    }

    /**
     * Returns a string representation of the {@link Token}.
     *
     * @return a string representation of the token.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.TYPE_TOKEN);
        sb.append(Constants.LEVEL_OBJECT_DELIMITER);
        sb.append(this.getImagePath());
        return sb.toString();
    }
}
