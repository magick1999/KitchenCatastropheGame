package group44.entities.Cells;

import group44.game.Level;

/**
 * Represents a wall in the game. Player can't step on the wall.
 *
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class Wall extends Cell {

    /**
     * Creates a new instance of {@link Wall}.
     *
     * @param level     - The {@link Level} where the {@link Wall} is located.
     * @param positionX - Position X in the {@link group44.game.Level}.
     * @param positionY - Position Y in the {@link group44.game.Level}.
     * @param imagePath - Path to the Image representing {@link Wall} in the game.
     */
    public Wall(Level level, int positionX, int positionY, String imagePath) {
        super(level, "Wall", positionX, positionY, imagePath);
    }
}