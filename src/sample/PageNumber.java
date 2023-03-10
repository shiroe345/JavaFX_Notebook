package sample;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.Group;

public class PageNumber {
    private double size = 25;
    private String font = "Meiryo UI";
    private Color color = Color.rgb(0,0,0,0.55);

    public void createPageNum(double width, double height, int pageCount, Group root){
        Text text = new Text();
        text.setText(String.valueOf(pageCount));
        text.setX(width - 30);
        text.setY(height - 45);
        text.setFont(Font.font(font, size));
        text.setFill(color);

        root.getChildren().add(text);
    }

}
