package projekt;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class SingleDayNode implements Initializable {

    public SingleDay day;
    @FXML public Pane pane;
    @FXML public ScrollPane spane;
    @FXML public ImageView imageView;
    @FXML public Label date;
    @FXML public Label temp;
    @FXML public Label hour;

    @Override
    public void initialize(URL location, ResourceBundle resources){

    }

    public void setDay(SingleDay day){
        Font font = new Font("projekt/fonts/FuturaLightFont.ttf", 20);
        this.day = day;
        imageView.setImage(new Image(day.getImageURL()));
        date.setText(day.dateToString());
        date.setFont(font);
        hour.setText(day.hourToString());
        hour.setAlignment(Pos.CENTER_RIGHT);
        hour.setFont(font);
        temp.setText(day.tempToString());
        temp.setAlignment(Pos.CENTER_RIGHT);
        temp.setFont(font);
        pane.setStyle("-fx-border-color: white");
    }
}
