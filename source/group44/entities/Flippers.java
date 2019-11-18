package group44.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Flippers extends CollectibleItem {

    public Flippers(){
        String flipperPath = "http://icons.iconarchive.com/icons/visualpharm/vacation/256/flippers-icon.png";
        Image flipper = new Image(flipperPath, 10, 10, true, true);
        this.setImage(flipper);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(this.getImage(), this.getPositionX(), this.getPositionY());
    }

}
