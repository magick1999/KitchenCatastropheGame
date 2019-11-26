package group44.game;

import group44.entities.LevelObject;
import group44.entities.MovableObject;
import group44.entities.Player;
import group44.entities.StepableCell;
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
    private Player player;

    /**
     * Creates a new instance of {@link Level}.
     * 
     * @param grid        - 2D array containing all game objects
     * @param displaySize - size of the grid displayed on screen
     * @param player      - The instance of {@link Player}
     * @throws IllegalArgumentException If the display size is less than 3, is not
     *                                  odd, or exceeds a size of a grid.
     */
    public Level(LevelObject[][] grid, int displaySize, Player player) {
        this.grid = grid;
        if (displaySize < 3 || displaySize > grid[0].length || displaySize > grid[0].length || displaySize % 2 != 1) {
            throw new IllegalArgumentException(Level.ERROR_DISPLAY_SIZE_ILLEGAL_ARGUMENT_EXCEPTION);
        } else {
            this.displaySize = displaySize;
        }
        this.player = player;
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
     * Returns a 2D array with all {@link LevelObject} in the {@link Level}.
     * 
     * @return 2D array of {@link LevelObject}s
     */
    public LevelObject[][] getGrid() {
        /*
         * The only class we will possibly use this method is the SmartTargetingEnemy.
         * Using some kind of repository does not make sence as the SmartTargetingEnemy
         * should decide on its own.
         */
        return this.grid;
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

        Area activeArea = this.getActiveArea();

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
        if (this.grid[this.player.getPositionX()][this.player.getPositionY()] instanceof IKeyReactive) {
            ((IKeyReactive) this.grid[this.player.getPositionX()][this.player.getPositionY()]).keyDown(event);
        }
    }

    /**
     * Invokes timeTick method on all {@link LevelObject}s in the active game area
     * implementing {@link group44.game.interfaces.ITimeReactive}.
     */
    @Override
    public void timeTick() {
        Area activeArea = this.getActiveArea();

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
     * @return the active area of the game
     */
    private Area getActiveArea() {
        int centerX = this.player.getPositionX();
        int centerY = this.player.getPositionY();

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