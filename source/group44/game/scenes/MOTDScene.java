package group44.game.scenes;

import static group44.Constants.WINDOW_HEIGHT;
import static group44.Constants.WINDOW_WIDTH;

import group44.game.layoutControllers.MOTDController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This class displays the MOTD..
 * 
 * @author Mihai, Jordan
 *
 */
public class MOTDScene {
    // The stage where the scenes are displayed.
    private Stage primaryStage;
    // The controller for this stage.
    private MOTDController motdController;

    /**
     * The constructor for the MOTDScene class. It defines the scene size, it
     * instantiates the controller, it displays the scene and it adds the events
     * listeners.
     * 
     * @param primaryStage
     */
    public MOTDScene(Stage primaryStage) {
	FXMLLoader fxmlLoader = new FXMLLoader(
		getClass().getResource("/group44/game/layouts/motd.fxml"));
	this.primaryStage = primaryStage;
	try {
	    Parent root = fxmlLoader.load();
	    // Setting the stage and adding custom style to it.
	    root.getStylesheets().add("/group44/resources/application.css");
	    root.setId("root");
	    Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
	    MOTDController tempController = fxmlLoader.getController();
	    setController(tempController);
	    primaryStage.setScene(scene);
	    primaryStage.show();
	    setUpControls();
	    refreshMOTD();

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     * Sets the globally available controller.
     * 
     * @param motdController
     *            the controller for this class.
     */
    private void setController(MOTDController motdController) {
	this.motdController = motdController;
    }

    /**
     * This adds listeners for the buttons on the scene.
     */
    private void setUpControls() {
	motdController.getBack().setOnMouseClicked(this::backToTheMenu);
    }

    /**
     * This returns the player to the MainMenuScene.
     * 
     * @param event
     */
    private void backToTheMenu(MouseEvent event) {
	new MainMenuScene(primaryStage);
    }

    private void refreshMOTD() {
	Timeline timer = new Timeline(
		new KeyFrame(Duration.ZERO, new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent actionEvent) {
			motdController.getMOTDRefresh();
		    }
		}), new KeyFrame(Duration.seconds(1)));
	timer.setCycleCount(Timeline.INDEFINITE);
	timer.play();
    }
}