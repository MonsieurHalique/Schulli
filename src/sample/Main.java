package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application {
    protected static Connection con;
    @Override
    public void start(Stage primaryStage) throws Exception{
        String url = "jdbc:mysql://localhost:3306/schulli?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin";
        String user = "root";
        String password = "root";
        try {
            con = DriverManager.getConnection(url,user,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Schulli");
        primaryStage.setScene(new Scene(root, 1280, 1024));
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
