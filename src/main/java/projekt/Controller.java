package projekt;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {



    @FXML
    Label tekst;

    @FXML
    HBox days;

    @FXML
    TextField CityText;

    @FXML
    ChoiceBox <String> CityBox;

    @FXML
    ChoiceBox <String> LanguageBox;


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


        ArrayList<SingleDay> singleDays = new ArrayList<SingleDay>();
        CityBox.getItems().addAll("Warsaw","Breslau","Danzig");
        CityBox.setValue("Choose your city");
        LanguageBox.getItems().addAll("Polski","English");
        LanguageBox.setValue("English");
        LanguageBox.setOnAction((event) ->
        {
            if (((String) LanguageBox.getValue()).equals("Polski"))
            {


                CityText.setPromptText("Wybierz miasto i wcisnij Enter");

            }
            else
            {
                CityText.setPromptText("Enter the city name and press Enter");
            }

        });
        CityText.setTooltip(new Tooltip("Warsaw, Breslau and Danzig are currently supported"));
        CityText.setOnAction((actionEvent -> {

            singleDays.clear();
            days.getChildren().clear();
            if (CityText.getText().equals("Warsaw")) {
                    singleDays.add(new SingleDay(5, 20, 17, 5));
                    singleDays.add(new SingleDay(5, 20, 19, 12));
                    singleDays.add(new SingleDay(5, 20, 18, 18));
                    singleDays.add(new SingleDay(5, 20, 15, 22));


                } else if (CityText.getText().equals("Breslau")) {
                    singleDays.add(new SingleDay(6, 21, 10, 5));
                    singleDays.add(new SingleDay(6, 21, 11, 12));
                    singleDays.add(new SingleDay(6, 21, 12, 18));
                    singleDays.add(new SingleDay(6, 21, 13, 22));

                } else if (CityText.getText().equals("Danzig")) {
                    singleDays.add(new SingleDay(7, 22, 23, 5));
                    singleDays.add(new SingleDay(7, 22, 24, 12));
                    singleDays.add(new SingleDay(7, 22, 25, 18));
                    singleDays.add(new SingleDay(7, 22, 26, 22));

                }
                for (int i = 0; i < singleDays.size(); i++) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/singleday.fxml"));
                    Pane pane = null;
                    try {
                        pane = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    SingleDay day = singleDays.get(i);
                    SingleDayNode controller = loader.getController();
                    controller.setDay(day);
                    days.getChildren().add(pane);
                }


        }));


        CityBox.setOnAction((event) ->
        {
            singleDays.clear();
            days.getChildren().clear();

            if (((String) CityBox.getValue()).equals("Warsaw"))
                {
                    singleDays.add(new SingleDay(5, 20, 17, 5));
                    singleDays.add(new SingleDay(5, 20, 19, 12));
                    singleDays.add(new SingleDay(5, 20, 18, 18));
                    singleDays.add(new SingleDay(5, 20, 15, 22));


                } else if (((String) CityBox.getValue()).equals("Breslau")) {
                    singleDays.add(new SingleDay(6, 21, 10, 5));
                    singleDays.add(new SingleDay(6, 21, 11, 12));
                    singleDays.add(new SingleDay(6, 21, 12, 18));
                    singleDays.add(new SingleDay(6, 21, 13, 22));

                } else if (((String) CityBox.getValue()).equals("Danzig")) {
                    singleDays.add(new SingleDay(7, 22, 23, 5));
                    singleDays.add(new SingleDay(7, 22, 24, 12));
                    singleDays.add(new SingleDay(7, 22, 25, 18));
                    singleDays.add(new SingleDay(7, 22, 26, 22));

                }
                for (int i = 0; i < singleDays.size(); i++) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/singleday.fxml"));
                    Pane pane = null;
                    try {
                        pane = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    SingleDay day = singleDays.get(i);
                    SingleDayNode controller = loader.getController();
                    controller.setDay(day);
                    days.getChildren().add(pane);
                }


        });






    }

}
