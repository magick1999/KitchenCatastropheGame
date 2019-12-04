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
    private ImageView logo;

    public MainMenuController(){

    }
    @FXML
    public void initialize(){
        logo.setImage(new Image("/group44/resources/kitchenLogo.png"));

        profiles.setCellFactory(param -> new ListCell<Profile>() {
            @Override
            protected void updateItem(Profile item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getUsername() == null) {
                    setText(null);
                } else {
                    setText(item.getUsername());
                }
            }
        });

        // Load and Bind profiles to the selection
        ProfileManager.load(PROFILES);
        profiles.setItems(ProfileManager.getProfiles());
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
    public void setQuit(Button quit) {
    	this.quit=quit;
    }
    public Button getQuit() {
    	return this.quit;
    }
}
