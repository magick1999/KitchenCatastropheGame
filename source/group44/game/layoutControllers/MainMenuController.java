package group44.game.layoutControllers;

import group44.controllers.ProfileManager;
import group44.models.Profile;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * This class does the fxml injection of the widgets from the main menu layout in the code.
 * It also contains the appropriate getters and setters for the widgets.
 */
public class MainMenuController {
	private static final String PROFILES = "source/group44/data/profiles.txt";

    @FXML
    private BorderPane root;
    @FXML
    private Button profileSelection;
    @FXML
    private Button leaderboard;
    @FXML
    private Button profilesText;
    @FXML
    private Button newProfile;
    @FXML
    private ListView<Profile> profiles;
    @FXML
    private Button play;
    @FXML
    private Button quit;
    @FXML
    private Button motd;
    @FXML
    private ImageView logo;

    public MainMenuController(){

    }
    @FXML
    public void initialize(){
        logo.setImage(new Image("/group44/resources/KitchenCatastrophe.png"));
    }
    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void play(Button play){
            this.play=play;
    }

    public Button getPlay() {
        return play;
    }

    public void setProfiles(ListView<Profile> profiles) {
        this.profiles = profiles;
    }

    public ListView<Profile> getProfiles() {
        return profiles;
    }

    public void setNewProfile(Button newProfile) {
        this.newProfile = newProfile;
    }

    public Button getNewProfile() {
        return newProfile;
    }

    public void setLeaderboard(Button leaderboard) {
        this.leaderboard = leaderboard;
    }

    public Button getLeaderboard() {
        return leaderboard;
    }

    public void setProfileSelection(Button profileSelection) {
        this.profileSelection = profileSelection;
    }

    public Button getProfileSelection() {
        return profileSelection;
    }

    public void setMOTD(Button motd) {
        this.motd = motd;
    }

    public Button getMOTD() {
        return motd;
    }
    public void setQuit(Button quit) {
    	this.quit=quit;
    }

    public Button getQuit() {
    	return this.quit;
    }
}
