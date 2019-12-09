package group44.entities.cells;

import group44.Constants;
import group44.entities.movableObjects.MovableObject;
import group44.game.Level;

/**
 * Represents a fire in a Game.
 *
 * @author Tomas Svejnoha.
 * @version 1.0
 */
public class Fire extends StepableCell {

    /**
     * Creates a new instance of {@link Fire}.
     *
     * @param level
     *            The {@link Level} where the {@link Fire} is located.
     * @param positionX
     *            The position X of the {@link Fire} in the game.
     * @param positionY
     *            The position Y of the {@link Fire} in the game.
     * @param imagePath
     *            Path to the Image which represents the {@link Fire} in the
     *            game.
     */
    public Fire(Level level, int positionX, int positionY, String imagePath) {
        super(level, Constants.TITLE_FIRE, positionX, positionY, imagePath);
    }

    /**
     * Interacts with {@link MovableObject} which stepped on the cell.
     *
     * @param object
     *            The {@link MovableObject} which stepped in the {@link Fire}.
     */
    @Override
    protected void onStepped(MovableObject object) {
        object.die(this);
    }

    /**
     * Returns a String representation of the {@link Fire}.
     *
     * @return the string representation of the {@link Fire}.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format(PARSE_PATTERN, Constants.TYPE_FIRE,
                this.getPositionX(), this.getPositionY(), this.getImagePath()));

        if (this.getMovableObject() != null) {
            builder.append(",");
            builder.append(this.getMovableObject().toString());
        }

        return builder.toString();
    }
}
