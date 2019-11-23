package group44.entities;
import javafx.scene.image.Image;

/**
 * Fireboot class which sets its image in constructor.
 *
 * @author Rowan Aldean
 * @version 1.0
 */
public class FireBoot extends CollectibleItem {

    /**
     * Constructs FireBoot object and sets image to a fireboot icon and sets name.
     */
    public FireBoot(){
        this.setTitle("fireboot");
        String bootPath = "https://www.clipartroo.com/images/93/firefighter-boots-clipart-93602.png";
        Image boot = new Image(bootPath, 10, 10, true, true);
        this.setImage(boot);
    }
}
