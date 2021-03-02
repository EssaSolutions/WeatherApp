package projekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Pane root = loader.load();
        Controller controller = loader.getController();
        controller.setButtonText("Do 2 sceny");

        Scene scene1 = new Scene(root);
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("sample2.fxml"));
        Pane root2 = loader2.load();
        Controller2 controller2 = loader2.getController();
        Scene scene2 = new Scene(root2);
        controller.getButton().setOnMouseClicked(e -> primaryStage.setScene(scene2));
        controller2.getButton().setOnMouseClicked(e -> primaryStage.setScene(scene1));
        controller2.setButtonText("Do 1 sceny");

//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        Parent root2 = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        Scene scene1 = new Scene(root,800,600);
//        Scene scene2 = new Scene(root2,800,600);
        primaryStage.setTitle("Weather Application");
        root.setStyle("-fx-background-color: linear-gradient(from 0% 10% to 0% 60%, #E0FFFF, #858df1);");
        root2.setStyle("-fx-background-color: linear-gradient(from 0% 10% to 0% 60%, #E0FFFF, #858df1);");
        primaryStage.getIcons().add(new Image("/images/weatherico.png"));
        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);

    }


}