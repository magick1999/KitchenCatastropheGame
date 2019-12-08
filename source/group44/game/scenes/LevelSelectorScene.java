package group44.game.scenes;

import static group44.Constants.WINDOW_HEIGHT;
import static group44.Constants.WINDOW_WIDTH;

import group44.controllers.Leaderboard;
import group44.controllers.LevelManager;
import group44.game.Level;
import group44.game.layoutControllers.LevelSelectorController;
import group44.models.Profile;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * This class displays the level selection screen and allows the player to
 * select a certain level.
 *
 * @author Mihai, Tomas Svejnoha
 * @version 1.0
 */
public class LevelSelectorScene {
    // The controller associated with the specific FXML file.
    private LevelSelectorController levelSelectorController;
    // This is the stage where the scene is displayed.
    // The maximum number of levels.
    private Integer maxLevel;
    private Stage primaryStage;
    private Integer currentLevelIndex;
    private Profile currentProfile;

    /*
     * This is the constructor that creates the scene, instantiates the
     * controller and sets up the listeners for the buttons on the screen.
     *
     * @param primaryStage This is the stage where the scene is displayed.
     */
    public LevelSelectorScene(Stage primaryStage, Profile currentProfile) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/group44/game/layouts/levelSelector.fxml"));
        this.primaryStage = primaryStage;
        try {
            Parent root = fxmlLoader.load();
            // Setting the stage and adding my custom style to it.
            root.getStylesheets().add("group44/resources/application.css");
            root.setId("root");
            Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
            // Instantiating the controller.
            LevelSelectorController tempController = fxmlLoader.getController();
            setController(tempController);
            Leaderboard.load();
            this.maxLevel = Math.min(LevelManager.load().size(),
                    Leaderboard.getAchievedLevel(currentProfile.getId()) + 1);
            currentLevelIndex = maxLevel;
            this.currentProfile = currentProfile;
            // Adding the listeners for the buttons.
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
     *
     * @param levelSelectorController
     *            the controller for this class.
     */
    private void setController(
            LevelSelectorController levelSelectorController) {
        this.levelSelectorController = levelSelectorController;
    }

    /**
     * This creates the game scene for the required level.
     *
     * @param e
     *            This is the event from the click on the level button.
     */
    private void setLevelSelect(MouseEvent e) {
        try {
            Level currentLevel;
            if (LevelManager.hasUnfinishedLevel(currentLevelIndex,
                    this.currentProfile.getId())) {
                currentLevel = LevelManager.resume(currentLevelIndex,
                        this.currentProfile.getId());
            } else {
                currentLevel = LevelManager.load(currentLevelIndex);
                // currentLevel =
                // LevelManager.load(LevelManager.load().get(currentLevelIndex
                // - 1));
            }

            new GameScene(primaryStage, currentLevel, currentProfile);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * This method sets the level to be played.
     */
    private void loadLevel() {

        levelSelectorController.getPlay()
                .setOnMouseClicked(this::setLevelSelect);
    }

    /**
     * This method takes the player to the main menu.
     *
     * @param e
     *            is the mouse event that triggers this action.
     */
    private void setMenu(MouseEvent e) {
        new MainMenuScene(primaryStage);
    }

    private void setNext(MouseEvent e) {
        setLevelNum(true);
    }

    private void setPrevious(MouseEvent e) {
        setLevelNum(false);
    }

    /**
     * This sets the current level available to play.
     */
    private void setLevelNum(boolean prevNext) {
        if (prevNext && currentLevelIndex < maxLevel) {
            levelSelectorController.getLevelNum()
                    .setText("Level " + (++currentLevelIndex).toString());
            levelSelectorController.getPrevious().setVisible(true);
        } else {
            if (!prevNext && currentLevelIndex > 1) {
                levelSelectorController.getLevelNum()
                        .setText("Level " + (--currentLevelIndex).toString());// This
                                                                              // could
                                                                              // be
                                                                              // used
                                                                              // for
                                                                              // level
                                                                              // loading.
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
        Leaderboard.getTopThreeRecords(this.currentLevelIndex);
    }

    private void setTopTimes() {
        Leaderboard.load();
        levelSelectorController.getLevelTimes()
                .setItems(Leaderboard.getTopThreeRecords(currentLevelIndex));
    }

    /**
     * This method creates listeners for all of the buttons on the scene. and
     * sets the visibility of the previous and next buttons depending on the
     * current level and max level.
     */
    private void setUpButtons() {
        levelSelectorController.getNext().setVisible(false);
        if (maxLevel == 1) {
            levelSelectorController.getNext().setVisible(false);
        } else {
            if (maxLevel == LevelManager.load().size()) {
                levelSelectorController.getLevelNum()
                        .setText("Level " + LevelManager.load().size());
                levelSelectorController.getNext().setVisible(false);
            } else {
                levelSelectorController.getLevelNum()
                        .setText("Level " + currentLevelIndex);

            }
        }
        loadLevel();
        setTopTimes();
        if (currentLevelIndex == 1) {
            levelSelectorController.getPrevious().setVisible(false);
        }
        levelSelectorController.getNext().setOnMouseClicked(this::setNext);
        levelSelectorController.getPrevious()
                .setOnMouseClicked(this::setPrevious);
        levelSelectorController.getMenu().setOnMouseClicked(this::setMenu);
    }
}
