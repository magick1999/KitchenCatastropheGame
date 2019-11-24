package group44.entities;

/**
 * Abstract class from which all cells inherit.
 * 
 * @author Tomas Svejnoha, Rowan Aldean
 * @version 1.0
 */
public abstract class Cell extends LevelObject {

    /**
     * Creates a new {@link Cell}.
     * 
     * @param title     - Title of the object
     * @param positionX - Position X in the game
     * @param positionY - Position Y in the game
     * @param size      - Size of the cell on the screen
     * @param imagePath - Image path of the instance
     */
    public Cell(String title, int positionX, int positionY, int size, String imagePath) {
        super(title, positionX, positionY, size, imagePath);
    }
}
