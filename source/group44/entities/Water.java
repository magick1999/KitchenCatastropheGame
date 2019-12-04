package group44.entities;

import group44.game.Level;

/**
 * Represents a water in the game.
 *
 * @author Tomas Svejnoha, Amy Mason
 * @version 1.0
 */
public class Water extends StepableCell {

    /**
     * Creates a new instance of {@link Water}.
     *
     * @param level     - The {@link Level} where the {@link Water} is located.
     * @param positionX - Position X of the {@link Water} in the game.
     * @param positionY - Position Y of the {@link Water} in the game.
     * @param imagePath - Path to the Image which represents the {@link Water} in
     *                  the game.
     */
    public Water(Level level, int positionX, int positionY, String imagePath) {
        super(level, "Water", positionX, positionY, imagePath);
    }

    /**
     * Interacts with {@link MovableObject} which stepped on the cell.
     *
     * @param object - The {@link MovableObject} which stepped in the {@link Water}.
     */
    @Override
    protected void onStepped(MovableObject object) {
        object.die(this);
    }
}