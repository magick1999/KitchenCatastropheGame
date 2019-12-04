package group44.game;

import group44.entities.Cell;
import group44.entities.Door;
import group44.entities.Ground;
import group44.entities.LevelObject;
import group44.entities.MovableObject;
import group44.entities.Player;
import group44.entities.StepableCell;
import group44.entities.Wall;
import group44.exceptions.CollisionException;
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
public class Level {
    private final static String ERROR_DISPLAY_SIZE_ILLEGAL_ARGUMENT_EXCEPTION = "The displaySize must be odd and >= 3.";
    private final static String ERROR_COLLISION_EXCEPTION = "Unable to place %s in the grid [%d][%d].";

    private int id;
    private Cell[][] grid; // The 2D game array
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
        this.grid = new Cell[gridWidth][gridHeight];
        if (displaySize < 3 || displaySize > gridWidth || displaySize > gridHeight || displaySize % 2 != 1) {
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
        Cell cell = this.grid[obj.getPositionX()][obj.getPositionY()];

        if (cell instanceof Wall) {
            return new CollisionCheckResult(cell);
        } else if (cell instanceof Door && ((Door) cell).isOpen() == false) {
            return new CollisionCheckResult(cell);
        } else if (cell instanceof Ground) {
            Ground ground = (Ground) cell;
            if (ground.isSteppedOn() == true && ground.getMovableObject() != obj) {
                return new CollisionCheckResult(ground.getMovableObject());
            }
        }
        return new CollisionCheckResult(null);
    }

    /**
     * Returns a 2D array with all {@link Cell} in the {@link Level}.
     * 
     * @return 2D array of {@link Cell}s
     */
    public Cell[][] getGrid() {
        /*
         * The only class we will possibly use this method is the SmartTargetingEnemy.
         * Using some kind of repository does not make sense as the SmartTargetingEnemy
         * should decide on its own.
         */
        return this.grid;
    }

    /**
     * Returns a width of the game.
     * 
     * @return width of the game
     */
    public int getGridWidth() {
        return this.grid.length;
    }

    /**
     * Returns a height of the game.
     * 
     * @return height of the game
     */
    public int getGridHeight() {
        return this.grid[0].length;
    }

    /**
     * Adds {@link Cell} in the grid to the specific location.
     * 
     * @param x    - position X of the {@link Cell}
     * @param y    - position Y of the {@link Cell}
     * @param cell - the {@link Cell} to place in the grid
     * @throws CollisionException when trying to rewrite existing cell in the grid
     */
    public void addCell(int x, int y, Cell cell) throws CollisionException {
        if (this.grid[x][y] != null) {
            throw new CollisionException(String.format(Level.ERROR_COLLISION_EXCEPTION, cell.getTitle(), x, y));
        }

        this.grid[x][y] = cell;
        if (cell instanceof StepableCell) {
            MovableObject object = ((StepableCell) cell).getMovableObject();
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