package group44.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class FireBoot extends CollectibleItem {

    public FireBoot(){
        String bootPath = "https://www.clipartroo.com/images/93/firefighter-boots-clipart-93602.png";
        Image boot = new Image(bootPath, 10, 10, true, true);
        this.setImage(boot);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(this.getImage(), this.getPositionX(), this.getPositionY());
    }

}
