package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {

    public ChoiceBox anlagen;
    public Label number;

    @FXML
    public void initialize() {
        try {
            ObservableList vAnlagen = FXCollections.observableArrayList();
            ZugriffAnlagen za = new ZugriffAnlagen();
            String value = "name";
            ResultSet rs = za.fkt_Lesen(value);
            while (rs.next()) {
                vAnlagen.add(rs.getString(value));
            }
            za.fkt_Statement_close();
            anlagen.setItems(vAnlagen);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void openanlagen(ActionEvent actionEvent) throws SQLException {
        ZugriffAnlagen za = new ZugriffAnlagen();
        String value = "anr";
        String where = "name = '" + anlagen.getValue() + "'";
        ResultSet rs = za.fkt_Lesen_Where(value,where);
        while (rs.next()){
            number.setText(rs.getString(value));
        }
        za.fkt_Statement_close();
    }
}
