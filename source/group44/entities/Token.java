package group44.entities;

/**
 * Represents a {@link Token} in the game.
 * 
 * @author Tomas Svejnoha, Rowan Aldean
 * @version 1.0
 */
public class Token extends CollectibleItem {
    /**
     * Creates a new instance of {@link Token} with position, size, and image.
     * 
     * @param positionX - Position X in the game
     * @param positionY - Position Y in the game
     * @param size      - Size of the cell on the screen
     * @param imagePath - Image path of the instance
     */
    public Token(int positionX, int positionY, int size, String imagePath) {
        super("Token", positionX, positionY, size, imagePath);
    }
}
