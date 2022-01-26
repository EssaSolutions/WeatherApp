package projekt;

import animatefx.animation.FadeInLeft;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.net.URL;
import java.util.ResourceBundle;


public class DetailsController implements Initializable {

    String language;

    @FXML Pane mainPane;
    @FXML HBox hbox;
    @FXML VBox vbox1;
    @FXML VBox vbox2;
    @FXML ImageView image;
    @FXML Label city;
    @FXML Label day;
    @FXML Label temp;
    @FXML Label weathertype;
    @FXML Label humidity;
    @FXML Label airpress;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        mainPane.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        Font font = new Font("projekt/fonts/FuturaLightFont.ttf", 30);
        city.setFont(font);
        Font font2 = new Font("projekt/fonts/FuturaLightFont.ttf", 20);
        day.setFont(font2);
        temp.setFont(font2);
        humidity.setFont(font2);
        airpress.setFont(font2);

    }

    public void setDetails(SingleDay day, City acity){
        language = Controller.language;
        if (language.equals("English"))
        {
            if(day.getImageURL() != null)
            {
                image.setImage(new Image(day.getImageURL().replace("100", "")));
                city.setText("City: " + acity.nameToString());
                temp.setText("Temperature: " + day.tempToString());
                weathertype.setText("Weather: " + day.getDescription());
                this.day.setText("Date: " + day.hourToString() + ", " + day.dateToString());
                humidity.setText("Humidity: " + day.humidityToString());
                airpress.setText("Air pressure: " + day.pressureToString());
            }
        }
        else
        {
            if(day.getImageURL() != null)
            {

                image.setImage(new Image(day.getImageURL().replace("100", "")));
                city.setText("Miasto: " + acity.nameToString());
                temp.setText("Temperatura: " + day.tempToString());
                weathertype.setText("Pogoda: " + day.getWeatherPL());
                this.day.setText("Data: " + day.hourToString() + ", " + day.dateToString());
                humidity.setText("Wilgotność: " + day.humidityToString());
                airpress.setText("Ciśnienie: " + day.pressureToString());
            }
        }

    }

    public void playAnimations() throws InterruptedException {
        new FadeInLeft(image).play();
        new FadeInLeft(city).play();
        new FadeInLeft(temp).play();
        new FadeInLeft(weathertype).play();
        new FadeInLeft(this.day).play();
        new FadeInLeft(airpress).play();
        new FadeInLeft(humidity).play();
    }
}

