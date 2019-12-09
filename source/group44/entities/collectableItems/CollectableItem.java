package group44.entities.collectableItems;

import group44.entities.LevelObject;
import group44.game.Level;

/**
 * Abstract class for all collectable items in the game.
 *
 * @author Tomas Svejnoha, Amy Mason
 * @version 1.0
 */
public abstract class CollectableItem extends LevelObject {

    /**
     * Creates a new {@link CollectableItem}.
     *
     * @param level
     *            The {@link Level} where the {@link CollectableItem} is
     *            located.
     * @param title
     *            Title of the {@link CollectableItem}.
     */
    public CollectableItem(Level level, String title) {
        super(level, title, 0, 0);
    }

    /**
     * Creates a new {@link CollectableItem}.
     *
     * @param level
     *            The {@link Level} where the {@link CollectableItem} is
     *            located.
     * @param title
     *            Title of the {@link CollectableItem}.
     * @param imagePath
     *            Path the the Image representing {@link CollectableItem} in the
     *            game.
     */
    public CollectableItem(Level level, String title, String imagePath) {
        super(level, title, 0, 0, imagePath);
    }
}
