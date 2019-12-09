package group44.game.scenes;

import static group44.Constants.WINDOW_HEIGHT;
import static group44.Constants.WINDOW_WIDTH;

import java.util.Optional;

import group44.controllers.ProfileManager;
import group44.exceptions.UsernameTakenException;
import group44.game.layoutControllers.ProfileCreatorController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * This class is responsible for displaying the profile creation scene.
 *
 * @author Mihai
 */
public class ProfileCreatorScene {
    // This is the stage where this scene will be displayed.
    private Stage primaryStage;
    // The controller associated with the specific FXML file.
    private ProfileCreatorController profileCreatorController;
    // The next two variables could be removed as they are not good practice
    // if
    // we decide to add more fields to the profile class.
    // But for now I included them for to make my life easier and debugging
    // aswell.
    // This is the text field for the name input.
    private TextField nameText;
    // This variable is used to check if the user has clicked on the name
    // text input field for the first time to remove the hints.
    private boolean firstTimeName = true;

    /**
     * This is the constructor for the ProfileCreatorScene class. It
     * instantiates the controller, scene, and sets the key listeners.
     *
     * @param primaryStage
     *            This is the stage where the scene will be displayed.
     */
    public ProfileCreatorScene(Stage primaryStage) {
        // The FXML file is loaded.
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/group44/game/layouts/profileCreator.fxml"));
        this.primaryStage = primaryStage;
        try {
            Parent root = fxmlLoader.load();
            // Setting the stage and adding my custom style to it.
            root.getStylesheets().add("group44/resources/application.css");
            root.setId("root");
            Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
            // Setting the globally available controller.
            ProfileCreatorController tempController = fxmlLoader
                    .getController();
            setController(tempController);
            // Adding the listeners for the buttons on the scene.
            setUpControls();
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
     * @param profileCreatorController
     *            the controller for this class.
     */
    private void setController(
            ProfileCreatorController profileCreatorController) {
        this.profileCreatorController = profileCreatorController;
    }

    /**
     * This sets the listeners for all the buttons and text input fields.
     */
    private void setUpControls() {
        nameText = (TextField) profileCreatorController.getCenterVBox()
                .getChildren().get(1);
        profileCreatorController.getConfirm()
                .setOnMouseClicked(this::createProfile);
        // This creates a listener to check if this is the first time
        // the user has clicked on the name text input field to
        // overwrite the hint.
        nameText.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (firstTimeName)
                    if (nameText instanceof TextField) {
                        nameText.setText("");
                    }
            }
        });
        profileCreatorController.getBack().setOnMouseClicked(this::backToMenu);

    }

    /**
     * This handles all the wrong username inputs.
     *
     * @param type
     *            is a variable that showcases the type of the error, true for
     *            username taken, false for blank name.
     */
    private void usernameError(Boolean type) {
        ButtonType closeAlert = new ButtonType(
                "Ok master chef, I have chosen my faith!",
                ButtonBar.ButtonData.OK_DONE);
        Alert a1 = new Alert(Alert.AlertType.NONE, "default Dialog",
                closeAlert);
        a1.setHeight(400);
        a1.setWidth(500);
        if (type == true) {
            a1.setTitle("Try again!");
            a1.setContentText("Your username is already taken. \n"
                    + "You could choose a different username or fight the owner to death!");

            Optional<ButtonType> result = a1.showAndWait();
            if (!result.isPresent()) {

            } else {
                if (result.get().equals(closeAlert)) {
                    nameText.setText("Try something else!");
                }
            }
        } else {
            a1.setTitle("Are you stoopid?!?!");
            a1.setContentText("You need to enter a username \n"
                    + "unless your mom was so uninspired that she had to call you 'blank name'!");

            Optional<ButtonType> result = a1.showAndWait();
            if (!result.isPresent()) {

            } else {
                if (result.get().equals(closeAlert)) {
                    nameText.setText(
                            "If you're so uninspired better go play that mario game!!!!");
                }
            }
        }
    }

    /**
     * This method takes the player to the main menu.
     *
     * @param e
     *            This is the mouse event that triggers this action.
     */
    private void backToMenu(MouseEvent e) {
        new MainMenuScene(primaryStage);
    }

    /**
     * This method will be used for profile creation. It also returns the user
     * to the main menu.
     *
     * @param event
     *            This is the mouse event that triggers this action.
     */
    private void createProfile(MouseEvent event) {
        // Create new profile to display in the profile selector
        // Profile newProfile=new
        // Profile(nameText.getText(),emailText.getText());
        // idk how have you thought to add it to a static class or
        // whatever.
        if (nameText.getText().equals("")
                || nameText.getText()
                        .equals("Enter your desired nickname here.")
                || nameText.getText().equals("Try something else!")
                || nameText.getText().equals(
                        "If you're so uninspired better go play that mario game!!!!")) {
            usernameError(false);
        } else {
            try {
                ProfileManager.register(nameText.getText());
                new MainMenuScene(primaryStage);
            } catch (UsernameTakenException e) {
                usernameError(true);
            }
        }

    }
}
