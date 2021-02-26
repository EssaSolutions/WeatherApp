package projekt;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller2 implements Initializable
{

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    @FXML
    Label tekst;

    @FXML
    HBox days;

    public void sayEssa(MouseEvent mouseEvent)
    {
        tekst.setText("Essa!");
    }
    @FXML
    Button button2;

    public Button getButton()
    {
        return this.button2;
    }

    public void setButtonText(String text)
    {
        button2.setText(text);
    }




}



