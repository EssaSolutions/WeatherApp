package projekt;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class Controller {

    @FXML
    Label tekst;

    public void sayEssa(MouseEvent mouseEvent)
    {
        tekst.setText("Essa!");
    }

}
