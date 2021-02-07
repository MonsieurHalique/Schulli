import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainController {
    /**
     * * Attribute
     */
    private static DatenbankZugriff dz;
    private Stage stage;

    private Investments investment;
    private int pnr = 0;
    private int anr = 0;
    private int oldpnr = 0;
    private int oldanr = 0;

    public ComboBox<String> portfolioComboBox;
    public ProgressBar progressBar;

    public TextField aktuellerStandField;

    public Label renditeLabel;
    public Label gewinnLabel;

    public ListView<String> investmentList;
    public Button graphButton;

    public Button okButton;
    public Button changeButton;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        try {
            dz = DatenbankZugriff.getInstance();

            Schulli[] value_arr = {Portfolio.name};

            portfolioComboBox.setPromptText("Wähle Anlage!");
            ResultSet rs = dz.fkt_Lesen(value_arr, Database.portfolio);
            while (rs.next()) {
                portfolioComboBox.getItems().add(rs.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void portfolioEnter(ActionEvent actionEvent) {
        investmentList.getItems().clear();

        pnr = portfolioComboBox.getSelectionModel().getSelectedIndex() + 1;
        if (pnr != oldpnr) {
            anr = 0;
            oldpnr = pnr;
        }

        Schulli[] value_arr = {Investment.name};
        String where = Investment.pnr + " = " + pnr;

        try {
            ResultSet rs = dz.fkt_Lesen(value_arr, Database.investments, where);
            while (rs.next()) {
                investmentList.getItems().add(rs.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void viewSelected(ListView.EditEvent<String> stringEditEvent) {
        anr = investmentList.getSelectionModel().getSelectedIndex() + 1;
        Investments.getInstance(anr, pnr);
        setInvestment();
        aktuellerStandField.setText(String.valueOf(Investments.getLastInstance().getOldAktuellerStand()));
        renditeLabel.setText(String.format("Rendite: %.2f%%", Investments.getLastInstance().getOldRendite() * 100));
        gewinnLabel.setText(String.format("Gewinn: %.2f€", Investments.getLastInstance().getOldGewinn()));
    }

    public void openGraph(ActionEvent actionEvent) {
        if (anr == 0 || pnr == 0) {
            JOptionPane.showMessageDialog(null, "Kein Investment gewählt", "Fehler", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("graph.fxml"));
            Parent view = fxmlLoader.load();

            GraphController graphController = fxmlLoader.<GraphController>getController();
            graphController.setStage(stage);
            graphController.setOldScene(stage.getScene());

            stage.setScene(new Scene(view));
            stage.setMaximized(true);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setInvestment() {
        setInvestmentPortfolio();
        setInvestmentInvestments();
        setInvestmentDaten();
        setInvestmentEinzahlung();
    }

    // TODO: 07.02.2021 prüfen was Schulli für scheiße baut 
    private void setInvestmentEinzahlung() {
        Schulli[] value_arr = {Einzahlung.invlfndr, Einzahlung.einzahlungen, Einzahlung.datum, Einzahlung.anteile};
        String where = Einzahlung.invlfndr + " = " + Investment.lfdnr + "and" + Daten.datum + "<=" + Einzahlung.datum;
        try {
            ResultSet rs = dz.fkt_Lesen(value_arr, Database.einzahlung, where);
            rs.next();
            Einzahlungen.getLastInstance().setEinzahlungen(rs.getInt(1), rs.getDouble(2), rs.getDate(3),rs.getDouble(4));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void setInvestmentPortfolio() {
        Schulli[] value_arr = {Portfolio.name, Portfolio.einzahlung, Portfolio.aktueller_Stand};
        String where = Portfolio.pnr + " = " + pnr;
        try {
            ResultSet rs = dz.fkt_Lesen(value_arr, Database.portfolio, where);
            rs.next();
            Investments.getLastInstance().setPortfolio(rs.getString(1), rs.getDouble(2), rs.getDouble(3));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void setInvestmentInvestments() {
        Schulli[] value_arr = {Investment.name, Investment.einzahlung, Investment.art, Investment.strategie, Investment.anteile, Investment.sparrate, Investment.sparrate_wert, Investment.kosten, Investment.steuern};
        String where = Investment.pnr + " = " + pnr + " and " + Investment.anr + " = " + anr;
        try {
            ResultSet rs = dz.fkt_Lesen(value_arr, Database.investments, where);
            rs.next();
            Art tmpArt = Art.valueOf(rs.getString(3));
            Investments.getLastInstance().setInvestments(rs.getString(1), rs.getDouble(2), tmpArt, rs.getDouble(4), rs.getDouble(5), rs.getBoolean(6), rs.getDouble(7), rs.getDouble(8), rs.getDouble(9));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void setInvestmentDaten() {
        Schulli[] value_arr = {Daten.aktueller_stand, Daten.gewinn, Daten.rendite, Daten.datum};
        String where = Daten.anr + " = " + anr + " and " + Daten.pnr + " = " + pnr;
        Schulli[] orderby = {Daten.lfdnr};

        try {
            ResultSet rs = dz.fkt_Lesen(value_arr, Database.daten, where, orderby, true);
            rs.next();
            Investments.getLastInstance().setDaten(rs.getDouble(1), rs.getDouble(2), rs.getDouble(3), rs.getDate(4));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void openEingabe(ActionEvent actionEvent) {
        if (anr == 0 || pnr == 0) {
            JOptionPane.showMessageDialog(null, "Kein Investment gewählt", "Fehler", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("eingabe.fxml"));
            Parent view = fxmlLoader.load();

            EingabeController eingabeController = fxmlLoader.<EingabeController>getController();
            eingabeController.setStage(stage);
            eingabeController.setOldScene(stage.getScene());

            stage.setScene(new Scene(view));
            stage.setMaximized(false);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}