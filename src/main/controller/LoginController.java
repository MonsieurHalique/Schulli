import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    /**
     * * Attribute
     */
    private DatenbankZugriff dz;
    private Stage stage;

    public TextField userField;
    public PasswordField passwdField;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
    }

    @FXML
    public void buttonChange(ActionEvent actionEvent) {
    }

    public void anmelden(ActionEvent actionEvent) {
        try {
            dz = DatenbankZugriff.getInstance(userField.getText(), passwdField.getText());
            dz.setStatements();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
            Parent view = fxmlLoader.load();

            MainController mainController = fxmlLoader.<MainController>getController();
            mainController.setStage(stage);

            stage.setScene(new Scene(view));
            stage.centerOnScreen();
            stage.show();
        } catch (SQLException throwables) {
            JOptionPane.showMessageDialog(null, "Benutzer oder Passwort falsch", "Anmeldung Error", JOptionPane.WARNING_MESSAGE);
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
