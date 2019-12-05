package group44.game;

import java.awt.*;
import java.util.ArrayList;

import group44.entities.SpriteAnimation;
import group44.entities.cells.Cell;
import group44.entities.cells.StepableCell;
import group44.entities.movableObjects.Enemy;
import group44.entities.movableObjects.MovableObject;
import group44.entities.movableObjects.Player;
import group44.exceptions.CollisionException;
import group44.game.layoutControllers.MainGameWindowController;
import group44.game.scenes.GameScene;
import javafx.animation.Animation;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import static group44.Constants.GRID_CELL_HEIGHT;
import static group44.Constants.GRID_CELL_WIDTH;
import static java.lang.Boolean.TRUE;

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
    private ArrayList<Enemy> enemies;
    private ImageView playerView;
    private MainGameWindowController mainGameWindowController;
    private boolean canMove=TRUE;
    private int orientation=0;
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

        this.enemies = new ArrayList<>();

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
     * Returns a 2D array with all {@link Cell} in the {@link Level}.
     *
     * @return 2D array of {@link Cell}s
     */
    public Cell[][] getGrid() {
        /*
         * The only class we will possibly use this method is the SmartTargetingEnemy.
         * Using some kind of repository does not make sence as the SmartTargetingEnemy
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
        	StepableCell stepableCell = ((StepableCell) cell);
        	MovableObject object = stepableCell.getMovableObject();
            if (object instanceof Player) {
                this.player = (Player) object;
                stepableCell.stepOff(); // HACK: INTEGRATION
                playerView.setX(player.getPositionX()-player.getVelocityX());
                playerView.setY(player.getPositionY()-player.getVelocityY());
                playerView.setImage(player.getImage());
                playerView.setFitWidth(GRID_CELL_WIDTH);
                playerView.setFitHeight(GRID_CELL_HEIGHT);
                this.mainGameWindowController = new FXMLLoader(getClass().getResource("/group44/game/layouts/MainGameWindow.fxml")).getController();
                mainGameWindowController.getMovableObjects().getChildren().add(playerView);
            }
            if (object instanceof Enemy) {
            	this.enemies.add((Enemy) object);
            	stepableCell.stepOff(); // HACK: INTEGRATION
            }
        }
    }

    /**
     * Returns the instance of {@link Player} in the game.
     *
     * @return the player.
     */
    public Player getPlayer() {
    	return this.player;
    }

    /**
     * Returns all enemies in the game.
     *
     * @return list of enemies.
     */
    public ArrayList<Enemy> getEnemies() {
    	return this.enemies;
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
                if(x==player.getPositionX()&&y==player.getPositionY()) {
                    if(player.getVelocityX()!=0){
                        if(player.getVelocityX()==-1)
                            orientation = 2;
                        else
                            orientation = 3;
                    }else{
                        if(player.getVelocityY() == -1){
                            orientation = 0;
                        }
                        else
                            orientation = 1;
                    }
                    final Animation animation = new SpriteAnimation(playerView, Duration.millis(200), playerView.getX(), playerView.getY(),orientation);
                    animation.setCycleCount(1);
                    animation.setOnFinished(event -> canMove = true);
                    animation.play();

                }else{
                    this.grid[x][y].draw(gc, x * cellSize, y * cellSize, cellSize, cellSize);}
                }
        }
    }

    /**
     * Passes the {@link KeyEvent} to the {@link Player}. Also moves all enemies.
     *
     * @param event - the {@link KeyEvent}
     */
    public void keyDown(KeyEvent event) {
        if(event.getCode() == KeyCode.ESCAPE){
            	canMove = false;
                //Escape key was pressed. So show the menu.
                mainGameWindowController.getMenuBox().setVisible(!mainGameWindowController.getMenuBox().isVisible());
                //Setting up the menu controls.
//                GameScene.setUpMenu();
            }
    	this.player.keyDown(event);
    	this.moveEnemies();
    }

    /**
     * Moves all enemies in the game.
     */
    private void moveEnemies() {
    	for (Enemy enemy : this.enemies) {
    		enemy.move();
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

    /**
     * Finished the current level.
     */
    public void finish() {
    	throw new UnsupportedOperationException();
    }
}