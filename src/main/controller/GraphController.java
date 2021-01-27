import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GraphController {
    /**
     * * Attribute
     */
    private static DatenbankZugriff dz;
    private Stage stage;
    private Scene oldScene;

    private int pnr = 0;
    private int anr = 0;

    public Button backButton;
    public Button closeButton;
    public LineChart<String, Number> lineChart;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setPnr(int pnr) {
        this.pnr = pnr;
    }

    public void setAnr(int anr) {
        this.anr = anr;
    }

    public void setOldScene(Scene oldScene) {
        this.oldScene = oldScene;
    }

    @FXML
    public void initialize() {
        try {
            dz = DatenbankZugriff.getInstance();
            anr = Investments.getLastInstance().getAnr();
            pnr = Investments.getLastInstance().getPnr();

            String[] value_arr = {"rendite", "gewinn", "datum"};
            String database = "daten";
            String where = "anr = " + anr + " AND pnr = " + pnr + " order by lfdnr asc";

            XYChart.Series seriesRendite = new XYChart.Series();
            XYChart.Series seriesGewinn = new XYChart.Series();
            seriesRendite.setName("Rendite");
            seriesGewinn.setName("Gewinn");
            try {
                ResultSet rs = dz.fkt_Lesen(value_arr, database, where);
                while (rs.next()) {
                    seriesRendite.getData().add(new XYChart.Data(rs.getString(3), rs.getDouble(1) * 100));
                    seriesGewinn.getData().add(new XYChart.Data(rs.getString(3), rs.getDouble(2)));
                }
                lineChart.getData().addAll(seriesGewinn, seriesRendite);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void back(ActionEvent actionEvent) {
        stage.setScene(oldScene);
        stage.setMaximized(false);
        stage.centerOnScreen();
        stage.show();
    }

    public void close(ActionEvent actionEvent) {
    }
}