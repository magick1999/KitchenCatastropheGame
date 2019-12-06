package group44.game.layoutControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;


public class LevelSelectorController {
    @FXML
    private ImageView logo;
    @FXML
    private Button levelNum;
    @FXML
    private Button previous;
    @FXML
    private Button next;
    @FXML
    private Button play;
    @FXML
    private ListView levelTimes;
    @FXML
    private Button menu;
    @FXML
    private BorderPane root;
    public LevelSelectorController(){
    }
    @FXML
    public void initialize(){
        logo.setImage(new Image("/group44/resources/KitchenCatastrophe.png"));
    }
    public void setMenu(Button menu) {
        this.menu = menu;
    }

    public Button getMenu() {
        return menu;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setLevelNum(Button levelNum) {
        this.levelNum = levelNum;
    }

    public Button getLevelNum() {
        return levelNum;
    }

    public void setLevelTimes(ListView levelTimes) {
        this.levelTimes = levelTimes;
    }

    public ListView getLevelTimes() {
        return levelTimes;
    }

    public void setPlay(Button play) {
        this.play = play;
    }

    public Button getPlay() {
        return play;
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
