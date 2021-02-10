import java.sql.*;

public class DatenbankZugriff {
    /**
     * * Attribute
     */
    private Connection con;
    private static DatenbankZugriff instance;
    private Statement stmt;
    private ResultSet rs;

    /**
     * * Constructor
     */
    private DatenbankZugriff(String user, String passwd) throws SQLException {
        /**
         * * Verbindung zur Datenbank wird erstellt
         */
        String url = "jdbc:mysql://raspberrypi/schulli?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin";

        con = DriverManager.getConnection(url, user, passwd);
    }

    /**
     * * Ausgabe der Instance
     *
     * @return gibt eine Instance der Klasse zurück
     */
    public static DatenbankZugriff getInstance() throws SQLException {
        return DatenbankZugriff.instance;
    }

    /**
     * * Ausgabe der Instance
     *
     * @return gibt eine Instance der Klasse zurück
     */
    public static DatenbankZugriff getInstance(String user, String passwd) throws SQLException {
        if (DatenbankZugriff.instance == null) {
            DatenbankZugriff.instance = new DatenbankZugriff(user, passwd);
        }
        return DatenbankZugriff.instance;
    }

    /**
     * * Erstellen des Statements
     */
    public void setStatements() throws SQLException {
        if (this.stmt == null) {
            stmt = con.createStatement();
        }
    }

    /**
     * * Schliessen des Statement
     */
    public void fkt_Statement_close() {
        try {
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * * Lesen aus der Datenbank
     *
     * @param value_arr angaben der zu zuregebenden Werten
     * @param database  angabe der Tabelle
     * @return zurückgabe der gefunden Werte
     * @throws SQLException Fehler wenn flasche Werte übergeben werden
     */
    public ResultSet fkt_Lesen(Schulli[] value_arr, Database database) throws SQLException {
        String select = getString(value_arr, ", ");
        String query = String.format("Select %s from %s", select, database);
        rs = stmt.executeQuery(query);
        return rs;
    }

    public ResultSet fkt_Lesen(Schulli[] value_arr, Database database, String where) throws SQLException {
        String select = getString(value_arr, ", ");
        String query = String.format("Select %s from %s where %s", select, database, where);
        System.out.println(query);
        rs = stmt.executeQuery(query);
        return rs;
    }

    public ResultSet fkt_Lesen(Schulli[] value_arr, Database database, String where, Schulli[] order, boolean desc) throws SQLException {
        String select = getString(value_arr, ", ");
        String orderby = getString(order, ", ");
        String query = String.format("Select %s from %s where %s order by %s", select, database, where, orderby);
        if (desc)
            query += " desc";
        rs = stmt.executeQuery(query);
        return rs;
    }

    /**
     * * Aufbereitung des String Arrays zu einem String
     *
     * @param str       der zu bearbeiten String
     * @param delimiter Trennzeichen
     * @return Rückgabe des aufbereiteten String
     */
    private String getString(Schulli[] str, String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (Schulli s : str) {
            sb.append(s).append(delimiter);
        }
        return sb.substring(0, sb.length() - delimiter.length());
    }
}
