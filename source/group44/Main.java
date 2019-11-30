package group44;

<<<<<<< HEAD
import group44.entities.LevelObject;
import group44.game.Level;
import javafx.animation.*;
=======
>>>>>>> upstream/dev
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.*;

import java.io.File;

import static group44.Constants.*;

/**
 * This class just redirects the player to the main menu stage.
 * @author Mihai
 *
 */
public class Main extends Application {
<<<<<<< HEAD


    // The canvas in the GUI. This needs to be a global variable
    // (in this setup) as we need to access it in different methods.
    // We could use FXML to place code in the controller instead.
    private Canvas canvas;

    // Loaded images
    Image player = new Image("group44/resources/player.png",GRID_CELL_HEIGHT,GRID_CELL_WIDTH,false,false);
    Image floor = new Image("group44/resources/floorNew.png",GRID_CELL_HEIGHT,GRID_CELL_WIDTH,false,false);
    Image wall = new Image("group44/resources/WallCounter.png",GRID_CELL_HEIGHT,GRID_CELL_WIDTH,false,false);

    // X and Y coordinate of player
    int playerX = GRID_CELL_WIDTH;
    int playerY = GRID_CELL_HEIGHT;
    
    //The controller associated with the specific fxml file.
    MainGameWindowController myController;
   
  //An array containing the map textures.
    Image[][] map = new Image[40][40];
    
    //The player data.
    ImageView playerView = new ImageView();
    //This variable allows for the player to move only after the previous animation has finished.
    boolean canMove = true;

    //Testing with fileReader
    Level testLevel = FileHandler.readLevelFile("level1.txt");



    /**
     * This is the main method that loads everything required to draw the scene.
     * @param primaryStage Can't really properly tell what this is, will clarify this in a future commit.
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainGameWindow.fxml"));
        Parent root = fxmlLoader.load();
        MainGameWindowController tempController = fxmlLoader.getController();
        setController(tempController);
        setCanvas(myController.getCanvas());
        //Setting the stage and adding my custom style to it
        primaryStage.setTitle("KitchenCatastropheGame");
        root.getStylesheets().add("group44/resources/application.css");
        root.setId("pane");
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> processKeyEvent(event));
        drawGame(testLevel);
        drawMovableObjects();
        primaryStage.setScene(scene);
        primaryStage.show();
   }
    
    /**
     * This method resets the game to the original state.
     * It will have to be changed once collectible items and enemies will be implemented.
     */
    public void restartGame() {
        // We just move the player to the starting position.
        playerView.setX(playerX);
        playerView.setY(playerY);
        drawGame(testLevel);
    }

    /**
     * This method sets the globally available controller to the current controller.
     * @param tempController The current controller.
     */
    public void setController(MainGameWindowController tempController) {
        myController = tempController;
    }

    /**
     * This method sets the globally available canvas to the current canvas.
     * @param canvas The current canvas.
     */
    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
    
    /**
     * This method draws every non movable object onto the screen.
     */
    public void drawGame(Level aLevel) {
        // Get the Graphic Context of the canvas. This is what we draw on.
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Clear canvas
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        //TODO: Read each object from each row array in the grid map and draw themselves.
        for(LevelObject[] row: aLevel.getGrid()){
            for(LevelObject obj: row){
                obj.draw(gc, obj.getPositionX(), obj.getPositionY(), GRID_CELL_WIDTH, GRID_CELL_HEIGHT);
            }
        }

        //KEPT FOR REFERENCE
        /*
        //Adding objects to map.
        for (int x = 0; x <= 10; x++) {
            for (int j = 0; j <= 10; j++) {
                if (x == 0 || x == 10 || j == 0 || j == 10)
                    map[x][j] = wall;
                else
                    map[x][j] = floor;
            }
        }


        //Drawing the map
        for (int i = 0; i <= 10; ++i) {
            for (int j = 0; j <= 10; ++j) {
                gc.drawImage(map[i][j],j*GRID_CELL_WIDTH,i*GRID_CELL_HEIGHT);
            }
        }*/
    }
    
    /**
     * This method draws the movable objects onto a pane, above the canvas
     * so that the movement can be rendered smoothly.
     */
    public void drawMovableObjects(){
        playerView.setFitWidth(GRID_CELL_WIDTH);
        playerView.setFitHeight(GRID_CELL_HEIGHT);
        playerView.setImage(player);
        playerView.setY(playerY);
        playerView.setX(playerX);
        //Add the movable objects onto the pane destined for them.
        myController.getMovableObjects().getChildren().add(playerView);
    }
    
    /**
     * This method smoothly translates the player position from playerY and playerX to playerY+yPos and playerX+xPos.
     * It also animates the player as he moves.
     * @param yPos is the increment or decrement added to playerY.
     * @param xPos is the increment or decrement added to playerX.
     */
    public void smoothTransition(double yPos, double xPos) {
      final Animation animation = new SpriteAnimation(playerView,Duration.millis(200),xPos,yPos);
      animation.setCycleCount(1);
      animation.setOnFinished(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
              canMove = true;
          }
      });
      animation.play();
  }

    /**
     * This method handles the keyboard input.
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
                if ((playerView.getX() - GRID_CELL_WIDTH) < (10*GRID_CELL_WIDTH) && (playerView.getX() - GRID_CELL_WIDTH) > 0 && canMove) {
                	canMove=false;
                    smoothTransition(0,  -GRID_CELL_WIDTH);
                }
                break;
            }
            case RIGHT: {
                // Right key was pressed. So move the player right by one cell.
                if ((playerView.getX() + GRID_CELL_WIDTH) < (10*GRID_CELL_WIDTH) && (playerView.getX() + GRID_CELL_WIDTH) > 0 && canMove){
                	canMove=false;
                    smoothTransition(0,  GRID_CELL_WIDTH);
                }
                break;
            }
            case UP: {
                //Up key was pressed. So move the player down by one cell.
                if ((playerView.getY() - GRID_CELL_HEIGHT) < (10*GRID_CELL_HEIGHT) && (playerView.getY() - GRID_CELL_HEIGHT) > 0 && canMove){
                	canMove=false;
                    smoothTransition(-GRID_CELL_HEIGHT, 0 );
                }
                break;
            }
            case DOWN: {
                //Down key was pressed. So move the player down by one cell.
                if ((playerView.getY() + GRID_CELL_HEIGHT) < (10*GRID_CELL_HEIGHT) && (playerView.getY() + GRID_CELL_HEIGHT) > 0 && canMove){
                	canMove=false;
                    smoothTransition(GRID_CELL_HEIGHT,0);
                }
                break;
            }
            default:
                // Do nothing
                break;
        }

        // Redraw game as the player may have moved.
        drawGame(testLevel);

        // Consume the event. This means we mark it as dealt with. This stops other GUI nodes (buttons etc) responding to it.
        event.consume();
    }

=======
	/**
	 * This method instantiates the MainMenuScene class.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception{
		MainMenuScene mainMenuStage = new MainMenuScene(primaryStage);
	}
	/**
	 * This method starts the program.
	 * @param args These are the launch arguments of the program.
	 */
>>>>>>> upstream/dev
    public static void main(String[] args) {
        launch(args);
    }
}
