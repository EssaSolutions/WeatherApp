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
import javafx.scene.web.WebView;
import javafx.util.Duration;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;

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

        BufferedImageTranscoder transcoder = new BufferedImageTranscoder();
        try (InputStream file = getClass().getResourceAsStream("/projekt/images/day.svg"))
        {
            TranscoderInput transIn = new TranscoderInput(file);
            try {
                transcoder.transcode(transIn, null);
                Image img = SwingFXUtils.toFXImage(transcoder.getBufferedImage(), null);
                sunIcon.setImage(img);
            } catch (TranscoderException ex)
            {
                ex.printStackTrace();
            }
        }
        catch (IOException io) {
            io.printStackTrace();
        }

        sunIcon.setFitHeight(100);
        sunIcon.setPreserveRatio(true);

        ImageView cloudIcon = new ImageView();

        BufferedImageTranscoder transcoder2 = new BufferedImageTranscoder();
        try (InputStream file = getClass().getResourceAsStream("/projekt/images/cloudySVG.svg"))
        {
            TranscoderInput transIn = new TranscoderInput(file);
            try {
                transcoder2.transcode(transIn, null);
                Image img = SwingFXUtils.toFXImage(transcoder2.getBufferedImage(), null);
                cloudIcon.setImage(img);
            } catch (TranscoderException ex)
            {
                ex.printStackTrace();
            }
        }
        catch (IOException io) {
            io.printStackTrace();
        }

        cloudIcon.setFitHeight(100);
        cloudIcon.setPreserveRatio(true);










        weatherIcon = new SVGPath();
        weatherIcon.setContent("M47.7,35.4     c0-4.6-3.7-8.2-8.2-8.2c-1,0-1.9,0.2-2.8,0.5c-0.3-3.4-3.1-6.2-6.6-6.2c-3.7,0-6.7,3-6.7,6.7c0,0.8,0.2,1.6,0.4,2.3     c-0.3-0.1-0.7-0.1-1-0.1c-3.7,0-6.7,3-6.7,6.7c0,3.6,2.9,6.6,6.5,6.7l17.2,0C44.2,43.3,47.7,39.8,47.7,35.4z");
        weatherIcon.setFill(Color.web("#91C0F8"));
        weatherIcon.setScaleX(2);
        weatherIcon.setScaleZ(2);
        weatherIcon.setScaleY(2);
        weatherIcon.setContent(testPane.getStyle());
        weatherImg2.setGraphic(weatherIcon);



        hourIcon = new SVGPath();
        //hourIcon.setContent("M14.5,13.2c0-3.7,2-6.9,5-8.7   c-1.5-0.9-3.2-1.3-5-1.3c-5.5,0-10,4.5-10,10s4.5,10,10,10c1.8,0,3.5-0.5,5-1.3C16.5,20.2,14.5,16.9,14.5,13.2z");
        hourIcon.setScaleX(2);
        hourIcon.setScaleZ(2);
        hourIcon.setScaleY(2);
        hourIcon.setFill(Color.ORANGE);











        //testPane.getChildren().add(weatherIcon);
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
                    weatherIcon.setContent("M47.7,35.4     c0-4.6-3.7-8.2-8.2-8.2c-1,0-1.9,0.2-2.8,0.5c-0.3-3.4-3.1-6.2-6.6-6.2c-3.7,0-6.7,3-6.7,6.7c0,0.8,0.2,1.6,0.4,2.3     c-0.3-0.1-0.7-0.1-1-0.1c-3.7,0-6.7,3-6.7,6.7c0,3.6,2.9,6.6,6.5,6.7l17.2,0C44.2,43.3,47.7,39.8,47.7,35.4z");


                }
                int hour = city.checkHour();
                if (hour > 6 && hour < 20)
                {

                    hourIcon.setContent("M14.5,13.2c0-3.7,2-6.9,5-8.7   c-1.5-0.9-3.2-1.3-5-1.3c-5.5,0-10,4.5-10,10s4.5,10,10,10c1.8,0,3.5-0.5,5-1.3C16.5,20.2,14.5,16.9,14.5,13.2z");
                    weatherImg2.setGraphic(sunIcon);

                }
                else
                {
                    hourIcon.setContent("M14.5,13.2c0-3.7,2-6.9,5-8.7   c-1.5-0.9-3.2-1.3-5-1.3c-5.5,0-10,4.5-10,10s4.5,10,10,10c1.8,0,3.5-0.5,5-1.3C16.5,20.2,14.5,16.9,14.5,13.2z");
                    weatherImg2.setGraphic(hourIcon);
                    weatherIcon.setContent("M47.7,35.4     c0-4.6-3.7-8.2-8.2-8.2c-1,0-1.9,0.2-2.8,0.5c-0.3-3.4-3.1-6.2-6.6-6.2c-3.7,0-6.7,3-6.7,6.7c0,0.8,0.2,1.6,0.4,2.3     c-0.3-0.1-0.7-0.1-1-0.1c-3.7,0-6.7,3-6.7,6.7c0,3.6,2.9,6.6,6.5,6.7l17.2,0C44.2,43.3,47.7,39.8,47.7,35.4z");
                    weatherImg1.setGraphic(weatherIcon);
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
