package group44.entities.collectableItems;

import group44.game.Level;

/**
 * Represents a coloured {@link Key} in the game.
 *
 * @author Tomas Svejnoha, Rowan Aldean, Amy Mason
 * @version 1.0
 */
public class Key extends CollectableItem {
    private KeyType keyType;

    /**
     * Creates a new instance of {@link Key} of specific colour type.
     *
     * @param level     - The {@link Level} where the {@link Key} is located.
     * @param type      - Colour type of the {@link Key}.
     */
    public Key(Level level, KeyType type) {
        super(level, type.getTitle(), type.getImagePath());
        this.keyType = type;
    }

    /**
     * Returns the code of the key.
     *
     * @return key code represented as {@link Integer}.
     */
    public int getKeyCode() {
        return this.keyType.getKeyCode();
    }

    /**
     * Represents all possible types of a {@link Key}. {@link KeyType} contains
     * KeyCode and image path.
     */
    public enum KeyType {
        RED(1, "Red key", "group44/resources/keys/red.png"), BLUE(2, "Blue key", "group44/resources/keys/blue.png"),
        GREEN(3, "Green key", "group44/resources/keys/green.png"),
        GOLD(4, "Gold key", "group44/resources/keys/yellow.png");

        private int code; // used to unlock the door (binary operation)
        private String title;
        private String imagePath;

        KeyType(int code, String title, String imagePath) {
            this.code = code;
            this.title = title;
            this.imagePath = imagePath;
        }

        /**
         * Returns the code of the key.
         *
         * @return key code represented as {@link Integer}.
         */
        public int getKeyCode() {
            return this.code;
        }

        /**
         * Returns the title (name) of the key.
         *
         * @return the title.
         */
        public String getTitle() {
            return this.title;
        }

        /**
         * Returns a path to the image of the key.
         *
         * @return a path represented as {@link String}.
         */
        public String getImagePath() {
            return this.imagePath;
        }
    }
}