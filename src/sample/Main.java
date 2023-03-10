package sample;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public Main() {
    }

    public void start(Stage primaryStage) throws IOException {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("frontpage.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
            Image icon = new Image("notes.png");
            primaryStage.getIcons().add(icon);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Note");
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
