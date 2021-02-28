package projekt;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.util.ArrayList;

public class Controller {

    @FXML
    Label tekst;

    @FXML
    HBox days;

    @FXML
    TextField CityName;

    @FXML
    ChoiceBox <String> CityBox;

    @FXML
    ChoiceBox <String> LanguageBox;

    public void cityBoxClicked()
    {
        JInternalFrame primaryStage = null;
        assert false;
        primaryStage.setTitle("Aplikacja pogodowa");

    }

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

    public void initialize() throws Exception
    {

        CityBox.getItems().addAll("Warsaw","Breslau","Danzig");
        LanguageBox.getItems().addAll("Polski","English");
        LanguageBox.setValue("English");
        LanguageBox.setOnAction((event) ->
        {
            if (((String) LanguageBox.getValue()).equals("Polski"))
            {

                CityName.setPromptText("Wybierz miasto");
            }
            else
            {
                CityName.setPromptText("Enter the city name");
            }

        });


        ArrayList<SingleDay> singleDays = new ArrayList<SingleDay>();
        singleDays.add(new SingleDay(5, 20, 17, 5));
        singleDays.add(new SingleDay(5, 20, 19, 12));
        singleDays.add(new SingleDay(5, 20, 18, 18));
        singleDays.add(new SingleDay(5, 20, 15, 22));

        for(int i = 0; i < singleDays.size(); i++){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("singleday.fxml"));
            Pane pane = loader.load();
            SingleDay day = singleDays.get(i);
            SingleDayNode controller = loader.getController();
            controller.setDay(day);
            days.getChildren().add(pane);
        }
    }

}
