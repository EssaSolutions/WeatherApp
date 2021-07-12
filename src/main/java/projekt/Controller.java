package projekt;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeInLeft;
import animatefx.animation.FadeOut;
import animatefx.animation.FadeOutLeft;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class Controller {
    static ArrayList<String> weatherImgs;



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
    Label weatherImg1;

    @FXML
    Label weatherImg2;













    public void initialize() throws Exception
    {

        SVGPath weatherIcon;
        SVGPath hourIcon;

//        WebView webView = new WebView();
//        webView.getEngine().load("file:///C:/Users/kubat/Desktop/wIcons/animated/cloudy.svg");
//        final WebView browser = new WebView();
//        URL url = getClass().getResource("/projekt/images/cloudySVG.svg");
//        browser.getEngine().load(url.toExternalForm());



        ImageView sunIcon = new ImageView();












        ArrayList<SingleDay> singleDays = new ArrayList<SingleDay>();
        LanguageBox.getItems().addAll("Polski", "English");
        LanguageBox.setValue("English");
        LanguageBox.setOnAction((event) ->
        {
            new FadeIn(testPane).play();


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
                new FadeOutLeft(days).play();

                singleDays.clear();
                days.getChildren().clear();
                City city = new City((CityText.getText()).replace(" ", "+"));
                String weather = city.checkWeather();
                if (weather.equals("Rain"))
                {


                }
                int hour = city.checkHour();
                if (hour > 6 && hour < 20)
                {


                }
                else
                {

                }
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
                    new FadeInLeft(days).play();
                    CityText.setText("");

            }



            }
                catch (org.json.JSONException e){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "City " + CityText.getText() + " not found.", ButtonType.OK);
                    alert.showAndWait();
            }


        }));


        spane.setStyle("-fx-background-color: #858df1;");
        days.setStyle("-fx-background-color: #858df1;");
        //testPane.setStyle("-fx-background-color: #0c0000;");
        spane.setFitToWidth(true);


    }


}
