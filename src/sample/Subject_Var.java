package sample;

import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.util.LinkedList;

public class Subject_Var {
    public static double XShift = 0.0;
    public static double YShift = 0.0;
    public static int current_page = 1;
    public static int image_count = 0;
    public static int page_count = 1;
    public static LinkedList<String> imageURL = new LinkedList<>();
    public static LinkedList<Scene> sceneLL = new LinkedList<Scene>();
    public static TextField lastSelectedTextField;
    public static TextField selectedTextField;
}
