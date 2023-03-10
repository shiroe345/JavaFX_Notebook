package sample;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Control_Main {
    public static String name = null;
    public static String name2 = null;
    int nm = 1;
    private Stage stage;
    private Scene scene;
    @FXML private TextField tt;
    @FXML private Label l1;
    @FXML private Label labele;
    @FXML private Button closeButton;

    public Control_Main() {
    }

    public void handle(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER && this.nm == 1) {
            if (this.tt.getText().length() > 10) {
                this.showStage();
            } else if (this.tt.getText().length() < 1) {
                this.showStage2();
            } else {
                Parent root = FXMLLoader.load(this.getClass().getResource("mainPage.fxml"));
                this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                this.scene = new Scene(root);
                this.scene.getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
                this.stage.setScene(this.scene);
                this.stage.show();
                Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
                stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
                stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
                name = this.tt.getText();
                this.nm = 2;
                newFile(name);
            }
        }

    }

    public void cal(ActionEvent e) {
        name2 = "calculus";
        Stage currentStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        notePage(currentStage);
    }

    public void change(ActionEvent e) {
        this.set();
    }

    public void lia(ActionEvent e) {
        name2 = "linear_algebra";
        Stage currentStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        notePage(currentStage);
    }

    public void notePage(Stage currentStage){
        try {
            currentStage.close();
            Stage primaryStage = new Stage();
            primaryStage.initStyle(StageStyle.UNDECORATED);
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResource("sample.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("CSS_Note.css").toExternalForm());
            Image icon = new Image("notes.png");
            primaryStage.getIcons().add(icon);      //Set icon
            primaryStage.setTitle("Note Beta");     //Set title
            primaryStage.setResizable(false);       //Set whether resizable
            primaryStage.setScene(scene);           //Set background of scene
            primaryStage.show();
        } catch (IOException ioe){
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new page.", ioe);
        }
    }

    public void oth(ActionEvent e){
    	name2 = "other";
        Stage currentStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        notePage(currentStage);
    }

    public void phy(ActionEvent e) {
        name2 = "physics";
        Stage currentStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        notePage(currentStage);
    }

    public void psy(ActionEvent e) {
        name2 = "psychology";
        Stage currentStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        notePage(currentStage);
    }

    public void re(ActionEvent e) {
        Stage stage = (Stage)this.closeButton.getScene().getWindow();
        stage.close();
    }

    public void set() {
        this.l1.setText(name + "'s Note");
    }

    public void showStage() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Pane pane = null;

        try {
            pane = (Pane)FXMLLoader.load(Control_Main.class.getResource("miss.fxml"));
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        Scene scene = new Scene(pane);
        scene.getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void showStage2() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Pane pane = null;

        try {
            pane = (Pane)FXMLLoader.load(Control_Main.class.getResource("miss1.fxml"));
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        Scene scene = new Scene(pane);
        scene.getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void sure(ActionEvent e) throws IOException {
        if (this.tt.getText().length() > 10) {
            this.showStage();
        } else if (this.tt.getText().length() < 1) {
            this.showStage2();
        } else {
            Parent root = FXMLLoader.load(this.getClass().getResource("mainPage.fxml"));
            this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            this.scene = new Scene(root);
            this.scene.getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
            this.stage.setScene(this.scene);
            this.stage.show();
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
            name = this.tt.getText();
            this.nm = 2;
            newFile(name);
        }
    }
    
    public void newFile(String name){
    	File file = new File("C:\\Users\\jakew\\desktop\\Notebeta\\" + name);
    	file.mkdir();
    	File file2 = new File("C:\\Users\\jakew\\desktop\\Notebeta\\" + name + "\\calculus");
    	file2.mkdir();
    	File file3 = new File("C:\\Users\\jakew\\desktop\\Notebeta\\" + name + "\\linear_algebra");
    	file3.mkdir();
    	File file4 = new File("C:\\Users\\jakew\\desktop\\Notebeta\\" + name + "\\physics");
    	file4.mkdir();
    	File file5 = new File("C:\\Users\\jakew\\desktop\\Notebeta\\" + name + "\\psychology");
    	file5.mkdir();
    	File file6 = new File("C:\\Users\\jakew\\desktop\\Notebeta\\" + name + "\\other");
    	file6.mkdir();
    	if (file != null) {
    		System.out.println("0");
    	}
    }
}
