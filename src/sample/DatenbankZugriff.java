package sample;

import java.sql.*;

public class DatenbankZugriff {

    /*
    Attribute
     */
    private Connection con;
    private static DatenbankZugriff instance;
    private Statement stmt;

    /*
    Constructor
     */
    private DatenbankZugriff() {
        /*
        Verbindung zur Datenbank wird erstellt
         */
        String url = "jdbc:mysql://localhost:3306/schulli?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin";
        String user = "root";
        String password = "root";

        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /*
    Ausgabe der Instance
     */
    public static DatenbankZugriff getInstance() {
        if (DatenbankZugriff.instance == null) {
            DatenbankZugriff.instance = new DatenbankZugriff();
        }
        return DatenbankZugriff.instance;
    }

    /*
    Schliessen des Statement
     */
    public void fkt_Statement_close() {
        try {
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /*
    Lesen aus der Datenbank
     */
    public ResultSet fkt_Lesen(String[] value_arr, String database) throws SQLException {
        String select = getString(value_arr, ", ");
        String query = String.format("Select %s from %s", select, database);
        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }

    public ResultSet fkt_Lesen(String[] value_arr, String database, String where) throws SQLException {
        String select = getString(value_arr, ", ");
        String query = String.format("Select %s from %s where %s", select, database, where);
        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }

    /*
    Aufbereitung des String Arrays zu einem String
     */
    private String getString(String[] str, String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (String s : str) {
            sb.append(s).append(delimiter);
        }
        return sb.substring(0, sb.length() - delimiter.length());
    }
}
