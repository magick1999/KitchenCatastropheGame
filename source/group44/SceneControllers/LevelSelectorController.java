package group44;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


public class LevelSelectorController {
    @FXML
    private Button logo;
    @FXML
    private GridPane levels;
    @FXML
    private Button previous;
    @FXML
    private Button next;
    public LevelSelectorController(){
//        ObservableList<Node> children = getLevels().getChildren();
//        for (Node node : children) {
//            GridPane.setHalignment(node, HPos.CENTER);
//            GridPane.setValignment(node, VPos.CENTER);
//        }
//This needs to work but is buggy for now.I just leave it commented.
    }

    public void setLogo(Button logo) {
        this.logo = logo;
    }

    public Button getLogo() {
        return logo;
    }

    public void setLevels(GridPane levels) {
        this.levels = levels;
    }

    public GridPane getLevels() {
        return levels;
    }

    public void setPrevious(Button previous) {
        this.previous = previous;
    }

    public Button getPrevious() {
        return previous;
    }

    public void setNext(Button next) {
        this.next = next;
    }

    public Button getNext() {
        return next;
    }
}
