package projekt;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SingleDayNode {

    public SingleDay day;
    public Pane pane;
    public ImageView imageView;
    public Label date;
    public Label temp;

    public SingleDayNode(SingleDay day){
        pane = new Pane();
        pane.setMinSize(110, 100);
        imageView = new ImageView("/images/SunWhite.png");
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        imageView.relocate(10, 10);
        temp = new Label(day.tempToString());
        temp.relocate(60, 35);
        temp.setTextFill(Color.web("#ffffff"));
        temp.setFont(new Font("Arial", 20));
        date = new Label(day.dateToString());
        date.relocate(10, 70);
        date.setTextFill(Color.web("#ffffff"));
        date.setFont(new Font("Arial", 15));
        pane.getChildren().addAll(imageView, temp, date);
        pane.setStyle("-fx-border-color: white");
    }

    public Pane getPane(){
        return pane;
    }
}
