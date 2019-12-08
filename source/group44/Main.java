package group44;

import group44.game.scenes.MainMenuScene;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class just redirects the player to the main menu stage.
 * 
 * @author Mihai
 *
 */
public class Main extends Application {
    /**
     * This method instantiates the MainMenuScene class.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
	MainMenuScene mainMenuStage = new MainMenuScene(primaryStage);
    }

    /**
     * This method starts the program.
     * 
     * @param args
     *            These are the launch arguments of the program.
     */
    public static void main(String[] args) {
	launch(args);
    }
}
