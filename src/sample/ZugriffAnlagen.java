package sample;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ZugriffAnlagen {
    Statement stmt;

    public ResultSet fkt_Lesen(String value) throws SQLException {
        stmt = Main.con.createStatement();
        String query = String.format("Select %s from anlagen", value);
        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }

    public ResultSet fkt_Lesen_Where(String value, String where) throws SQLException {
        stmt = Main.con.createStatement();
        String query = String.format("Select %s from anlagen where %s", value, where);
        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }

    public void fkt_Statement_close(){
        try {
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
