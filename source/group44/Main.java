package group44;

import javafx.animation.*;
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

import static group44.Constants.*;


public class Main extends Application {


    // The canvas in the GUI. This needs to be a global variable
    // (in this setup) as we need to access it in different methods.
    // We could use FXML to place code in the controller instead.
    private Canvas canvas;

    // Loaded images
    Image player = new Image("group44/player.png");
    Image floor = new Image("group44/floor.png");
    Image wall = new Image("group44/default_silver_sand.png");

    // X and Y coordinate of player
    int playerX = 25;
    int playerY = 25;

    ImageView[][] mapTextures = new ImageView[40][40];
    MainGameWindowController myController;

    Image[][] map = new Image[40][40];
    ImageView playerView = new ImageView();

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainGameWindow.fxml"));
        Parent root = fxmlLoader.load();
        MainGameWindowController tempController = fxmlLoader.getController();
        setController(tempController);
        setCanvas(myController.getCanvas());
        //Setting the stage and adding my custom style to it
        primaryStage.setTitle("Hello World");
        root.getStylesheets().add("group44/style.css");
        root.setId("pane");
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> processKeyEvent(event));
        drawGame();
        drawMovableObjects();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void restartGame(int startX, int startY) {
        // We just move the player to cell (2, 2)
        playerX = startX;
        playerY = startY;
        drawGame();
    }



    public void setController(MainGameWindowController tempController) {
        myController = tempController;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public void drawGame() {
        // Get the Graphic Context of the canvas. This is what we draw on.
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Clear canvas
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Draw row of dirt images
        // We multiply by the cell width and height to turn a coordinate in our grid into a pixel coordinate.
        // We draw the row at y value 2.
        for (int x = 0; x <= 22; x++) {
            for (int j = 0; j <= 29; j++) {
                if (x == 0 || x == 21 || j == 0 || j == 28)
                    map[x][j] = wall;
                else
                    map[x][j] = floor;
            }
        }
        for (int i = 0; i <= 22; ++i) {
            for (int j = 0; j <= 29; ++j) {
                gc.drawImage(map[i][j],j*GRID_CELL_WIDTH,i*GRID_CELL_HEIGHT);
            }
        }
        // Draw player at current location
//        gc.drawImage(player, playerX ,playerY, GRID_CELL_WIDTH, GRID_CELL_HEIGHT);

    }

    public void drawMovableObjects(){
        playerView.setFitWidth(GRID_CELL_WIDTH);
        playerView.setFitHeight(GRID_CELL_HEIGHT);
        playerView.setImage(player);
        playerView.setY(playerY);
        playerView.setX(playerX);
        myController.getMovableObjects().getChildren().add(playerView);


    }

    public void smoothTransition(float yPos,float xPos) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(300), playerView);
        tt.setByX(xPos);
        tt.setByY(yPos);
        tt.setCycleCount(0);
        tt.setAutoReverse(false);
        tt.play();
    }

    public void processKeyEvent(KeyEvent event) {
        switch (event.getCode()) {

            case ESCAPE: {
                myController.getMenuBox().setVisible(!myController.getMenuBox().isVisible());
                break;
            }
            case LEFT: {
                if ((playerX - 25) < (28*25) && (playerX - 25) > 0) {
                    smoothTransition(0,  -25);
                    playerX = playerX - 25;
                }
                break;
            }
            case RIGHT: {
                // Right key was pressed. So move the player right by one cell.
                if ((playerX + 25) < (28*25) && (playerX + 25) > 0){
                    smoothTransition(0,  25);
                    playerX = playerX + 25;
                }
                break;
            }
            case UP: {
                if ((playerY - 25) < (21*25) && (playerY - 25) > 0){
                    smoothTransition(-25, 0 );
                    playerY = playerY - 25;
                }
                break;
            }
            case DOWN: {
                if ((playerY + 25) < (21*25) && (playerY + 25) > 0){
                    smoothTransition(25,0);
                    playerY = playerY + 25;
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

    public static void main(String[] args) {
        launch(args);
    }
}
