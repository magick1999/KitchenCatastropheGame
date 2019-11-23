package group44.entities;

import javafx.scene.paint.Color;

/**
 * Key class which holds color (JavaFx Color) of the key..
 *
 * @author Rowan Aldean
 * @version 1.0
 */
public class Key extends CollectibleItem {
    private Color keyColor;
    //The possible colors a key can be.
    public enum keyColors {
        RED(Color.RED),
        BLUE(Color.BLUE),
        GREEN(Color.GREEN),
        GOLD(Color.GOLD);

        private Color color;

        keyColors(Color someColor) {
            this.color = someColor;
        }

        public Color getColor() {
            return color;
        }
    }

    /**
     * A key constructor with no parameters defaults to Yellow/Gold.
     *
     */
    public Key(){
        this.keyColor = keyColors.GOLD.getColor();//default gold key.
    }

    /**
     * A Key constructor which takes some keyColor enum as a parameter and constructs a corresponding color key.
     * @param givenColor the keyColors enum type color to create.
     */
    public Key(keyColors givenColor) {
        this.keyColor = givenColor.getColor();
    }

    /**
     * Gets the JavaFX Color of the Key object
     * @return the color attribute of the Key object.
     */
    public Color getKeyColor() {
        return keyColor;
    }

}
