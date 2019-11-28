package group44;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class changes the movable object image each time interpolate is called to create an animation.
 * It also changes the position of movable object each time interpolate is called.
 * @author Mihai
 */
public class SpriteAnimation extends Transition {
	//Random resources loaded to test the animation
    Image player = new Image("group44/resources/player.png");
    Image wall = new Image("group44/resources/default_silver_sand.png");
    //The object on which the animation will be performed.
    private final ImageView imageView;
    //The number of textures to be cycled through.
    private final int count;
    //The container for the images.
    ArrayList<Image> images = new ArrayList<Image>();
    //The x coordinate modifier.
    private double byX;
    //The y coordinate modifier.
    private double byY;
    //Current image displayed.
    private int imageCounter=0;
    //The starting x position.
    private double startingX;
    //The starting y position.
    private double startingY;

    public SpriteAnimation(
            ImageView imageView,
            Duration duration, double x, double y) {
    	//Loading image container.
        images.add(wall);
        images.add(player);
        images.add(wall);
        images.add(player);
        images.add(wall);
        images.add(player);
        this.imageView = imageView;
        startingX = imageView.getX();
        startingY = imageView.getY();
        this.count = images.size();
        this.byX = x;
        this.byY = y;
        //Stating of how long one cycle of this animation should last.
        setCycleDuration(duration);
        //Setting the animation behavior.
        setInterpolator(Interpolator.LINEAR);
    }
    /**
     * This method is called frequently to update the animation.
     * @param k is a fraction in between 0 and 1 that showcases the progress of the animation.
     */
    protected void interpolate(double k) {
    	//Move the player.
        imageView.setX(startingX+(k * byX));
        imageView.setY(startingY+(k * byY));
        //Change the image.
        if (imageCounter<=count) {
            imageView.setImage(images.get(imageCounter));
            imageCounter++;
        }else{
        	imageView.setImage(images.get(0));
            imageCounter = 1;
            }
    }
}