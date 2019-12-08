package group44.entities.collectableItems;

import group44.Constants;
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
     * @param level
     *            - The {@link Level} where the {@link FireBoots} are located.
     * @param imagePath
     *            - Path to the Image representing {@link FireBoots} in the
     *            game.
     */
    public FireBoots(Level level, String imagePath) {
        super(level, Constants.TITLE_FIREBOOTS, imagePath);
    }

    /**
     * Returns a String representation of the {@link FireBoots}.
     *
     * @return the string representation of the {@link FireBoots}.
     */
    @Override
    public String toString() {
        return Constants.TYPE_FIRE_BOOTS + Constants.LEVEL_OBJECT_DELIMITER
                + this.getImagePath();
    }
}
