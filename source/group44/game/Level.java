package group44.game;

import group44.entities.LevelObject;
import group44.entities.MovableObject;
import group44.entities.Player;
import group44.entities.StepableCell;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

/**
 * Level maintains all data and event handling in the game.
 *
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class Level {
    private final static String ERROR_DISPLAY_SIZE_ILLEGAL_ARGUMENT_EXCEPTION = "The displaySize must be odd and >= 3.";

    private int id;
    private LevelObject[][] grid; // The 2D game array
    private int displaySize; // The size of the grid displayed
    private Player player;

    /**
     * Creates a new instance of {@link Level}.
     *
     * @param id          - the Id of the level
     * @param gridWidth   - width of the 2D array
     * @param gridHeight  - height of the 2D array
     * @param displaySize - size of the grid displayed on screen
     * @throws IllegalArgumentException If the display size is less than 3, is not
     *                                  odd, or exceeds a size of a grid.
     */
    public Level(int id, int gridWidth, int gridHeight, int displaySize) {
        this.id = id;
        this.grid = new LevelObject[gridWidth][gridHeight];
        if (displaySize < 3 || displaySize > grid[0].length || displaySize > grid[0].length || displaySize % 2 != 1) {
            throw new IllegalArgumentException(Level.ERROR_DISPLAY_SIZE_ILLEGAL_ARGUMENT_EXCEPTION);
        } else {
            this.displaySize = displaySize;
        }
    }

    /**
     * Returns the Id of the {@link Level}.
     *
     * @return the level id
     */
    public int getId() {
        return this.id;
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
     * Adds {@link LevelObject} in the grid to the specific location.
     *
     * @param x           - position X of the {@link LevelObject}
     * @param y           - position Y of the {@link LevelObject}
     * @param levelObject - the {@link LevelObject} to place in the grid
     */
    public void addLevelObject(int x, int y, LevelObject levelObject) {
        this.grid[x][y] = levelObject;
        if (levelObject instanceof StepableCell) {
            MovableObject object = ((StepableCell) levelObject).getMovableObject();
            if (object instanceof Player) {
                this.player = (Player) object;
            }
        }
    }

    /**
     * Returns a current position of the {@link Player}.
     *
     * @return the {@link Player}'s position
     */
    public Position getPlayerPosition() {
        return new Position(this.player.getPositionX(), this.player.getPositionY());
    }

    /**
     * Draws the cell in the active game area.
     *
     * @param gc - {@link GraphicsContext} to which the game is drawn
     */
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
    public void keyDown(KeyEvent event) {
    	this.player.keyDown(event);
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

    /**
     * Finishes the level
     * */
    public void finish() {
        throw new UnsupportedOperationException(); // TODO: Implement
    }
}