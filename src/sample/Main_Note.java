//package sample;
//
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.Parent;
//import javafx.scene.image.Image;
//import javafx.stage.Stage;
//import javafx.stage.StageStyle;
//
//public class Main extends Application {
//
//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        primaryStage.initStyle(StageStyle.UNDECORATED);
//        FXMLLoader loader = new FXMLLoader();
//        Parent root = loader.load(getClass().getResource("sample.fxml"));
//        Scene scene = new Scene(root);
//        scene.getStylesheets().add(getClass().getResource("CSS_Note.css").toExternalForm());
//        Image icon = new Image("notes.png");
//        primaryStage.getIcons().add(icon);      //Set icon
//        primaryStage.setTitle("Note Beta");     //Set title
//        primaryStage.setResizable(false);       //Set whether resizable
//        primaryStage.setScene(scene);           //Set background of scene
//        primaryStage.show();
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
