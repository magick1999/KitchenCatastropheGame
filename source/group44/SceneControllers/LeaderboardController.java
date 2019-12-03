package group44;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;


public class LeaderboardController {
    @FXML
    private Button logo;
    @FXML
    private ListView leaderboard;
    @FXML
    private Button back;
    public LeaderboardController(){

    }

    public void setLogo(Button logo) {
        this.logo = logo;
    }

    public Button getLogo() {
        return logo;
    }

    public void setLeaderboard(ListView leaderboard) {
        this.leaderboard = leaderboard;
    }

    public ListView getLeaderboard() {
        return leaderboard;
    }

    public void setBack(Button back) {
        this.back = back;
    }

    public Button getBack() {
        return back;
    }
}
