package group44.entities.Cells;

import group44.entities.MovableObjects.MovableObject;
import group44.game.Level;

/**
 * Represents a Goal in the game.
 * 
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class Goal extends StepableCell {

    /**
     * Creates a new instance of {@link Goal}.
     * 
     * @param level     - The {@link Level} where the {@link Goal} is located
     * @param positionX - Position X of the object in the {@link group44.game.Level}
     * @param positionY - Position Y of the object in the {@link group44.game.Level}
     * @param imagePath - Path to the Image representing {@link Goal} in the game
     */
    public Goal(Level level, int positionX, int positionY, String imagePath) {
        super(level, "Goal", positionX, positionY, imagePath);
    }

    /**
     * Interacts with {@link MovableObject} that stepped on the {@link Goal}.
     * 
     * @param object - The {@link MovableObject} that stepped on {@link Goal}.
     */
    @Override
    protected void onStepped(MovableObject object) {
        this.getLevel().finish();
    }
}