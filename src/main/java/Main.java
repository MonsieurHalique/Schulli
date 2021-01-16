import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        /**
         * * Aufbau der Scene
         */
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Schulli");
        primaryStage.setScene(new Scene(root, 1280, 1024));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
