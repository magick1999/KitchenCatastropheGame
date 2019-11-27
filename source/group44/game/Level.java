package group44.game;

import group44.entities.LevelObject;
import group44.game.interfaces.IKeyReactive;
import group44.game.interfaces.ILevel;
import group44.game.interfaces.ITimeReactive;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

/**
 * Level maintains all data and event handling in the game.
 * 
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class Level implements ILevel {
    private final static String ERROR_DISPLAY_SIZE_ILLEGAL_ARGUMENT_EXCEPTION = "The displaySize must be odd and >= 3.";

    private LevelObject[][] grid; // The 2D game array
    private int displaySize; // The size of the grid displayed

    // TODO: Replace with reference to the Player
    private int playerPositionX;
    private int playerPositionY;

    /**
     * Creates a new instance of {@link Level}.
     * 
     * @param grid            - 2D array containing all game objects
     * @param displaySize     - size of the grid displayed on screen
     * @param playerPositionX - X index of the {@link Player} in the array
     * @param playerPositionY - Y index of the {@link Player} in the array
     */
    public Level(LevelObject[][] grid, int displaySize, int playerPositionX, int playerPositionY) {
        this.grid = grid;
        if (displaySize < 3 || displaySize > grid[0].length || displaySize > grid[0].length || displaySize % 2 != 1) {
            // TODO: Document this exception in Javadoc
            throw new IllegalArgumentException(Level.ERROR_DISPLAY_SIZE_ILLEGAL_ARGUMENT_EXCEPTION);
        } else {
            this.displaySize = displaySize;
        }
        // TODO: Check legality of values
        this.playerPositionX = playerPositionX;
        this.playerPositionY = playerPositionY;
    }

    /**
     * Checks whether the object is colliding or not.
     * 
     * @param obj - object for to check the collision.
     * 
     * @return result of the collision check.
     */
    public CollisionCheckResult checkCollision(LevelObject obj) {
        if (this.isColiding(obj)) {
            return new CollisionCheckResult(this.grid[obj.getPositionX()][obj.getPositionY()]);
        }
        return new CollisionCheckResult(null);
    }

    /**
     * Checks whether the object is colliding or not.
     * 
     * @param obj - object for which to check the collision.
     * @return true if the object is colliding, otherwise false
     */
    private Boolean isColiding(LevelObject obj) {
        return this.grid[obj.getPositionX()][obj.getPositionY()] != obj;
    }

    /**
     * Draws the cell in the active game area.
     * 
     * @param gc - {@link GraphicsContext} to which the game is drawn
     */
    @Override
    public void draw(GraphicsContext gc) {
        double cellWidth = gc.getCanvas().getWidth() / this.displaySize;
        double cellHeight = gc.getCanvas().getHeight() / this.displaySize;
        double cellSize = Math.min(cellWidth, cellHeight);

        Area activeArea = this.getActiveArea(this.playerPositionX, this.playerPositionY);

        for (int x = activeArea.getX1(); x <= activeArea.getX2(); x++) {
            for (int y = activeArea.getY1(); y <= activeArea.getY2(); y++) {
                this.grid[x][y].draw(gc, x * cellSize, y * cellSize, cellSize, cellSize);
            }
        }
    }

    /**
     * Passes the {@link KeyEvent} to the {@link Player}.
     * 
     * @param event - the {@link KeyEvent}
     */
    @Override
    public void keyDown(KeyEvent event) {
        if (this.grid[this.playerPositionX][this.playerPositionY] instanceof IKeyReactive) {
            ((IKeyReactive) this.grid[this.playerPositionX][this.playerPositionY]).keyDown(event);
        }
    }

    /**
     * Invokes timeTick method on all {@link LevelObject}s in the active game area
     * implementing {@link group44.game.interfaces.ITimeReactive}.
     */
    @Override
    public void timeTick() {
        Area activeArea = this.getActiveArea(this.playerPositionX, this.playerPositionY);

        for (int x = activeArea.getX1(); x < activeArea.getX2(); x++) {
            for (int y = activeArea.getY1(); y < activeArea.getY2(); y++) {
                if (this.grid[x][y] instanceof ITimeReactive) {
                    ((ITimeReactive) this.grid[x][y]).timeTick();
                }
            }
        }
    }

    /**
     * Returns the active {@link Area} of the game.
     * 
     * @param playerPositionX - the position X of the {@link Player}
     * @param playerPositionY - the position Y of the {@link Player}
     * @return the active area of the game
     */
    private Area getActiveArea(int playerPositionX, int playerPositionY) {
        int centerX = this.playerPositionX;
        int centerY = this.playerPositionY;

        if (centerX < this.displaySize / 2) {
            centerX = this.displaySize / 2;
        }
        if (centerY < this.displaySize / 2) {
            centerY = this.displaySize / 2;
        }
        if (centerX > (this.grid[0].length - 1) - this.displaySize / 2) {
            centerX = (this.grid[0].length - 1) - this.displaySize / 2;
        }
        if (centerY > (this.grid[1].length - 1) - this.displaySize / 2) {
            centerY = (this.grid[1].length - 1) - this.displaySize / 2;
        }

        return new Area(centerX - this.displaySize / 2, centerY - this.displaySize / 2, centerX + this.displaySize / 2,
                centerY + this.displaySize / 2);
    }
}