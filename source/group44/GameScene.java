package group44;

import javafx.animation.*;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.WritableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import static group44.Constants.*;

public class GameScene {


    // The canvas in the GUI. This needs to be a global variable
    // (in this setup) as we need to access it in different methods.
    // We could use FXML to place code in the controller instead.
    private Canvas canvas;

    // Loaded images
    Image player = new Image("group44/resources/player.png");
    Image floor = new Image("group44/resources/floor.png");
    Image wall = new Image("group44/resources/default_silver_sand.png");

    // X and Y coordinate of player
    int playerX = GRID_CELL_WIDTH;
    int playerY = GRID_CELL_HEIGHT;
    //An array containing the map textures.
    ImageView[][] mapTextures = new ImageView[40][40];

    //The controller asociated with the specific fxml file.
    MainGameWindowController myController;
    //Might be redundant, does the same as above, will remove in a future commit.
    Image[][] map = new Image[40][40];
    //The player data.
    ImageView playerView = new ImageView();


    boolean canMove = true;
    /**
     * This is the main method that loads everything required to draw the scene.
     *
     * @param primaryStage Can't really properly tell what this is, will clarify this in a future commit.
     * @throws Exception
     */
    private Stage stage;

    public GameScene(Stage primaryStage) {
        this.stage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/group44/MainGameWindow.fxml"));
        try {
            Parent root = fxmlLoader.load();
            //Setting the stage and adding my custom style to it.
            root.getStylesheets().add("group44/resources/application.css");
            root.setId("pane");
            Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
            //Adding the key listener to the scene.
            scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> processKeyEvent(event));
            MainGameWindowController tempController = fxmlLoader.getController();
            setController(tempController);
            setCanvas(myController.getCanvas());
            drawGame();
            drawMovableObjects();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("Hello World");
    }

    //This method will have to be rewritten.
    public void restartGame(int startX, int startY) {
        // We just move the player to cell (2, 2)
        playerX = 25;
        playerY = 25;
        drawGame();
    }


    /**
     * This method sets the globally available controller to the current controller.
     *
     * @param tempController The current controller.
     */
    public void setController(MainGameWindowController tempController) {
        myController = tempController;
    }

    /**
     * This method sets the globally available canvas to the current canvas.
     *
     * @param canvas The current canvas.
     */
    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    /**
     * This method draws every non movable object onto the screen.
     */
    public void drawGame() {
        // Get the Graphic Context of the canvas. This is what we draw on.
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Clear canvas
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        //Create a map for testing
        for (int x = 0; x <= 22; x++) {
            for (int j = 0; j <= 29; j++) {
                if (x == 0 || x == 21 || j == 0 || j == 28)
                    map[x][j] = wall;
                else
                    map[x][j] = floor;
            }
        }
        //Drawing the map
        for (int i = 0; i <= 22; ++i) {
            for (int j = 0; j <= 29; ++j) {
                gc.drawImage(map[i][j], j * GRID_CELL_WIDTH, i * GRID_CELL_HEIGHT);
            }
        }

    }

    /**
     * This method draws the movable objects onto a pane, above the canvas
     * so that the movement can be rendered smoothly.
     */
    public void drawMovableObjects() {
        playerView.setFitWidth(GRID_CELL_WIDTH);
        playerView.setFitHeight(GRID_CELL_HEIGHT);
        playerView.setImage(player);
        playerView.setY(playerY);
        playerView.setX(playerX);
        //Add the movable objects to the pane destined for them.
        myController.getMovableObjects().getChildren().add(playerView);
    }

    /**
     * This method smoothly translates the player position from playerY and playerX to playerY+yPos and playerX+xPos.
     *
     * @param yPos is the increment or decrement added to playerY.
     * @param xPos is the increment or decrement added to playerX.
     */
    public void smoothTransition(double yPos, double xPos) {
//        TranslateTransition tt = new TranslateTransition(Duration.millis(200),playerView);
//        tt.setByX(xPos);
//        tt.setByY(yPos);
//        tt.setOnFinished(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                canMove = true;
//                playerY = (int)(playerY+yPos);
//                playerX = (int)(playerX+xPos);
//            }
//        });
//        tt.play();
        final Animation animation = new SpriteAnimation(playerView, Duration.millis(200), xPos, yPos);
        animation.setCycleCount(1);
        animation.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                canMove = true;
                playerY = (int) (playerY + yPos);
                playerX = (int) (playerX + xPos);
            }
        });
        animation.play();
    }

    /**
     * This method handles the keyboard input.
     *
     * @param event Passes in the events from the keyboard.
     */
    public void processKeyEvent(KeyEvent event) {
        switch (event.getCode()) {
            case ESCAPE: {
                //Escape key was pressed. So show the menu.
                myController.getMenuBox().setVisible(!myController.getMenuBox().isVisible());
                break;
            }
            case LEFT: {
                // Left key was pressed. So move the player right by one cell.
                if ((playerX - GRID_CELL_HEIGHT) < (28 * GRID_CELL_HEIGHT) && (playerX - GRID_CELL_HEIGHT) > 0 && canMove) {
                    canMove = false;
                    smoothTransition(0, -GRID_CELL_HEIGHT);
                }
                break;
            }
            case RIGHT: {
                // Right key was pressed. So move the player right by one cell.
                if ((playerX + GRID_CELL_HEIGHT) < (28 * GRID_CELL_HEIGHT) && (playerX + GRID_CELL_HEIGHT) > 0 && canMove) {
                    canMove = false;
                    smoothTransition(0, GRID_CELL_HEIGHT);
                }
                break;
            }
            case UP: {
                //Up key was pressed. So move the player down by one cell.
                if ((playerY - GRID_CELL_HEIGHT) < (21 * GRID_CELL_HEIGHT) && (playerY - GRID_CELL_HEIGHT) > 0 && canMove) {
                    canMove = false;
                    smoothTransition(-GRID_CELL_HEIGHT, 0);
                }
                break;
            }
            case DOWN: {
                //Down key was pressed. So move the player down by one cell.
                if ((playerY + GRID_CELL_HEIGHT) < (21 * GRID_CELL_HEIGHT) && (playerY + GRID_CELL_HEIGHT) > 0 && canMove) {
                    canMove = false;
                    smoothTransition(GRID_CELL_HEIGHT, 0);
                }
                break;
            }
            default:
                // Do nothing
                break;
        }

        // Redraw game as the player may have moved.
        drawGame();
        // Consume the event. This means we mark it as dealt with. This stops other GUI nodes (buttons etc) responding to it.
        event.consume();
    }

}
