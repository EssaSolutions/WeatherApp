package projekt;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Controller {

    @FXML
    Label tekst;

    @FXML
    HBox days;

    public void sayEssa(MouseEvent mouseEvent)
    {
        tekst.setText("Essa!");
    }
    @FXML
    Button button1;

    public Button getButton()
    {
       return this.button1;
    }

    public void setButtonText(String text)
    {
        button1.setText(text);
    }

    public void initialize()
    {
        days.getChildren().add(new SingleDayNode(new SingleDay(5, 20, 17, 5)).getPane());
        days.getChildren().add(new SingleDayNode(new SingleDay(5, 20, 19, 12)).getPane());
        days.getChildren().add(new SingleDayNode(new SingleDay(5, 20, 18, 18)).getPane());
        days.getChildren().add(new SingleDayNode(new SingleDay(5, 20, 15, 22)).getPane());
    }

}
