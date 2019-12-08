package group44.game.layoutControllers;

import group44.controllers.parsers.MOTDParser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Jordan
 * This class does the fxml injection of the widgets from the MOTD layout in the code.
 * It also contains the appropriate getters and setters for the widgets.
 */
public class MOTDController {
    @FXML
    private Label motd;
    @FXML
    private Button back;

    public void getMOTDRefresh() {
        this.motd.setText(MOTDParser.getMOTD());// Sets label to the
                                                // MOTD
    }

    public Button getBack() {
        return back;
    }

}
