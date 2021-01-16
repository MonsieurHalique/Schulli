import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {
    /**
     * * Attribute
    */
    private DatenbankZugriff dz;
    public Label number;
    public ComboBox anlagen;

    @FXML
    public void initialize() {
        dz = DatenbankZugriff.getInstance();

        String[] value_arr = {"name"};
        String database = "anlagen";

        anlagen.setPromptText("Wähle Anlage!");
        try {
            ResultSet rs = dz.fkt_Lesen(value_arr, database);
            while (rs.next()) {
                anlagen.getItems().add(rs.getString(value_arr[0]));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void leseDaten(ActionEvent actionEvent) {
        System.out.println(anlagen.getSelectionModel().getSelectedIndex()+1);
        //TODO
    }
}