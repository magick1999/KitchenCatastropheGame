package group44.game.layoutControllers;


import group44.controllers.parsers.MOTDParser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class MOTDController {
    @FXML
    private Button refresh;
    @FXML
    private Label motd;
    @FXML
    private Button back;

    public void setMOTDRefresh(Button refresh) {
        this.refresh = refresh;
    }

    public Button getMOTDRefresh() {
        this.motd.setText(MOTDParser.getMOTD());//Sets label to the MOTD
        return refresh;
    }

    public void setMOTDText(Label motd) {
        this.motd = motd;
    }

    public Label getMOTDText() {
        return motd;
    }

    public void setBack(Button back) {
        this.back = back;
    }

    public Button getBack() {
        return back;
    }

}