package projekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application
{

    public static Stage mainStage;


    @Override
    public void start(Stage primaryStage) throws Exception
    {

        mainStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"));
        Pane root = loader.load();
        Scene scene1 = new Scene(root);
        primaryStage.setTitle("Weather Application");
        root.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 0% 40%, #E0FFFF, #858df1);");
        primaryStage.getIcons().add(new Image("projekt/images/weatherico.png"));
        primaryStage.setScene(scene1);
        primaryStage.setMinHeight(880);
        primaryStage.setMinWidth(1170);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }



}