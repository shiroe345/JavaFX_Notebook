package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Control_ExitConfirm {
    @FXML Button yes = new Button();

    public void yes(){
        Stage thisStage = (Stage) yes.getScene().getWindow();
        thisStage.close();
        System.exit(0);
    }

    public void no(){
        Stage thisStage = (Stage) yes.getScene().getWindow();
        thisStage.close();
    }

}
