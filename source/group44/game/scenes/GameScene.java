package group44.game.scenes;

import javafx.animation.*;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.WritableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import static group44.Constants.*;

import group44.entities.SpriteAnimation;
import group44.game.layoutControllers.MainGameWindowController;

public class GameScene {


    // The canvas in the GUI. This needs to be a global variable
    // (in this setup) as we need to access it in different methods.
    private Canvas canvas;

    // Loaded images
    private Image player = new Image("group44/resources/player.png");
    private Image floor = new Image("group44/resources/floor.png");
    private Image wall = new Image("group44/resources/default_silver_sand.png");

    //The controller asociated with the specific fxml file.
    private MainGameWindowController myController;
    
    //Holds the map images
    private Image[][] map = new Image[40][40];
    
    //The player data.
    private ImageView playerView = new ImageView();
    
    //It showcases the orientation of the player.
    int orientation = 1;
    //The window itself.
    private Stage primaryStage;
    //This boolean lets the player move only after it has finished the previous animation.
    private boolean canMove = true;
    /**
     * This is the main method that loads everything required to draw the scene.
     * @param primaryStage represents the window where the stages are displayed
     * @throws Exception
     */
    public GameScene(Stage primaryStage) {
    	this.primaryStage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/group44/game/layouts/MainGameWindow.fxml"));
        try {
            Parent root = fxmlLoader.load();
            //Setting the stage and adding my custom style to it.
            root.getStylesheets().add("group44/resources/application.css");
            root.setId("root");
            Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
            //Loading the controller
            MainGameWindowController tempController = fxmlLoader.getController();
            setController(tempController);
            //Setting the canvas
            setCanvas(myController.getCanvas());
            //Adding the key listener to the scene.
            scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> processKeyEvent(event));
            drawGame();
            drawMovableObjects();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("Kitchen Catastrophe");
    }
    /**
     * Adding the listeners to the menu buttons.
     * It also makes the player unable to move while the menu is closed.
     * Here the time of the player needs to be stopped aswell.
     */
    private void setUpMenu() {
    	canMove = false;
    	myController.getResumeButton().setOnMouseClicked(this::setUpResume);
    	myController.getRestartButton().setOnMouseClicked(this::setUpRestart);
    	myController.getHomeButton().setOnMouseClicked(this::setUpHome);
    }
    /**
     * Defining behaviour for the click on the resume button.Resumes the game state and the time.
     * @param event This is the event for the click on the resume button.
     */
    private void setUpResume(MouseEvent event) {
        myController.getMenuBox().setVisible(!myController.getMenuBox().isVisible());
    	canMove = true;
    }
    
    /**
     * Defining behaviour for the click on the restart button.Restarts the game and the time.
     * @param event This is the event for the click on the restart button.
     */
    private void setUpRestart(MouseEvent event) {
    	myController.getMenuBox().setVisible(!myController.getMenuBox().isVisible());
    	canMove = true;
    	playerView.setX(GRID_CELL_WIDTH);
    	playerView.setY(GRID_CELL_HEIGHT);
    }
    /**
     * Defining behaviour for the click on the home button.Sends the player to the home screen.
     * @param event This is the event for the click on the restart button.
     */
    private void setUpHome(MouseEvent event) {
    	new MainMenuScene(primaryStage);
    }
    


    /**
     * This method sets the globally available controller to the current controller.
     *
     * @param tempController The current controller.
     */
    private void setController(MainGameWindowController tempController) {
        myController = tempController;
    }

    /**
     * This method sets the globally available canvas to the current canvas.
     *
     * @param canvas The current canvas.
     */
    private void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    /**
     * This method draws every non movable object onto the screen.
     */
    private void drawGame() {
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
    private void drawMovableObjects() {
        playerView.setFitWidth(GRID_CELL_WIDTH);
        playerView.setFitHeight(GRID_CELL_HEIGHT);
        playerView.setImage(player);
        playerView.setY(GRID_CELL_HEIGHT);
        playerView.setX(GRID_CELL_WIDTH);
        //Add the movable objects to the pane destined for them.
        myController.getMovableObjects().getChildren().add(playerView);
    }

    /**
     * This method smoothly translates the player position from playerY and playerX to playerY+yPos and playerX+xPos.
     *
     * @param yPos is the increment or decrement added to playerY.
     * @param xPos is the increment or decrement added to playerX.
     */
    private void smoothTransition(double yPos, double xPos,int orientation) {
    	//Here is created an animation with the node to be moved being playerView, the duration and by how much to move it on the x and y axis.
        final Animation animation = new SpriteAnimation(playerView, Duration.millis(200), xPos, yPos,orientation);
        //This sets the number of animation repetitions to 1 meaning that the animation is played only once.
        animation.setCycleCount(1);
        //This allows the player to perform another move.
        animation.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                canMove = true;
            }
        });
        //Start the animation
        animation.play();
    }

    /**
     * This method handles the keyboard input.
     *
     * @param event Passes in the events from the keyboard.
     */
    private void processKeyEvent(KeyEvent event) {
        switch (event.getCode()) {
            case ESCAPE: {
            	canMove = false;
                //Escape key was pressed. So show the menu.
                myController.getMenuBox().setVisible(!myController.getMenuBox().isVisible());
                //Setting up the menu controls.
                setUpMenu();
                break;
            }
            case LEFT: {
                // Left key was pressed. So move the player right by one cell.The canMove variable is set to false until the end of the animation.
                if ((playerView.getX() - GRID_CELL_HEIGHT) < (28 * GRID_CELL_HEIGHT) && (playerView.getX() - GRID_CELL_HEIGHT) > 0 && canMove) {
                    canMove = false;
                    smoothTransition(0, -GRID_CELL_HEIGHT,2);
                }
                break;
            }
            case RIGHT: {
                // Right key was pressed. So move the player right by one cell.The canMove variable is set to false until the end of the animation.
                if ((playerView.getX() + GRID_CELL_HEIGHT) < (28 * GRID_CELL_HEIGHT) && (playerView.getX() + GRID_CELL_HEIGHT) > 0 && canMove) {
                    canMove = false;
                    smoothTransition(0, GRID_CELL_HEIGHT,3);
                }
                break;
            }
            case UP: {
                //Up key was pressed. So move the player down by one cell.The canMove variable is set to false until the end of the animation.
                if ((playerView.getY() - GRID_CELL_HEIGHT) < (21 * GRID_CELL_HEIGHT) && (playerView.getY() - GRID_CELL_HEIGHT) > 0 && canMove) {
                    canMove = false;
                    smoothTransition(-GRID_CELL_HEIGHT, 0,0);
                }
                break;
            }
            case DOWN: {
                //Down key was pressed. So move the player down by one cell.The canMove variable is set to false until the end of the animation.
                if ((playerView.getY() + GRID_CELL_HEIGHT) < (21 * GRID_CELL_HEIGHT) && (playerView.getY() + GRID_CELL_HEIGHT) > 0 && canMove) {
                    canMove = false;
                    smoothTransition(GRID_CELL_HEIGHT, 0,1);
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
