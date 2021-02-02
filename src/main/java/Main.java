import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        int height = 400;
        int width = 600;
        /**
         * * Aufbau der Scene
         */
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = fxmlLoader.load();

        LoginController loginController = fxmlLoader.<LoginController>getController();
        loginController.setStage(primaryStage);



        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("bild.png")));
        primaryStage.setTitle("Schulli");
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.setMinHeight(height);
        primaryStage.setMinWidth(width);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
