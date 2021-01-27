import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MainController {
    /**
     * * Attribute
     */
    private static DatenbankZugriff dz;
    private Stage stage;

    private int pnr = 0;
    private int anr = 0;

    public TextField aktuellerStandField;
    public Button okButton;
    public Button changeButton;
    public ComboBox<String> portfolio;
    public ProgressBar progressBar;
    public Label renditeLabel;
    public Label gewinnLabel;
    public ListView<String> investmentList;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        try {
            dz = DatenbankZugriff.getInstance();

            String[] value_arr = {"name"};
            String database = "portfolio";

            portfolio.setPromptText("Wähle Anlage!");
            ResultSet rs = dz.fkt_Lesen(value_arr, database);
            while (rs.next()) {
                portfolio.getItems().add(rs.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void portfolioEnter(ActionEvent actionEvent) {
        investmentList.getItems().clear();

        pnr = portfolio.getSelectionModel().getSelectedIndex() + 1;
        String[] value_arr = {"name"};
        String database = "investments";
        String where = "pnr = " + pnr;

        try {
            ResultSet rs = dz.fkt_Lesen(value_arr, database, where);
            while (rs.next()) {
                investmentList.getItems().add(rs.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void viewtest(ListView.EditEvent<String> stringEditEvent) {
        anr = investmentList.getSelectionModel().getSelectedIndex() + 1;
        String[] value_arr = {"aktueller_Stand", "rendite", "gewinn"};
        String database = "daten";
        String where = "anr = " + anr + " AND pnr = " + pnr + " order by lfdnr desc";

        try {
            ResultSet rs = dz.fkt_Lesen(value_arr, database, where);
            rs.next();
            aktuellerStandField.setText(String.valueOf(rs.getDouble(1)));
            renditeLabel.setText(String.format("Rendite: %s", String.valueOf(rs.getDouble(2) * 100)) + "%");
            gewinnLabel.setText(String.format("Gewinn: %s€", String.valueOf(rs.getDouble(3))));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}