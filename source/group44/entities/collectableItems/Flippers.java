package group44.entities.collectableItems;

import group44.Constants;
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
     * @param level
     *            - The {@link Level} where the object is located.
     * @param imagePath
     *            - Path to the Image representing {@link Flippers} in the game.
     */
    public Flippers(Level level, String imagePath) {
	super(level, Constants.TITLE_FLIPPERS, imagePath);
    }

    /**
     * Returns a String representation of the {@link Flippers}.
     *
     * @return the string representation of the {@link Flippers}.
     */
    @Override
    public String toString() {
	return Constants.TYPE_FLIPPERS + Constants.LEVEL_OBJECT_DELIMITER
		+ this.getImagePath();
    }
}
