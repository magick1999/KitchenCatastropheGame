package group44.entities;

import javafx.scene.paint.Color;

/**
 * Represents a colored {@link Key} in the game.
 *
 * @author Tomas Svejnoha, Rowan Aldean
 * @version 1.0
 */
public class Key extends CollectibleItem {
    private KeyType keyType;

    /**
     * Creates a new instance of {@link Key} of specific color type.
     * 
     * @param type      - Color type of the {@link Key}
     * @param positionX - Position X in the game
     * @param positionY - Position Y in the game
     * @param size      - Size of the cell on the screen
     */
    public Key(KeyType type, int positionX, int positionY, int size) {
        super(type.getTitle(), positionX, positionY, size, type.getImagePath());
        this.keyType = type;
    }

    /**
     * Returns the code of the key
     * 
     * @return key code represented as {@link Integer}
     */
    public int getKeyCode() {
        return this.keyType.getKeyCode();
    }

    /**
     * Represents all possible types of a {@link Key}. {@link KeyType} contains
     * KeyCode and image path.
     */
    public enum KeyType {
        // TODO: Add image paths
        RED(1, "Red key", ""), BLUE(2, "Blue key", ""), GREEN(3, "Green key", ""), GOLD(4, "Gold key", "");

        private int code; // used to unlock the door (binary operation)
        private String title;
        private String imagePath;

        KeyType(int code, String title, String imagePath) {
            this.code = code;
            this.title = title;
            this.imagePath = imagePath;
        }

        /**
         * Returns the code of the key
         * 
         * @return key code represented as {@link Integer}
         */
        public int getKeyCode() {
            return this.code;
        }

        /**
         * Returns the title (name) of the key
         * 
         * @return the title
         */
        public String getTitle() {
            return this.title;
        }

        /**
         * Returns a path to the image of the key
         * 
         * @return a path represented as {@link String}
         */
        public String getImagePath() {
            return this.imagePath;
        }
    }
}
