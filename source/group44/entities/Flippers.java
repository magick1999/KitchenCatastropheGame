package group44.entities;
import javafx.scene.image.Image;

/**
 * Flipper class which sets its image in constructor.
 *
 * @author Rowan Aldean
 * @version 1.0
 */
public class Flippers extends CollectibleItem {

    /**
     * Constructs Flippers object and sets image to a flipper icon and sets name.
     */
    public Flippers(){
        this.setTitle("flippers");
        String flipperPath = "http://icons.iconarchive.com/icons/visualpharm/vacation/256/flippers-icon.png";
        Image flipper = new Image(flipperPath, 10, 10, true, true);
        this.setImage(flipper);
    }
}
