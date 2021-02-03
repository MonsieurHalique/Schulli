import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EingabeController {
    /**
     * * Attribute
     */
    private static DatenbankZugriff dz;


    public MenuItem back;
    public Label oldAnteil;
    public Label oldEinzahlung;
    public TextField newEinzahlung;
    public TextField newAnteileField;
    public TextField newAktuelllerStand;
    public DatePicker datum;
    public Label gewinn;
    public Label rendite;
    public Button uebernehmen;
    public Button zurueck;
    private Stage stage;
    private Scene oldScene;
    private Investments investment;
    public Label pnrNameLabel;
    public Label einzahlungLabel;
    public Label gesStandLabel;
    public Label anrNameLabel;
    public Label artLabel;
    public Label strategieLabel;
    public Label gesamtAnteil;
    public Label gesamtEinzahlung;
    double aktuellerStand;


    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setOldScene(Scene oldScene) {
        this.oldScene = oldScene;
    }

    @FXML
    public void initialize() {
        investment = Investments.getLastInstance();
        pnrNameLabel.setText(investment.getPnrName());
        einzahlungLabel.setText(String.valueOf(investment.getGesamtEinzahlung()));
        gesStandLabel.setText(String.valueOf(investment.getGesamtStand()));
        anrNameLabel.setText(investment.getAnrName());
        artLabel.setText(String.valueOf(investment.getArt()));
        if (investment.getArt() == Art.Robo || investment.getArt() == Art.Tagesgeld) {
            oldAnteil.setVisible(false);
            newAnteileField.setVisible(false);
            gesamtAnteil.setVisible(false);
            strategieLabel.setText(String.valueOf(investment.getStrategie()));
        } else {
            strategieLabel.setVisible(false);
        }
        oldEinzahlung.setText(String.valueOf(investment.getEinzahlung()));
        oldAnteil.setText(String.valueOf(investment.getAnteile()));
    }

    public void goBack(ActionEvent actionEvent) {
        stage.setScene(oldScene);
        stage.setMaximized(false);
        stage.centerOnScreen();
        stage.show();
    }

    public void speichernEinzahlung(ActionEvent actionEvent) {
        double gesamtEinzahlungValue = Double.parseDouble(oldEinzahlung.getText()) + Double.parseDouble(newEinzahlung.getText());
        gesamtEinzahlung.setText("Einzahlung: " + gesamtEinzahlungValue);
    }

    public void speichernAnteil(ActionEvent actionEvent) {
        double gesamtAnteilValue = Double.parseDouble(oldAnteil.getText()) + Double.parseDouble(newAnteileField.getText());
        gesamtAnteil.setText("Anteile: " + gesamtAnteilValue);
    }

    public void speichernStand(ActionEvent actionEvent) {
        if (investment.getArt() != Art.Robo && investment.getArt() != Art.Tagesgeld) {
            aktuellerStand = Double.parseDouble(oldAnteil.getText()) * Double.parseDouble(newAktuelllerStand.getText());
        } else {
            aktuellerStand = Double.parseDouble(newAktuelllerStand.getText());
        }
        gewinn.setText(String.format("Gewinn: %s€", (aktuellerStand - Double.parseDouble(oldEinzahlung.getText()))));
        rendite.setText(String.format("Rendite: %s", ((aktuellerStand / Double.parseDouble(oldEinzahlung.getText()) - 1 )* 100) + "%"));
    }

    public void datepick(ActionEvent actionEvent) {
        //fromat change to 2020-01-24

    }

    public void uebernahme(ActionEvent actionEvent) {
        //aktuellerStand an SQL
        //Datum übergeben
        //newAnteil übergeben wenn >0 && art != "Robo" && art != "Tagesgeld"

    }

}



