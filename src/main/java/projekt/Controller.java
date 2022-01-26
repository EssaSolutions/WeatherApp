package projekt;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeInLeft;
import animatefx.animation.FadeOut;
import animatefx.animation.FadeOutLeft;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Controller {
    static String language;
    public Object lock = new Object ();


    @FXML
    HBox days;

    @FXML
    TextField CityText;

    @FXML
    ChoiceBox<String> LanguageBox;

    @FXML
    GridPane mainGridPane;

    @FXML
    ScrollPane spane;

    @FXML
    BorderPane bpane;
    @FXML
    Button slideShowButton;

    @FXML
    Button StopButton;

    public void initialize() throws Exception {

        slideShowButton.setVisible(false);
        StopButton.setVisible(false);
        ArrayList<SingleDay> singleDays = new ArrayList<SingleDay>();
        LanguageBox.getItems().addAll("Polski", "English");
        LanguageBox.setValue("English");
        LanguageBox.setOnAction((event) ->
        {
            if ((LanguageBox.getValue()).equals("Polski")) {
                language = "Polski";
                CityText.setPromptText("Wybierz miasto i wcisnij Enter");
                Main.mainStage.setTitle("Aplikacja pogodowa");
            } else {
                language = "English";
                CityText.setPromptText("Enter the city name and press Enter");
                Main.mainStage.setTitle("Weather Application");

            }


        });
        CityText.setTooltip(new Tooltip("200 thousand cities supported!"));
        CityText.setOnAction((actionEvent -> {



            try {
                slideShowButton.setVisible(true);
                StopButton.setVisible(true);
                language = LanguageBox.getValue();
                new FadeOutLeft(days).play();
                singleDays.clear();
                days.getChildren().clear();
                City city = new City((CityText.getText()).replace(" ", "+"));

                singleDays.addAll(city.getDays());
                try {
                    if (mainGridPane.getChildren().get(1) != null)
                        mainGridPane.getChildren().remove(1);
                } catch (IndexOutOfBoundsException e) {

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
                    new FadeInLeft(days).play();
                    CityText.setText("");
                    controller.getPane().setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            try {
                                if (mainGridPane.getChildren().get(1) != null)
                                    mainGridPane.getChildren().remove(1);
                            } catch (IndexOutOfBoundsException ef) {

                            }
                            FXMLLoader dloader = new FXMLLoader(getClass().getResource("/details.fxml"));
                            Pane pane = null;
                            try {
                                pane = dloader.load();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            pane.setPrefWidth(mainGridPane.getPrefWidth());
                            DetailsController controller2 = dloader.getController();
                            controller2.setDetails(controller.getDay(), city);
                            pane.setPrefWidth(mainGridPane.getPrefWidth());
                            mainGridPane.add(pane, 1, 1);
                            try {
                                controller2.playAnimations();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }


                        }

                    });
                    slideShowButton.setOnAction((actionEvent2 ->
                    {

                        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
                        ScheduledFuture files= executorService.scheduleAtFixedRate(new Runnable() {
                            @Override
                            public void run()
                            {
                                for (double ii = 0; ii< 1000 ; ii++)
                                {
                                    try {
                                        double tmp = ii/1000;
                                        spane.setHvalue(tmp);
                                        Thread.sleep(15);
                                    } catch (InterruptedException e) {
                                        break;
                                    }
                                }
                            }
                        }, 0, 200, TimeUnit.MILLISECONDS);

                        StopButton.setOnAction((ActionEvent ->
                        {
                            spane.setHvalue(0);
                            files.cancel(true);




                        }));
                    }));
                }


                FXMLLoader dloader = new FXMLLoader(getClass().getResource("/details.fxml"));
                Pane pane = null;
                try {
                    pane = dloader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                pane.setPrefWidth(mainGridPane.getPrefWidth());
                DetailsController controller = dloader.getController();
                controller.setDetails(singleDays.get(0), city);
                mainGridPane.add(pane, 1, 1);
                controller.playAnimations();
            } catch (JSONException | InterruptedException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "City " + CityText.getText() + " not found.", ButtonType.OK);
                alert.showAndWait();
                new FadeInLeft(days).play();
                try {
                    if (mainGridPane.getChildren().get(1) != null)
                        mainGridPane.getChildren().remove(1);
                } catch (IndexOutOfBoundsException ef) {

                }
            }


        }));

        spane.setStyle("-fx-background-color: #858df1;");
        days.setStyle("-fx-background-color: #858df1;");
        spane.setFitToWidth(true);
        mainGridPane.setPrefWidth(bpane.getWidth());

    }



}
