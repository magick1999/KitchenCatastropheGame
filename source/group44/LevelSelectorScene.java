package group44;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


import static group44.Constants.WINDOW_HEIGHT;
import static group44.Constants.WINDOW_WIDTH;

public class LevelSelectorScene {
    LevelSelectorController levelSelectorController;
    Stage primaryStage;
    public LevelSelectorScene(Stage primaryStage){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/group44/levelSelector.fxml"));
        this.primaryStage=primaryStage;
        try {
            Parent root = fxmlLoader.load();
            //Setting the stage and adding my custom style to it.
            root.getStylesheets().add("group44/resources/application.css");
            root.setId("pane");
            Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
            //Adding the key listener to the scene.
//            scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> processKeyEvent(event));
            LevelSelectorController tempController = fxmlLoader.getController();
            setController(tempController);
            primaryStage.setScene(scene);
            primaryStage.show();
            setUpButtons();
        } catch (Exception e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("Hello World");
    }

    private void setController(LevelSelectorController levelSelectorController){
        this.levelSelectorController = levelSelectorController;
    }

    private void setLevelSelect(MouseEvent e){
        new GameScene(primaryStage);
    }

    private void loadLevels(){
        ObservableList<Node> children = levelSelectorController.getLevels().getChildren();
        for (Node node : children) {
//            node.setOnMouseClicked(MouseEvent.MOUSE_CLICKED,event -> setLevelSelect(event));
            node.setOnMouseClicked(this::setLevelSelect);
        }
    }
    private void setUpButtons(){
        loadLevels();
    }
}
