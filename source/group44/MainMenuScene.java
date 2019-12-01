package group44;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import static group44.Constants.WINDOW_HEIGHT;
import static group44.Constants.WINDOW_WIDTH;
/**
 * This class displays the main menu and handles all the redirecting of the player to the desired scenes.
 * It also allows the player to select the desired profile.
 * @author Mihai
 *
 */
public class MainMenuScene {
    //The controller associated with the specific FXML file.
	private MainMenuController mainMenuController;
    //This is the stage where all the scenes are displayed.
    private Stage primaryStage;
    /**
     * This is the constructor for this class.It instantiates the scene, the respective controller and adds listeners to the buttons.
     * It also displays the scene.
     * @param primaryStage is the stage where the scene will be displayed.
     */
    public MainMenuScene(Stage primaryStage) {
    	//Load the FXML file.
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/group44/mainMenu.fxml"));
        this.primaryStage = primaryStage;
        try {
        	//Setting the root.
            Parent root = fxmlLoader.load();
            //Setting the stage and adding my custom style to it.
            root.getStylesheets().add("group44/resources/application.css");
            root.setId("pane");
            Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
            MainMenuController tempController = fxmlLoader.getController();
            setController(tempController);
            //Adding the listeners for the buttons on the scene.
            setUpButtons();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("Hello World");

    }
    /**
     * This method sets the globally available controller to the current controller.
     * @param tempController The current controller.
     */
    private void setController(MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
    }
    /**
     * This method instantiates the LevelSelectorScene class.
     * @param e The mouse event created by the press on the play button.
     */
    private void pressPlay(MouseEvent e){
        new LevelSelectorScene(primaryStage);
    }
    /**
     * This method instantiates the ProfileCreatorScene class.
     * @param e The mouse event created by the press on the new profile button.
     */
    private void newProfile(MouseEvent e){
        new ProfileCreatorScene(primaryStage);
    }
    /**
     * This method instantiates the LeaderboardScene class.
     * @param e The mouse event created by the press on the leaderboard button.
     */
    private void showLeaderboard(MouseEvent e){
        new LeaderboardScene(primaryStage);
    }
    /**
     * This method adds the listeners to the buttons on screen.
     * The quit button needs a listener, will add that in a future commit.
     */
    private void closeGame(MouseEvent e) {
    	primaryStage.close();
    }
    private void setUpButtons(){
        mainMenuController.getPlay().setOnMouseClicked(this::pressPlay);
        mainMenuController.getNewProfile().setOnMouseClicked(this::newProfile);
        mainMenuController.getLeaderboard().setOnMouseClicked(this::showLeaderboard);
        mainMenuController.getQuit().setOnMouseClicked(this::closeGame);
    }
}
