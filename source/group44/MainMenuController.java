package group44;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;


public class MainMenuController {
    @FXML
    BorderPane root;
    @FXML
    Button profileSelection;
    @FXML
    Button leaderboard;
    @FXML
    Button profilesText;
    @FXML
    Button newProfile;
    @FXML
    ListView<?> profiles;
    @FXML
    Button play;
    @FXML
    Button quit;
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
