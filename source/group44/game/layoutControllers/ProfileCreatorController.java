package group44.game.layoutControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import javax.xml.soap.Text;


public class ProfileCreatorController {
    @FXML
    private BorderPane root;
    @FXML
    private VBox leftVBox;
    @FXML
    private VBox centerVBox;
    @FXML
    private Button confirm;
    @FXML
    private ImageView logo;
    public ProfileCreatorController(){
    }
    @FXML
    public void initialize(){
        logo.setImage(new Image("/group44/resources/kitchenLogo.png"));
    }
    public void setRoot(BorderPane root) {
        this.root = root;
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setLeftVBox(VBox leftVBox) {
        this.leftVBox = leftVBox;
    }

    public VBox getLeftVBox() {
        return leftVBox;
    }

    public void setCenterVBox(VBox centralVBox) {
        this.centerVBox = centralVBox;
    }

    public VBox getCenterVBox() {
        return centerVBox;
    }

    public void setConfirm(Button confirm) {
        this.confirm = confirm;
    }

    public Button getConfirm() {
        return confirm;
    }

    public void setLogo(ImageView logo) {
        this.logo = logo;
    }

    public ImageView getLogo() {
        return logo;
    }


}
