package group44;

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

import static group44.Constants.*;

/**
 * This class just redirects the player to the main menu stage.
 * @author Mihai
 *
 */
public class Main extends Application {
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
    public static void main(String[] args) {
        launch(args);
    }
}
