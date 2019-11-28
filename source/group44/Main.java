package group44;

import javafx.animation.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.*;

import static group44.Constants.*;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		MainMenuStage mainMenuStage = new MainMenuStage(primaryStage);
	}
    public static void main(String[] args) {
        launch(args);
    }
}
