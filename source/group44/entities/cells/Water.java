package group44.entities.cells;
import group44.Constants;
import group44.entities.movableObjects.MovableObject;
import group44.game.Level;

/**
 * Represents a water in the game.
 *
 * @author Tomas Svejnoha, Amy Mason
 * @version 1.0
 */
public class Water extends StepableCell {
    private static final String PARSE_PATTERN = "%s,%d,%d,%s";

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

    /**
     * Returns a String representation of the Ground.
     *
     * @return the string representation of the ground.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format(PARSE_PATTERN, Constants.TYPE_WATER, this.getPositionX(), this.getPositionY(),
                this.getImagePath()));

        return builder.toString();
    }
}