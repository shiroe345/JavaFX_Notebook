package sample;

import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.util.LinkedList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Control_Note {
//import from fxml
    @FXML AnchorPane imagePane = new AnchorPane();
    @FXML Button inputPicture = new Button();
    @FXML Button minimize = new Button();
    @FXML ColorPicker colorPicker = new ColorPicker();
    @FXML Label pageNumber = new Label();
    @FXML TextField line_1 = new TextField();
    @FXML TextField line_2 = new TextField();
    @FXML TextField line_3 = new TextField();
    @FXML TextField line_4 = new TextField();
    @FXML TextField line_5 = new TextField();
    @FXML TextField line_6 = new TextField();
    @FXML TextField line_7 = new TextField();
    @FXML TextField line_8 = new TextField();
    @FXML TextField line_9 = new TextField();
    @FXML TextField line_10 = new TextField();
    @FXML TextField line_11 = new TextField();
    @FXML TextField line_12 = new TextField();
    @FXML TextField line_13 = new TextField();
    @FXML TextField line_14 = new TextField();
    @FXML TextField line_15 = new TextField();
    @FXML TextField line_16 = new TextField();
    @FXML TextField line_17 = new TextField();
    @FXML TextField line_18 = new TextField();
    @FXML TextField line_19 = new TextField();
    @FXML TextField line_20 = new TextField();
    @FXML TextField line_21 = new TextField();
//macro
    public static int LINES_NUMBER = 21;
//internal objects
    boolean isDragging = false;
    boolean isInResizableNW = false;
    boolean isInResizableNE = false;
    boolean isInResizableSW = false;
    boolean isInResizableSE = false;
    boolean selectedImageBool = false;
    Color[] colorLL = new Color[LINES_NUMBER];
    double resizableZoneRange = 40.0;
    ImageView selectedImage;
    LinkedList<TextField> textFieldLL = new LinkedList<>();
    LinkedList<ImageView> imageLL = new LinkedList<>();
	private Stage stage;
	private Scene scene;
	private Stage stage2;
    static PseudoClass if_bold = PseudoClass.getPseudoClass("if-bold");
    static PseudoClass if_italic = PseudoClass.getPseudoClass("if-italic");
//keyboard detection
    @FXML private void handleOnKeyPressed(KeyEvent event){
        if (event.getCode() == KeyCode.B && event.isControlDown()) //Ctrl + B
            bold_code();
        if (event.getCode() == KeyCode.I && event.isControlDown()) //Ctrl + I
            italic_code();
        switch(event.getCode()){
            case BACK_SPACE:
                backspace();
                break;
            case DOWN:
                switchLinesDOWN(false);
                break;
            case ENTER:
                switchLinesDOWN(true);
                break;
            case TAB:
                event.consume();
                Subject_Var.lastSelectedTextField.setText("����" + Subject_Var.lastSelectedTextField.getText());
                Subject_Var.lastSelectedTextField.end();
                break;
            case UP:
                switchLinesUP();
                break;
        }
    }
//initialization
    public void initialize(){
        installListenersEncapsulated();
        initialize_ll();
        try {
            File file = new File("C:\\Users\\jakew\\desktop\\NoteBeta\\" + Control_Main.name + "\\" + Control_Main.name2 + "\\page_" + Subject_Var.current_page + ".txt");
            if (!file.createNewFile()) {
                openFile_code();
            }
        } catch(IOException ioe){
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to open file", ioe);
        }
        pageNumber.setText("蝚� " + Subject_Var.current_page + " ���");
        pageNumber.setTextAlignment(TextAlignment.CENTER);
    }
    public void initialize_ll(){
        textFieldLL.addFirst(line_1);
        textFieldLL.add(line_2);
        textFieldLL.add(line_3);
        textFieldLL.add(line_4);
        textFieldLL.add(line_5);
        textFieldLL.add(line_6);
        textFieldLL.add(line_7);
        textFieldLL.add(line_8);
        textFieldLL.add(line_9);
        textFieldLL.add(line_10);
        textFieldLL.add(line_11);
        textFieldLL.add(line_12);
        textFieldLL.add(line_13);
        textFieldLL.add(line_14);
        textFieldLL.add(line_15);
        textFieldLL.add(line_16);
        textFieldLL.add(line_17);
        textFieldLL.add(line_18);
        textFieldLL.add(line_19);
        textFieldLL.add(line_20);
        textFieldLL.addLast(line_21);
    }
    public void initializeSceneLL(){
        Scene currentScene = minimize.getScene();
        Subject_Var.sceneLL.add(currentScene);
    }
//methods
    public void backspace(){
        if (Math.max(0, Subject_Var.lastSelectedTextField.getCaretPosition()) == 0 && Subject_Var.lastSelectedTextField.getSelectedText().equals("")) {
            for (int i = 0; i < LINES_NUMBER; i++) {
                if (Subject_Var.lastSelectedTextField.getId().equals(textFieldLL.get(i).getId())) {
                    if (i == 0) {
                        System.out.println("Error: This is the first line.");
                    } else {
                        textFieldLL.get(i - 1).requestFocus();
                        int index = textFieldLL.get(i - 1).getText().length();
                        textFieldLL.get(i - 1).setText(textFieldLL.get(i - 1).getText() + textFieldLL.get(i).getText());
                        for (int j = i + 1; j < LINES_NUMBER; j++) {
                            textFieldLL.get(j - 1).setText(textFieldLL.get(j).getText());
                        }
                        textFieldLL.get(i - 1).positionCaret(index);
                    }
                }
            }
        }
    }
    public void bold(ActionEvent event){
        bold_code();
    }
    public void bold_code(){
        try {
            if (Subject_Var.lastSelectedTextField.getPseudoClassStates().contains(if_bold)){
                Subject_Var.lastSelectedTextField.pseudoClassStateChanged(if_bold, false);
            }
            else{
                Subject_Var.lastSelectedTextField.pseudoClassStateChanged(if_bold, true);
            }
        }catch(Exception error){
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to catch the selected text field.", error);
        }
    }
    public void closeWindow(ActionEvent event){
        try{
            FXMLLoader exitLoader = new FXMLLoader();
            exitLoader.setLocation(getClass().getResource("exit_confirm.fxml"));
            Scene exitScene = new Scene(exitLoader.load(), 250, 100);
            Stage exitStage = new Stage();
            exitStage.setTitle("Exit?");
            exitStage.setScene(exitScene);
            exitStage.show();
        } catch (IOException ioe) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", ioe);
        }
    }
    public void colorText(){
        Color temp = colorPicker.getValue();
        int textFieldIndex;
        for (int i = 0; i < LINES_NUMBER; i++){
            if (textFieldLL.get(i) == Subject_Var.lastSelectedTextField){
                textFieldIndex = i;
                colorLL[textFieldIndex] = temp;
            }
        }
        String temp_String = temp.toString();
        temp_String = temp_String.substring(2);
        temp_String = temp_String.toUpperCase(Locale.ROOT);
        Subject_Var.lastSelectedTextField.setStyle("-fx-text-fill: #" + temp_String + ";");
    }
    public void fileChooser_picture(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("View Picture");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All files", "*.*"),
                new FileChooser.ExtensionFilter("Image files", "*.jpg", "*.png")
        );
        File file = fileChooser.showOpenDialog(inputPicture.getScene().getWindow());
        if (file != null) {
            String path = file.getAbsolutePath();
            Subject_Var.image_count += 1;
            imageviewCreate(("Image_" + Subject_Var.image_count), path, 0.0, 0.0, 0.0, 0.0);
        } else {
            System.out.println("Error: No file detected.");
        }
    }
    public void home(ActionEvent event) throws IOException{
        saveFile();
        saveFileGlobalVar();
        Stage stage = (Stage)pageNumber.getScene().getWindow();
        stage.close();
        Stage stage2 = new Stage();
        stage2.initStyle(StageStyle.DECORATED);
        Parent root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        Scene scene = new Scene(root,480,480);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        Image icon = new Image("notes.png");
        stage2.getIcons().add(icon);
        stage2.setTitle("Note Beta");
        stage2.setResizable(false);
        stage2.setScene(scene);
        stage2.show();
    }
    public void imageviewCreate(String id, String path, double x, double y, double imageWidth, double imageHeight){
        try {
            Subject_Var.imageURL.add(path);
            Image targetImage = new Image(new FileInputStream(path));
            if (imageWidth == 0.0 && imageHeight == 0.0) {
                imageHeight = targetImage.getHeight();
                imageWidth = targetImage.getWidth();
            }
            double imageRatio = imageWidth/imageHeight;
            double paneRatio = 736.0/610.0;
            ImageView targetImageView = new ImageView(targetImage);
            imageLL.add(targetImageView);
            targetImageView.setId(id);
            targetImageView.setX(x);
            targetImageView.setY(y);
            if (610 - imageWidth < 0){
                if (736 - imageHeight < 0){
                    if (imageRatio >= paneRatio){
                        targetImageView.setFitWidth(610.0);
                        targetImageView.setFitHeight(imageHeight * 610.0 / imageWidth);
                    } else {
                        targetImageView.setFitWidth(imageWidth * 736.0 / imageHeight);
                        targetImageView.setFitHeight(736.0);
                    }
                } else {
                    targetImageView.setFitWidth(imageWidth * 610.0 / imageWidth);
                    targetImageView.setFitHeight(imageHeight * 610.0 / imageWidth);
                }
            } else if (736 - imageHeight < 0) {
                targetImageView.setFitWidth(imageWidth * 736.0 / imageHeight);
                targetImageView.setFitHeight(imageHeight * 736.0 / imageHeight);
            } else {
                targetImageView.setFitWidth(imageWidth);
                targetImageView.setFitHeight(imageHeight);
            }
            targetImageView.setPreserveRatio(true);
            targetImageView.setOnDragDetected(event -> {
                System.out.println("Dragging Image: " + ((Node)event.getSource()).getId());
                selectedImage = (ImageView)event.getSource();
                selectedImage.requestFocus();
                selectedImage.startFullDrag();
            });
            targetImageView.setOnKeyPressed(event -> {
                KeyCode keyCode = event.getCode();
                if(keyCode.equals(KeyCode.DELETE)){
                    imagePane.getChildren().remove(selectedImage);
                }
            });
            targetImageView.setOnMouseClicked(event -> {
                System.out.println("Selected Image: " + ((Node) event.getSource()).getId());
                selectedImage = (ImageView)event.getSource();
                selectedImage.requestFocus();
                selectedImageBool = true;
            });
            targetImageView.setOnMouseDragged(event -> {
                if (isDragging) {
                    selectedImage = (ImageView) event.getSource();
                    selectedImage.setX(event.getX() - Subject_Var.XShift);
                    selectedImage.setY(event.getY() - Subject_Var.YShift);
                    event.setDragDetect(false);
                } else if (isInResizableSE) {
                    double mouseX = event.getX() - selectedImage.getX();
                    double mouseY = event.getY() - selectedImage.getY();
                    selectedImage = (ImageView) event.getSource();
                    selectedImage.setFitWidth(mouseX + Subject_Var.XShift);
                    selectedImage.setFitHeight(mouseY + Subject_Var.YShift);
                } else if (isInResizableNE){
                    double mouseX = event.getX() - selectedImage.getX();
                    double mouseY = selectedImage.getFitHeight() - (event.getY() - selectedImage.getY());
                    selectedImage = (ImageView) event.getSource();
                    Subject_Var.fixedY = selectedImage.getY();
                    Subject_Var.originalWidth = selectedImage.getFitWidth();
                    Subject_Var.originalHeight = selectedImage.getFitHeight();
                    selectedImage.setFitWidth(mouseX + Subject_Var.XShift);
                    selectedImage.setFitHeight(mouseY + Subject_Var.YShift);
                    selectedImage.setY(Subject_Var.fixedY + (Subject_Var.originalHeight - selectedImage.getFitHeight()));
                } else if (isInResizableSW){
                    double mouseX = selectedImage.getFitWidth() - (event.getX() - selectedImage.getX());
                    double mouseY = event.getY() - selectedImage.getY();
                    selectedImage = (ImageView) event.getSource();
                    Subject_Var.fixedX = selectedImage.getX();
                    Subject_Var.originalWidth = selectedImage.getFitWidth();
                    selectedImage.setFitWidth(mouseX + Subject_Var.XShift);
                    selectedImage.setFitHeight(mouseY + Subject_Var.YShift);
                    selectedImage.setX(Subject_Var.fixedX + (Subject_Var.originalWidth - selectedImage.getFitWidth()));
                } else if (isInResizableNW) {
                    double mouseX = selectedImage.getFitWidth() - (event.getX() - selectedImage.getX());
                    double mouseY = selectedImage.getFitHeight() - (event.getY() - selectedImage.getY());
                    selectedImage = (ImageView) event.getSource();
                    selectedImage.setFitWidth(mouseX + Subject_Var.XShift);
                    selectedImage.setFitHeight(mouseY + Subject_Var.YShift);
                    selectedImage.setX(Subject_Var.fixedX + (Subject_Var.originalWidth - selectedImage.getFitWidth()));
                    selectedImage.setY(Subject_Var.fixedY + (Subject_Var.originalHeight - selectedImage.getFitHeight()));
                }
            });
            targetImageView.setOnMouseEntered(event -> {
                selectedImage = (ImageView)event.getSource();
                double mouseX = event.getX();
                double mouseY = event.getY();
                double mouseIndirectX = mouseX - selectedImage.getX();
                double mouseIndirectY = mouseY - selectedImage.getY();
                double height = selectedImage.getFitHeight();
                double width = selectedImage.getFitWidth();
                if (mouseIndirectX >= 0 && mouseIndirectX <= resizableZoneRange){
                    if (mouseIndirectY >= 0 && mouseIndirectY <= resizableZoneRange){ //up-left
                        selectedImage.setCursor(Cursor.NW_RESIZE);
                        isInResizableNW = true;
                    }
                    else if (mouseIndirectY >= height - resizableZoneRange && mouseIndirectY <= height){ //down-left
                        selectedImage.setCursor(Cursor.SW_RESIZE);
                        isInResizableSW = true;
                    } else {
                        selectedImage.setCursor(Cursor.DEFAULT);
                    }
                } else if (mouseIndirectX >= width - resizableZoneRange && mouseIndirectX <= width){
                    if (mouseIndirectY >= 0 && mouseIndirectY <= resizableZoneRange){ //up-left
                        selectedImage.setCursor(Cursor.NE_RESIZE);
                        isInResizableNE = true;
                    } else if (mouseIndirectY >= height - resizableZoneRange && mouseIndirectY <= height){ //down-left
                        selectedImage.setCursor(Cursor.SE_RESIZE);
                        isInResizableSE = true;
                    } else {
                        selectedImage.setCursor(Cursor.DEFAULT);
                    }
                } else {
                    selectedImage.setCursor(Cursor.DEFAULT);
                }
            });
            targetImageView.setOnMouseExited(event -> {
                if(!(isInResizableNW || isInResizableSE || isInResizableNE || isInResizableSW)) {
                    selectedImage.setCursor(Cursor.DEFAULT);
                }
            });
            targetImageView.setOnMousePressed(event -> {
                if (isInResizableSE) {
                    System.out.println("Start Resizing: SE");
                    selectedImage = (ImageView) event.getSource();
                    Subject_Var.XShift = selectedImage.getX() + selectedImage.getFitWidth() - event.getX();
                    Subject_Var.YShift = selectedImage.getY() + selectedImage.getFitHeight() - event.getY();
                    event.setDragDetect(true);
                } else if (isInResizableNE) {
                    System.out.println("Start Resizing: NE");
                    selectedImage = (ImageView) event.getSource();
                    Subject_Var.XShift = selectedImage.getX() + selectedImage.getFitWidth() - event.getX();
                    Subject_Var.YShift = event.getY() - selectedImage.getY();
                    event.setDragDetect(true);
                } else if (isInResizableSW) {
                    System.out.println("Start Resizing: SW");
                    selectedImage = (ImageView) event.getSource();
                    Subject_Var.XShift = event.getX() - selectedImage.getX();
                    Subject_Var.YShift = selectedImage.getY() + selectedImage.getFitHeight() - event.getY();
                    event.setDragDetect(true);
                } else if (isInResizableNW) {
                    System.out.println("Start Resizing: NW");
                    selectedImage = (ImageView) event.getSource();
                    Subject_Var.XShift = event.getX() - selectedImage.getX();
                    Subject_Var.YShift = event.getY() - selectedImage.getY();
                    event.setDragDetect(true);
                } else {
                    isDragging = true;
                    System.out.println("Start Dragging");
                    selectedImage = (ImageView) event.getSource();
                    selectedImage.setOpacity(0.8);
                    selectedImage.setCursor(Cursor.MOVE);
                    Subject_Var.XShift = event.getX() - selectedImage.getX();
                    Subject_Var.YShift = event.getY() - selectedImage.getY();
                    event.setDragDetect(true);
                }
            });
            targetImageView.setOnMouseReleased(event -> {
                Subject_Var.XShift = 0;
                Subject_Var.YShift = 0;
                if (isInResizableSE) {
                    System.out.println("Finish Resizing: SE");
                    isInResizableSE = false;
                } else if (isInResizableSW){
                    System.out.println("Finish Resizing: SW");
                    isInResizableSW = false;
                } else if (isInResizableNW){
                    System.out.println("Finish Resizing: NW");
                    isInResizableNW = false;
                } else if (isInResizableNE){
                    System.out.println("Finish Resizing: NE");
                    isInResizableNE = false;
                } else if (isDragging) {
                    System.out.println("Finish Dragging");
                    selectedImage.setOpacity(1);
                    isDragging = false;
                }
                selectedImage.setCursor(Cursor.DEFAULT);
                event.setDragDetect(false);
            });
            imagePane.getChildren().add(targetImageView);
        } catch(FileNotFoundException e){
            System.out.println("Error: No file detected.");
        }
    }
    private void installListeners(TextField... textFields){
        for(TextField target : textFields){
            target.focusedProperty().addListener(((observable, oldValue, newValue) -> {
                Subject_Var.selectedTextField = null;
                if (newValue){
                    Subject_Var.selectedTextField = target;
                    Subject_Var.lastSelectedTextField = Subject_Var.selectedTextField;
                    System.out.println("Selected Text: " + Subject_Var.selectedTextField.getText());
                }
            }));
        }
    }
    protected void installListenersEncapsulated(){
        installListeners(line_1, line_2, line_3, line_4, line_5, line_6, line_7, line_8, line_9, line_10, line_11, line_12, line_13, line_14, line_15, line_16, line_17, line_18, line_19, line_20, line_21);
    }
    public void italic(ActionEvent event){
        italic_code();
    }
    public void italic_code(){
        try {
            if (Subject_Var.lastSelectedTextField.getPseudoClassStates().contains(if_italic)){
                Subject_Var.lastSelectedTextField.pseudoClassStateChanged(if_italic, false);
            }
            else{
                Subject_Var.lastSelectedTextField.pseudoClassStateChanged(if_italic, true);
            }
        }catch(Exception error){
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to catch the selected text field.", error);
        }
    }
    public void minimizeWindow(ActionEvent event){
        Stage minimizeStage = (Stage)minimize.getScene().getWindow();
        minimizeStage.toBack();
    }
    public void nextPage(){
        if(Subject_Var.current_page != Subject_Var.page_count) {
            saveFile();
            saveFileGlobalVar();
            Subject_Var.current_page += 1;
            Scene scene = Subject_Var.sceneLL.get(Subject_Var.current_page - 1);
            scene.getStylesheets().add(getClass().getResource("CSS_Note.css").toExternalForm());
            Stage nextPageStage = (Stage) minimize.getScene().getWindow();
            nextPageStage.setScene(scene);
            System.out.println("Directed to next page.");
        } else {
            try {
                if(Subject_Var.current_page == 1) {
                    initializeSceneLL();
                }
                saveFile();
                Subject_Var.current_page += 1;
                FXMLLoader loader = new FXMLLoader();
                Parent root = loader.load(getClass().getResource("sample.fxml"));
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("CSS_Note.css").toExternalForm());
                Stage newPageStage = (Stage) minimize.getScene().getWindow();
                Subject_Var.page_count += 1;
                newPageStage.setScene(scene);
                Subject_Var.sceneLL.add(scene);
                System.out.println("Created new page.");
            } catch (IOException ioe) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new page.", ioe);
            }
        }
    }
    public void openFile(){
        openFileGlobalVar();
        Scene scene = Subject_Var.sceneLL.get(Subject_Var.current_page - 1);
        scene.getStylesheets().add(getClass().getResource("CSS_Note.css").toExternalForm());
        Stage nextPageStage = (Stage) minimize.getScene().getWindow();
        nextPageStage.setScene(scene);
        openFile_code();
        pageNumber.setText("蝚� " + Subject_Var.current_page + " ���");
        pageNumber.setTextAlignment(TextAlignment.CENTER);
    }
    public void openFile_code(){
        File file = new File("C:\\Users\\jakew\\desktop\\Notebeta\\" + Control_Main.name + "\\" + Control_Main.name2 + "\\page_" + Subject_Var.current_page + ".txt");
        if(file != null) {
            try {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                bufferedReader.readLine();
                String bufferedLine = bufferedReader.readLine();
                boolean noImage = false;
                if(!(bufferedLine.equals("\tImage: {")) && !(bufferedLine.equals("}"))){
                    for (int i = 1; i <= LINES_NUMBER; i++){
                        if (!(bufferedLine.equals("}"))) {
                            int endIndex = (6 + (i / 10));
                            if (bufferedLine.substring(5, endIndex).equals("" + i)) {
                                textFieldLL.get(i - 1).setText(bufferedLine.substring(endIndex + 2));
                                bufferedReader.readLine();
                                bufferedLine = bufferedReader.readLine();
                                textFieldLL.get(i - 1).pseudoClassStateChanged(if_bold, bufferedLine.substring(8).equals("true")); //read bold
                                bufferedLine = bufferedReader.readLine();
                                textFieldLL.get(i - 1).pseudoClassStateChanged(if_italic, bufferedLine.substring(10).equals("true")); //read italic
                                bufferedLine = bufferedReader.readLine();
                                String temp_String = bufferedLine.substring(9);
                                temp_String = temp_String.substring(2);
                                temp_String = temp_String.toUpperCase(Locale.ROOT);
                                textFieldLL.get(i - 1).setStyle("-fx-text-fill: #" + temp_String + ";"); //read color
                                bufferedReader.readLine();
                                bufferedLine = bufferedReader.readLine();
                            }
                        } else {
                            noImage = true;
                            break;
                        }
                    }
                } else {noImage = true;}
                if (!noImage) {
                    bufferedLine = bufferedReader.readLine();
                    while (!(bufferedLine.equals("\t}"))) {
                        String id = bufferedLine.substring(6); //read image ID
                        bufferedReader.readLine();
                        String path = bufferedReader.readLine().substring(9); //read image path
                        bufferedLine = bufferedReader.readLine();
                        int separationIndex = 0;
                        double x = 0.0, y = 0.0;
                        for (int i = 16; ; i++) {
                            if (bufferedLine.charAt(i) == ',') {
                                x = Double.parseDouble(bufferedLine.substring(16, i)); //read x
                                separationIndex = i + 1;
                            } else if (bufferedLine.charAt(i) == ')') {
                                y = Double.parseDouble(bufferedLine.substring(separationIndex, i)); //read y
                                break;
                            }
                        }
                        bufferedLine = bufferedReader.readLine();
                        double width = 0.0, height = 0.0;
                        for (int i = 10; ; i++) {
                            if (bufferedLine.charAt(i) == ',') {
                                width = Double.parseDouble(bufferedLine.substring(10, i)); //read width
                                separationIndex = i + 1;
                            } else if (bufferedLine.charAt(i) == ')') {
                                height = Double.parseDouble(bufferedLine.substring(separationIndex, i)); //read height
                                break;
                            }
                        }
                        imageviewCreate(id, path, x, y, width, height);
                        bufferedReader.readLine();
                        bufferedLine = bufferedReader.readLine();
                    }
                }
                fileReader.close();
            }catch(Exception ex) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to open file.", ex);
            }
        }
    }
    public void openFileGlobalVar(){
        File file = new File("C:\\Users\\jakew\\desktop\\Notebeta\\" + Control_Main.name + "\\" + Control_Main.name2 + "\\subject_var.txt");
        if(file != null) {
            try {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                bufferedReader.readLine();
                Subject_Var.current_page = Integer.parseInt(bufferedReader.readLine().substring(15));
                Subject_Var.page_count = Integer.parseInt(bufferedReader.readLine().substring(13));
                Subject_Var.image_count = Integer.parseInt(bufferedReader.readLine().substring(14));
                bufferedReader.readLine();
                    for (int i = 0; ;i++) {
                        String bufferedLine = bufferedReader.readLine();
                        if (!(bufferedLine.equals("\t}"))) {
                            Subject_Var.imageURL.add(bufferedLine.substring(9 + ((i+1)/10)));
                        } else {
                            break;
                        }
                    }
                fileReader.close();
                try {
                    for (int i = 0; i < Subject_Var.page_count; i++) {
                        if (i == 0) {
                            initializeSceneLL();
                        } else {
                            FXMLLoader loader = new FXMLLoader();
                            Parent root = loader.load(getClass().getResource("sample.fxml"));
                            Scene scene = new Scene(root);
                            scene.getStylesheets().add(getClass().getResource("CSS_Note.css").toExternalForm());
                            Subject_Var.sceneLL.add(scene);
                        }
                    }
                } catch (IOException ioe) {
                    Logger logger = Logger.getLogger(getClass().getName());
                    logger.log(Level.SEVERE, "Failed to create new page.", ioe);
                }
            }catch(Exception ex) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to open file.", ex);
            }
        }
    }
    public void openMathWindow(ActionEvent event){
        try{
            FXMLLoader mathLoader = new FXMLLoader();
            mathLoader.setLocation(getClass().getResource("mathfunc.fxml"));
            Scene mathScene = new Scene(mathLoader.load(),631,750);
            mathScene.getStylesheets().add(getClass().getResource("CSS_Math.css").toExternalForm());
            Stage mathStage = new Stage();
            mathStage.setTitle("Generate Math Functions");
            mathStage.setScene(mathScene);
            mathStage.setX(1270);
            mathStage.setY(200);
            mathStage.show();
        } catch (IOException ioe) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new window.", ioe);
        }
    }
    public void prevPage(){
        if (Subject_Var.current_page != 1) {
            saveFile();
            saveFileGlobalVar();
            Subject_Var.current_page -= 1;
            Scene scene = Subject_Var.sceneLL.get(Subject_Var.current_page - 1);
            scene.getStylesheets().add(getClass().getResource("CSS_Note.css").toExternalForm());
            Stage prevPageStage = (Stage) minimize.getScene().getWindow();
            prevPageStage.setScene(scene);
            System.out.println("Directed to previous page.");
        } else {
            System.out.println("Error: no previous page.");
        }
    }
    public void save(ActionEvent e){
        saveFile();
        saveFileGlobalVar();
    }
    public void saveFile(){
        try {
            File file = new File("C:\\Users\\jakew\\desktop\\Notebeta\\" + Control_Main.name + "\\" + Control_Main.name2 + "\\page_" + Subject_Var.current_page + ".txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File exists, cancelled creating process.");
            }
            if(file != null) {
                try {
                    FileWriter fileWriter = new FileWriter(file);
                    fileWriter.write("{\n");
                    for(int i = 0; i < LINES_NUMBER; i++) {
                        if (!(textFieldLL.get(i).getText().equals(""))){
                            Color saveColor;
                            if (colorLL[i] == null){
                                saveColor = Color.rgb(0,0,0,1);
                            } else {
                                saveColor = colorLL[i];
                            }
                            fileWriter.write("" +
                                    "\tLine" + (i + 1) + ": " + textFieldLL.get(i).getText() + "\n\t{\n" +
                                    "\t\tBold: "+textFieldLL.get(i).getPseudoClassStates().contains(if_bold)+"\n" +
                                    "\t\tItalic: "+textFieldLL.get(i).getPseudoClassStates().contains(if_italic)+"\n" +
                                    "\t\tColor: "+saveColor+"\n\t}\n"
                            );
                        }
                    }
                    if (Subject_Var.image_count > 0) {fileWriter.write("\tImage: {\n");}
                    for (int j = 0; j < imageLL.size(); j++){
                        String path = null;
                        for(int k = 0; k < Subject_Var.image_count; k++){
                            if (imageLL.get(j).getId().equals("Image_" + (k+1))){
                                path = Subject_Var.imageURL.get(k);
                            }
                        }
                        fileWriter.write("\t\tID: "+imageLL.get(j).getId()+"\n\t\t{\n" +
                                "\t\t\tPath: " + path + "\n" +
                                "\t\t\tCoordinate: (" + imageLL.get(j).getX() + "," + imageLL.get(j).getY() + ")\n"+
                                "\t\t\tSize: (" + imageLL.get(j).getFitWidth() + "," + imageLL.get(j).getFitHeight() + ")\n\t\t}\n"
                        );
                    }
                    if (Subject_Var.image_count > 0) {fileWriter.write("\t}\n");}
                    fileWriter.write("}\n");
                    fileWriter.close();
                }catch(Exception ex) {
                    Logger logger = Logger.getLogger(getClass().getName());
                    logger.log(Level.SEVERE, "Failed to save file.", ex);
                }
            }
        } catch (IOException ioe) {
            System.out.println("An error occurred: cannot create file.");
            ioe.printStackTrace();
        }
    }
    public void saveFileGlobalVar(){
        try {
            File file = new File("C:\\Users\\jakew\\desktop\\Notebeta\\" + Control_Main.name + "\\" + Control_Main.name2 + "\\subject_var.txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File exists, cancelled creating process.");
            }
            if(file != null) {
                try {
                    FileWriter fileWriter = new FileWriter(file);
                    fileWriter.write("{\n" +
                            "\tCurrent Page: " + Subject_Var.current_page + "\n" +
                            "\tPage Count: " + Subject_Var.page_count + "\n" +
                            "\tImage Count: " + Subject_Var.image_count + "\n\t{\n");
                    for (int i = 0; i < Subject_Var.image_count; i++){
                        fileWriter.write("\t\tPath" + (i+1) + ": " + Subject_Var.imageURL.get(i) + "\n");
                    }
                    fileWriter.write("\t}\n}\n");
                    fileWriter.close();
                }catch(Exception ex) {
                    Logger logger = Logger.getLogger(getClass().getName());
                    logger.log(Level.SEVERE, "Failed to save file.", ex);
                }
            }
        } catch (IOException ioe) {
            System.out.println("An error occurred: cannot create file.");
            ioe.printStackTrace();
        }
    }

    public void switchLinesDOWN(Boolean enter){
        for (int i = 0; i < LINES_NUMBER; i++){
            if (Subject_Var.lastSelectedTextField.getId().equals(textFieldLL.get(i).getId())){
                if (i == LINES_NUMBER - 1){
                    System.out.println("Error: This is the last line.");
                }
                else {
                    if(enter){
                        for (int j = LINES_NUMBER - 2; j > i; j--){
                            textFieldLL.get(j+1).setText(textFieldLL.get(j).getText());
                        }
                        final int index = Math.max(0, textFieldLL.get(i).getCaretPosition());
                        textFieldLL.get(i+1).setText(textFieldLL.get(i).getText().substring(index));
                        textFieldLL.get(i).setText(textFieldLL.get(i).getText().substring(0, index));
                    }
                    Subject_Var.lastSelectedTextField = textFieldLL.get(i+1);
                    Subject_Var.lastSelectedTextField.requestFocus();
                    if(!enter){
                        Subject_Var.lastSelectedTextField.end();
                    }
                    else{
                        Subject_Var.lastSelectedTextField.positionCaret(0);
                    }
                    break;
                }
            }
        }
    }
    public void switchLinesUP(){
        for (int i = 0; i < LINES_NUMBER; i++){
            if (Subject_Var.lastSelectedTextField.getId().equals(textFieldLL.get(i).getId())){
                if (i == 0){
                    System.out.println("Error: This is the first line.");
                }
                else {
                    Subject_Var.lastSelectedTextField = textFieldLL.get(i-1);
                    Subject_Var.lastSelectedTextField.requestFocus();
                    Subject_Var.lastSelectedTextField.end();
                    break;
                }
            }
        }
    }
}
