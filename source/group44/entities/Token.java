package group44.entities;

/**
 * Represents a {@link Token} in the game.
 * 
 * @author Tomas Svejnoha, Rowan Aldean
 * @version 1.0
 */
public class Token extends CollectableItem {
    /**
     * Creates a new instance of {@link Token} with position, and image.
     * 
     * @param positionX - Position X in the game
     * @param positionY - Position Y in the game
     * @param imagePath - Image path of the instance
     */
    public Token(int positionX, int positionY, String imagePath) {
        super("Token", positionX, positionY, imagePath);
    }
}
