package group44;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


import static group44.Constants.WINDOW_HEIGHT;
import static group44.Constants.WINDOW_WIDTH;
/**
 * This class displays the level selection screen and allows the player to select a certain level.
 * @author Mihai
 *
 */
public class LevelSelectorScene {
    //The controller associated with the specific FXML file.
	private LevelSelectorController levelSelectorController;
    //This is the stage where the scene is displayed.
	private Stage primaryStage;
    /*
     * This is the constructor that creates the scene, instantiates the controller 
     * and sets up the listeners for the buttons on the screen.
     * @param primaryStage This is the stage where the scene is displayed.
     */
    public LevelSelectorScene(Stage primaryStage){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/group44/levelSelector.fxml"));
        this.primaryStage=primaryStage;
        try {
            Parent root = fxmlLoader.load();
            //Setting the stage and adding my custom style to it.
            root.getStylesheets().add("group44/resources/application.css");
            root.setId("pane");
            Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
            //Instantiating the controller.
            LevelSelectorController tempController = fxmlLoader.getController();
            setController(tempController);
            //Adding the listeners for the buttons.
            setUpButtons();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("Hello World");
    }
    /**
     * Sets the globally available controller.
     * @param levelSelectorController the controller for this class.
     */
    private void setController(LevelSelectorController levelSelectorController){
        this.levelSelectorController = levelSelectorController;
    }
    /**
     * This creates the game scene for the required level.
     * When the level will be implemented here will be needed to pass the level
     * as an argument to the game scene.
     * @param e This is the event from the click on the level button/
     */
    private void setLevelSelect(MouseEvent e){
        new GameScene(primaryStage);
    }
    /**
     * This method creates listeners for each of the buttons
     */
    private void loadLevels(){
        ObservableList<Node> children = levelSelectorController.getLevels().getChildren();
        for (Node node : children) {
            node.setOnMouseClicked(this::setLevelSelect);
        }
    }
    /**
     * This method creates listeners for all of the buttons on the scene.
     * Listeners for back and forward need to be added.
     */
    private void setUpButtons(){
        loadLevels();
    }
}
