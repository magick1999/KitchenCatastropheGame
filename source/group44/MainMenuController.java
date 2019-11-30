package group44;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;


public class MainMenuController {
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
    private ListView<?> profiles;
    @FXML
    private Button play;
    @FXML
    private Button quit;
    public MainMenuController(){

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

    public void setProfiles(ListView<?> profiles) {
        this.profiles = profiles;
    }

    public ListView<?> getProfiles() {
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
