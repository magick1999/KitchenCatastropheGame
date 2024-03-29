package group44.game.interfaces;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

/**
 * An interface for {@link group44.game.Level}.
 * 
 * @author Tomas Svejnoha
 * @version 1.0
 */
public interface ILevel {
    /**
     * Draws the game.
     * 
     * @param gc - {@link GraphicsContext} to which the game is drawn
     */
    void draw(GraphicsContext gc);

    /**
     * Handles the KeyDown event
     * 
     * @param event - the {@link KeyEvent}
     */
    void keyDown(KeyEvent event);

    /**
     * Method executed on every time tick.
     */
    void timeTick();
}