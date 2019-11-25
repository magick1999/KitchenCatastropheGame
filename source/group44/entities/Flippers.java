package group44.entities;

/**
 * Represents {@link Flippers} in the game.
 *
 * @author Tomas Svejnoha, Rowan Aldean
 * @version 1.0
 */
public class Flippers extends CollectableItem {
    /**
     * Creates a new instance of {@link Flippers} with position, size, and image.
     * 
     * @param positionX - Position X in the game
     * @param positionY - Position Y in the game
     * @param size      - Size of the cell on the screen
     * @param imagePath - Image path of the instance
     */
    public Flippers(int positionX, int positionY, int size, String imagePath) {
        super("Flippers", positionX, positionY, size, imagePath);
    }
}
