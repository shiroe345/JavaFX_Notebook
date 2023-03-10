package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class NoteLine{
    private double x = 40, y = 100;
    private int strokeWidth = 1;
    private Color color = Color.rgb(0,0,0,0.35);

    public void createLines(double height, double appWidth, double lineWidth, Group root){
        for (double temp_y = y; temp_y <= height; temp_y = temp_y + lineWidth) {
            Line line = new Line();
            line.setStartX(x);
            line.setStartY(temp_y);
            line.setEndX(appWidth - x);
            line.setEndY(temp_y);
            line.setStrokeWidth(strokeWidth);
            line.setStroke(color);

            root.getChildren().add(line);
        }
    }
}
