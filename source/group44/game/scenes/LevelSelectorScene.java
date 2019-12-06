package group44.game.scenes;

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

import group44.game.layoutControllers.LevelSelectorController;
/**
 * This class displays the level selection screen and allows the player to select a certain level.
 * @author Mihai
 *
 */
public class LevelSelectorScene {
    //The controller associated with the specific FXML file.
	private LevelSelectorController levelSelectorController;
    //This is the stage where the scene is displayed.
	//The maximum number of levels.
	private static Integer maxLevel=3;
	private Stage primaryStage;
    Integer currentLevel = 1;

    /*
     * This is the constructor that creates the scene, instantiates the controller 
     * and sets up the listeners for the buttons on the screen.
     * @param primaryStage This is the stage where the scene is displayed.
     */
    public LevelSelectorScene(Stage primaryStage){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/group44/game/layouts/levelSelector.fxml"));
        this.primaryStage=primaryStage;
        try {
            Parent root = fxmlLoader.load();
            //Setting the stage and adding my custom style to it.
            root.getStylesheets().add("group44/resources/application.css");
            root.setId("root");
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
        primaryStage.setTitle("Kitchen Catastrophe");
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
        new GameScene(primaryStage,currentLevel);
    }
    /**
     * This method creates listeners for each of the buttons
     */
    private void loadLevels(){

        levelSelectorController.getPlay().setOnMouseClicked(this::setLevelSelect);
}
    
    private void setMenu(MouseEvent e){
        new MainMenuScene(primaryStage);
    }

    private void setNext(MouseEvent e){
        setLevelNum(true);
    }
    private void setPrevious(MouseEvent e){
        setLevelNum(false);
    }
    private void setLevelNum(boolean prevNext){
        currentLevel  =Integer.parseInt(levelSelectorController.getLevelNum().getText().replaceAll("[A-Z a-z]",""));
        if(prevNext == true && currentLevel<maxLevel){
            levelSelectorController.getLevelNum().setText("Level "+(++currentLevel).toString());
            levelSelectorController.getPrevious().setVisible(true);
        }else {
            if(prevNext == false && currentLevel>1){
                levelSelectorController.getLevelNum().setText("Level "+(--currentLevel).toString());//This could be used for level loading.
                levelSelectorController.getNext().setVisible(true);
            }
        }
        if(currentLevel==1){
            levelSelectorController.getPrevious().setVisible(false);
        }else {
            if(currentLevel==maxLevel){
                levelSelectorController.getNext().setVisible(false);
            }
        }
    }
    private void setTopTimes(){
        //This is where you call the leaderboard
        // Create an ObservableList<Record>
        //And load it with top 3
        //And then
//        levelSelectorController.getLevelTimes().setItems("TopTimes");
       }
    /**
     * This method creates listeners for all of the buttons on the scene.
     * Listeners for back and forward need to be added.
     */
    private void setUpButtons(){
        loadLevels();
        levelSelectorController.getPrevious().setVisible(false);
        levelSelectorController.getNext().setOnMouseClicked(this::setNext);
        levelSelectorController.getPrevious().setOnMouseClicked(this::setPrevious);
        levelSelectorController.getMenu().setOnMouseClicked(this::setMenu);
    }
}
