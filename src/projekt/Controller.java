package projekt;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class Controller {

    @FXML
    Label tekst;

    @FXML
    HBox days;

    public void sayEssa(MouseEvent mouseEvent)
    {
        tekst.setText("Essa!");
    }

    public void initialize(){
        days.getChildren().add(new SingleDayNode(new SingleDay(5, 20, 20)).getPane());
        days.getChildren().add(new SingleDayNode(new SingleDay(4, 16, 19)).getPane());
        days.getChildren().add(new SingleDayNode(new SingleDay(2, 17, 8)).getPane());
        days.getChildren().add(new SingleDayNode(new SingleDay(3, 9, -7)).getPane());
    }

}
