package group44;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import static group44.Constants.WINDOW_HEIGHT;
import static group44.Constants.WINDOW_WIDTH;
/**
 * This class displays the leaderboard.
 * This should be rethought to display leaderboards for each level.
 * @author Mihai
 *
 */
public class LeaderboardScene {
	//The stage where the scenes are displayed.
    private Stage primaryStage;
    //The controller for this stage.
    private LeaderboardController leaderboardController;
    /**
     * The constructor for the LeaderboardScene class.
     * It defines the scene size, it instantiates the controller, it displays the scene and it adds the events listeners.
     * @param primaryStage
     */
    public LeaderboardScene(Stage primaryStage){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("leaderboard.fxml"));
        this.primaryStage = primaryStage;
        try {
            Parent root = fxmlLoader.load();
            //Setting the stage and adding my custom style to it.
            root.getStylesheets().add("sample/style.css");
            root.setId("pane");
            Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
            //Adding the key listener to the scene.
//            scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> processKeyEvent(event));
            LeaderboardController tempController = fxmlLoader.getController();
            setController(tempController);
            primaryStage.setScene(scene);
            primaryStage.show();
            setUpControls();
//            setUpInput();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Sets the globally available controller.
     * @param leaderboardController the controller for this class.
     */
    private void setController(LeaderboardController leaderboardController){
        this.leaderboardController = leaderboardController;
    }
    /**
     * This adds listeners for the buttons on the scene.
     */
    private void setUpControls(){
        leaderboardController.getBack().setOnMouseClicked(this::backToTheMenu);
    }
    /**
     * This returns the player to the MainMenuScene.
     * @param event
     */
    private void backToTheMenu(MouseEvent event){
        new MainMenuScene(primaryStage);
    }
}
