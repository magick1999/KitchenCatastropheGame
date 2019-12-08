package group44.entities.cells;

import group44.entities.LevelObject;
import group44.game.Level;

/**
 * Abstract class from which all cells inherit.
 *
 * @author Tomas Svejnoha, Rowan Aldean
 * @version 1.0
 */
public abstract class Cell extends LevelObject {
    static final String PARSE_PATTERN = "%s,%d,%d,%s";

    /**
     * Creates a new {@link Cell}.
     *
     * @param level
     *            - The {@link Level} where the {@link Cell} is located.
     * @param title
     *            - Title of the {@link Cell}.
     * @param positionX
     *            - Position X in the game.
     * @param positionY
     *            - Position Y in the game.
     * @param imagePath
     *            - Path to the Image representing the {@link Cell} in the game.
     */
    public Cell(Level level, String title, int positionX, int positionY,
	    String imagePath) {
	super(level, title, positionX, positionY, imagePath);
    }
}
