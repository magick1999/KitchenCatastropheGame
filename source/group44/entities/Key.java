package group44.entities;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Key class which sets a corresponding image dependent on the color ID of a Key (derived upon instantiation of Key object).
 *
 * @author Rowan Aldean
 * @version 1.0
 */
public class Key extends CollectibleItem {
    private int keyColorID; //The color id for the key (1-4)
    private Color keyColor; //The color corresponding to the id (R,G,B,Gold)

    /**
     * A key constructor with no parameters defaults to Yellow/Gold.
     *
     */
    public Key(){
        deriveKey(4);//4 (anything not 1,2,3) is default gold key.
    }

    /**
     * A Key constructor which takes some keyColorID as a parameter and constructs a corresponding color key.
     * @param keyColorID the corresponding ID for a key of some color e.g 1 is RED 2 is GREEN 3 is BLUE
     */
    public Key(int keyColorID) {
        deriveKey(keyColorID);
    }

    /**
     * Helper function which takes some int ID and maps the current Key object to the corresponding color.
     * @param keyColorID the intended ID to set the Key color to.
     */
    private void deriveKey(int keyColorID){
        String keyPath;
        switch (keyColorID) {
            case 1:
                this.keyColor = Color.RED;
                keyPath = "http://pixelartmaker.com/art/da230f59bea5069.png";
                break;
            case 2:
                this.keyColor = Color.GREEN;
                keyPath = "http://pixelartmaker.com/art/75afa0a33c68872.png";
                break;
            case 3:
                this.keyColor = Color.BLUE;
                keyPath = "http://pixelartmaker.com/art/df24dc0ad11402a.png";
                break;
            default:
                System.out.println("Default gold key object created");
                this.keyColor = Color.GOLD;
                keyPath = "http://pixelartmaker.com/art/72369a5aede8489.png";
        }
        this.title = keyColor.toString() + " key";
        Image key = new Image(keyPath, 10, 10, true, true);
        this.setImage(key);
    }

    /**
     * Gets the ID of a Key object corresponding to each color.
     * @return the ID of the Key object.
     */
    public int getKeyColorID() {
        return keyColorID;
    }

    /**
     * Gets the JavaFX Color of the Key object
     * @return the color attribute of the Key object.
     */
    public Color getKeyColor() {
        return keyColor;
    }

}
