package projekt;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.web.WebView;
import javafx.util.Duration;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
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

    @FXML
    Pane testPane;

    @FXML
    Label weatherImg;









    public void initialize() throws Exception
    {
        SVGPath githubIcon;

//        WebView webView = new WebView();
//        webView.getEngine().load("file:///C:/Users/kubat/Desktop/wIcons/animated/cloudy.svg");
//        final WebView browser = new WebView();
//        URL url = getClass().getResource("/projekt/images/cloudySVG.svg");
//        browser.getEngine().load(url.toExternalForm());









        githubIcon = new SVGPath();
        githubIcon.setContent("M47.7,35.4     c0-4.6-3.7-8.2-8.2-8.2c-1,0-1.9,0.2-2.8,0.5c-0.3-3.4-3.1-6.2-6.6-6.2c-3.7,0-6.7,3-6.7,6.7c0,0.8,0.2,1.6,0.4,2.3     c-0.3-0.1-0.7-0.1-1-0.1c-3.7,0-6.7,3-6.7,6.7c0,3.6,2.9,6.6,6.5,6.7l17.2,0C44.2,43.3,47.7,39.8,47.7,35.4z");
        githubIcon.setFill(Color.web("#91C0F8"));
        githubIcon.setScaleX(2);
        githubIcon.setScaleZ(2);
        githubIcon.setScaleY(2);
        weatherImg.setGraphic(githubIcon);



        //testPane.getChildren().add(githubIcon);
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



            }
                catch (org.json.JSONException e){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "City " + CityText.getText() + " not found.", ButtonType.OK);
                    alert.showAndWait();
            }


        }));


        spane.setStyle("-fx-background-color: #858df1;");
        days.setStyle("-fx-background-color: #858df1;");
//        spane.prefWidthProperty().bind(bpane.prefWidthProperty());
//        days.prefWidthProperty().bind(spane.prefWidthProperty());
        spane.setFitToWidth(true);


    }

}
