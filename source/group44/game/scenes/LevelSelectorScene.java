package group44.game.scenes;

import group44.controllers.Leaderboard;
import group44.controllers.LevelManager;
import group44.game.Level;
import group44.game.layoutControllers.LevelSelectorController;
import group44.models.Profile;
import group44.models.Record;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import static group44.Constants.WINDOW_HEIGHT;
import static group44.Constants.WINDOW_WIDTH;

/**
 * This class displays the level selection screen and allows the player to select a certain level.
 *
 * @author Mihai
 */
public class LevelSelectorScene {
    //The controller associated with the specific FXML file.
    private LevelSelectorController levelSelectorController;
    //This is the stage where the scene is displayed.
    //The maximum number of levels.
    private static Integer maxLevel = 3;
    private Stage primaryStage;
    private Integer currentLevelIndex;
    private Profile currentProfile;
    /*
     * This is the constructor that creates the scene, instantiates the controller
     * and sets up the listeners for the buttons on the screen.
     * @param primaryStage This is the stage where the scene is displayed.
     */
    public LevelSelectorScene(Stage primaryStage, Profile currentProfile) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/group44/game/layouts/levelSelector.fxml"));
        this.primaryStage = primaryStage;
        try {
            Parent root = fxmlLoader.load();
            //Setting the stage and adding my custom style to it.
            root.getStylesheets().add("group44/resources/application.css");
            root.setId("root");
            Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
            //Instantiating the controller.
            LevelSelectorController tempController = fxmlLoader.getController();
            setController(tempController);
            currentLevelIndex = Integer.parseInt(levelSelectorController.getLevelNum().getText().replaceAll("[A-Z a-z]", ""));
            //Adding the listeners for the buttons.
            setUpButtons();
            primaryStage.setScene(scene);
            primaryStage.show();
            this.currentProfile = currentProfile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("Kitchen Catastrophe");
    }

    /**
     * Sets the globally available controller.
     *
     * @param levelSelectorController the controller for this class.
     */
    private void setController(LevelSelectorController levelSelectorController) {
        this.levelSelectorController = levelSelectorController;
    }

    /**
     * This creates the game scene for the required level.
     * When the level will be implemented here will be needed to pass the level
     * as an argument to the game scene.
     *
     * @param e This is the event from the click on the level button/
     */
    private void setLevelSelect(MouseEvent e) {
        try {
            LevelManager.load("source/group44/data/levels/");
            Level currentLevel = LevelManager.load(LevelManager.getLevelInfos().get(currentLevelIndex - 1));
            new GameScene(primaryStage, currentLevel, currentProfile);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * This method creates listeners for each of the buttons
     */
    private void loadLevel() {

        levelSelectorController.getPlay().setOnMouseClicked(this::setLevelSelect);
    }

    private void setMenu(MouseEvent e) {
        new MainMenuScene(primaryStage);
    }

    private void setNext(MouseEvent e) {
        setLevelNum(true);
    }

    private void setPrevious(MouseEvent e) {
        setLevelNum(false);
    }

    private void setLevelNum(boolean prevNext) {
        if (prevNext && currentLevelIndex < maxLevel) {
            levelSelectorController.getLevelNum().setText("Level " + (++currentLevelIndex).toString());
            levelSelectorController.getPrevious().setVisible(true);
        } else {
            if (prevNext && currentLevelIndex > 1) {
                levelSelectorController.getLevelNum().setText("Level " + (--currentLevelIndex).toString());//This could be used for level loading.
                levelSelectorController.getNext().setVisible(true);
            }
        }
        if (currentLevelIndex == 1) {
            levelSelectorController.getPrevious().setVisible(false);
        } else {
            if (currentLevelIndex.equals(maxLevel)) {
                levelSelectorController.getNext().setVisible(false);
            }
        }
    }

    private void setTopTimes() {
        Leaderboard.load("source/group44/data/records.txt");
        Record[] top3 = Leaderboard.getTopThreeRecords(currentLevelIndex);
        ObservableList<Record> observableList = FXCollections.observableArrayList(top3);
        levelSelectorController.getLevelTimes().setItems(observableList);
    }

    /**
     * This method creates listeners for all of the buttons on the scene.
     * Listeners for back and forward need to be added.
     */
    private void setUpButtons() {
        loadLevel();
        setTopTimes();
        levelSelectorController.getPrevious().setVisible(false);
        levelSelectorController.getNext().setOnMouseClicked(this::setNext);
        levelSelectorController.getPrevious().setOnMouseClicked(this::setPrevious);
        levelSelectorController.getMenu().setOnMouseClicked(this::setMenu);
    }
}
