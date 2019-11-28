package group44;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import static group44.Constants.WINDOW_HEIGHT;
import static group44.Constants.WINDOW_WIDTH;

public class MainMenuStage extends Stage {
    MainMenuController mainMenuController;
    Stage primaryStage;
    public MainMenuStage(Stage primaryStage) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/group44/mainMenu.fxml"));
        this.primaryStage = primaryStage;
        try {
            Parent root = fxmlLoader.load();
            //Setting the stage and adding my custom style to it.
            root.getStylesheets().add("group44/resources/application.css");
            root.setId("pane");
            Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
            //Adding the key listener to the scene.
//            scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> processKeyEvent(event));
            MainMenuController tempController = fxmlLoader.getController();
            setController(tempController);
            primaryStage.setScene(scene);
            primaryStage.show();
            setUpButtons();
        } catch (Exception e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("Hello World");

    }
    private void setController(MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
    }
    private void pressPlay(MouseEvent e){
        new LevelSelectorScene(primaryStage);
    }
    private void setUpButtons(){
        mainMenuController.getPlay().setOnMouseClicked(this::pressPlay);
    }
    /**
     * This method handles the keyboard input.
     *
     * @param event Passes in the events from the keyboard.
     */
//    public void processKeyEvent(KeyEvent event) {
//        switch (event.getCode()) {
//            case ESCAPE: {
//
//                break;
//            }
//            case LEFT: {
//
//                break;
//            }
//            case RIGHT: {
//
//            }
//            case UP: {
//                break;
//            }
//            case DOWN: {
//
//                break;
//            }
//            case ENTER:{
//
//                break;
//            }
//            default:
//                // Do nothing
//                break;
//        }
//
//        // Redraw game as the player may have moved.
//        // Consume the event. This means we mark it as dealt with. This stops other GUI nodes (buttons etc) responding to it.
//        event.consume();
//    }
}
