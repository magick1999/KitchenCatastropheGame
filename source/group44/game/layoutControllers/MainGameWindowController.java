package group44.game.layoutControllers;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;


public class MainGameWindowController {
    @FXML
    private Button homeButton;
    @FXML
    private Button restartButton;
    @FXML
    private Button resumeButton;
    @FXML
    private StackPane root;
    @FXML
    private Canvas canvas;
    @FXML
    private VBox menuBox;
    @FXML
    private Pane movableObjects;
    public MainGameWindowController(){

    }

    @FXML
    public void initialize(){
        menuBox.setVisible(false);
    }

    public Button getHomeButton() {
        return homeButton;
    }

    public void setHomeButton(Button homeButton) {
        this.homeButton = homeButton;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public Canvas getCanvas(){
        return canvas;
    }

    public void setRoot(StackPane root) {
        this.root = root;
    }

    public StackPane getRoot() {
        return root;
    }

    public void setRestartButton(Button restartButton) {
        this.restartButton = restartButton;
    }

    public Button getRestartButton() {
        return restartButton;
    }

    public void setResumeButton(Button resumeButton) {
        this.resumeButton = resumeButton;
    }

    public Button getResumeButton() {
        return resumeButton;
    }

    public void setMenuBox(VBox menuBox) {
        this.menuBox = menuBox;
    }

    public VBox getMenuBox() {
        return menuBox;
    }

    public void setMovableObjects(Pane movableObjects) {
        this.movableObjects = movableObjects;
    }

    public Pane getMovableObjects() {
        return movableObjects;
    }
}
