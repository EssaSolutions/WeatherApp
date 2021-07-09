package projekt;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    @FXML
    HBox days;

    @FXML
    TextField CityText;

    @FXML
    ChoiceBox<String> LanguageBox;

    @FXML
    Pane pane;

    @FXML
    GridPane mainGridPane;

    @FXML
    FlowPane mainFlowPane;

    @FXML
    ScrollPane spane;

    @FXML
    BorderPane bpane;

    public void initialize() throws Exception
    {
        ArrayList<SingleDay> singleDays = new ArrayList<SingleDay>();
        LanguageBox.getItems().addAll("Polski", "English");
        LanguageBox.setValue("English");
        LanguageBox.setOnAction((event) ->
        {

            if (((String) LanguageBox.getValue()).equals("Polski"))
            {
                CityText.setPromptText("Wybierz miasto i wcisnij Enter");
                Main.mainStage.setTitle("Aplikacja pogodowa");

            } else {
                CityText.setPromptText("Enter the city name and press Enter");
                Main.mainStage.setTitle("Weather Application");
            }

        });
        CityText.setTooltip(new Tooltip("200 thousand cities supported!"));
        CityText.setOnAction((actionEvent -> {
            try {
                singleDays.clear();
                days.getChildren().clear();
                City city = new City((CityText.getText()).replace(" ", "+"));
                singleDays.addAll(city.getDays());
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
            } catch (org.json.JSONException e){
                Alert alert = new Alert(Alert.AlertType.ERROR, "City " + CityText.getText() + " not found.", ButtonType.OK);
                alert.showAndWait();
            }

        }));
        spane.setStyle("-fx-background-color: #858df1;");
        days.setStyle("-fx-background-color: #858df1;");
        spane.setFitToWidth(true);
    }

}
