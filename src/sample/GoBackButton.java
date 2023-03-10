package sample;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GoBackButton {
    private double x = 30, y = 30;

    public void createButton(Group root){
        Image goBackURL = new Image("goBack.png");
        ImageView goBackAdd = new ImageView(goBackURL);
        Button goBackButton = new Button();
        goBackButton.setLayoutX(x);
        goBackButton.setLayoutY(y);
        goBackButton.setStyle("-fx-background-color: #e9e9e9ff; ");
        goBackButton.setGraphic(goBackAdd);
        root.getChildren().add(goBackButton);
    }
}
