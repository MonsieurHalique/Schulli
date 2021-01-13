package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ZugriffDaten {

    public ResultSet fkt_Lesen(String value) throws SQLException {
        Statement stmt = Main.con.createStatement();
        String query = String.format("Select %s from daten", value);
        ResultSet rs = stmt.executeQuery(query);
        stmt.close();
        return rs;
    }
}
