package group44;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import javax.xml.soap.Text;

import static group44.Constants.WINDOW_HEIGHT;
import static group44.Constants.WINDOW_WIDTH;
/**
 * This class is responsible for displaying the profile creation scene.
 * @author Mihai
 */
public class ProfileCreatorScene {
	//This is the stage where this scene will be displayed.
	private Stage primaryStage;
    //The controller associated with the specific FXML file.
	private ProfileCreatorController profileCreatorController;
    //The next two variables could be removed as they are not good practice if 
    //we decide to add more fields to the profile class.
    //But for now I included them for to make my life easier and debugging aswell.
    //This is the text field for the name input.
	private TextField nameText;
    //This is the text field for the email input.
	private TextField emailText;
    //This variable is used to check if the user has clicked on the name 
    //text input field for the first time to remove the hints.
    private boolean firstTimeName = true;
  //This variable is used to check if the user has clicked on the email 
    //text input field for the first time to remove the hints.
    private boolean firstTimeEmail = true;
/**
 * This is the constructor for the ProfileCreatorScene class.
 * It instantiates the controller, scene, and sets the key listeners.
 * @param primaryStage This is the stage where the scene will be displayed.
 */
    public ProfileCreatorScene(Stage primaryStage) {
    	//The FXML file is loaded.
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profileCreator.fxml"));
        this.primaryStage = primaryStage;
        try {
            Parent root = fxmlLoader.load();
            //Setting the stage and adding my custom style to it.
            root.getStylesheets().add("group44/application.css");
            root.setId("root");
            Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
            //Setting the globally available controller.
            ProfileCreatorController tempController = fxmlLoader.getController();
            setController(tempController);
            //Adding the listeners for the buttons on the scene.
            setUpControls();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Sets the globally available controller.
     * @param levelSelectorController the controller for this class.
     */
    private void setController(ProfileCreatorController profileCreatorController) {
        this.profileCreatorController = profileCreatorController;
    }
/**
 * This sets the listeners for all the buttons and text input fields.
 */
    private void setUpControls() {
        nameText = (TextField) profileCreatorController.getCenterVBox().getChildren().get(1);
        emailText = (TextField) profileCreatorController.getCenterVBox().getChildren().get(2);
        profileCreatorController.getConfirm().setOnMouseClicked(this::createProfile);
        //This creates a listener to check if this is the first time
        //the user has clicked on the name text input field to overwrite the hint.
        nameText.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(firstTimeName)
                    if (nameText instanceof TextField){
                        nameText.setText("");
                    }
            }
        });
      //This creates a listener to check if this is the first time
        //the user has clicked on the email text input field to overwrite the hint.
        emailText.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(firstTimeEmail)
                         emailText.setText("");
            }
        });
    }
    /**
     * This method will be used for profile creation.
     * It also returns the user to the main menu.
     * @param event
     */
    private void createProfile(MouseEvent event) {
        //Create new profile to display in the profile selector
        //Profile newProfile=new Profile(nameText.getText(),emailText.getText());
        //idk how have you thought to add it to a static class or whatever.
        new MainMenuScene(primaryStage);
    }
}
