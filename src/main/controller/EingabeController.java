import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import javax.swing.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class EingabeController {
    /**
     * * Attribute
     */
    private static DatenbankZugriff dz;
    private Stage stage;
    private Scene oldScene;
    private Investments investment;

    public MenuItem backMenu;

    public Label pnrNameLabel;
    public Label einzahlungLabel;
    public Label gesStandLabel;

    public Label anrNameLabel;
    public Label artLabel;
    public TextField newEinzahlungField;
    public Label oldEinzahlungLabel;
    public TextField newAnteileField;
    public Label oldAnteilLabel;
    public Label gesamtEinzahlungLabel;
    public Label gesamtAnteilLabel;
    public Label strategieLabel;

    public TextField newAktuelllerStandField;
    public DatePicker datumDatePicker;
    public Label gewinnLabel;
    public Label renditeLabel;
    public Button uebernehmenButton;
    public Button zurueckButton;


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
        einzahlungLabel.setText(String.valueOf(investment.getOldGesamtEinzahlung()));
        gesStandLabel.setText(String.valueOf(investment.getOldGesamtStand()));

        anrNameLabel.setText(investment.getAnrName());
        artLabel.setText(String.valueOf(investment.getArt()));
        if (investment.getArt() == Art.Robo || investment.getArt() == Art.Tagesgeld) {
            oldAnteilLabel.setVisible(false);
            newAnteileField.setVisible(false);
            gesamtAnteilLabel.setVisible(false);
            strategieLabel.setText(String.valueOf(investment.getStrategie()));
        } else {
            strategieLabel.setVisible(false);
            oldAnteilLabel.setText(String.valueOf(investment.getOldAnteile()));
            gesamtAnteilLabel.setText(String.format("Anteile: %.2f", investment.getOldAnteile()));
        }
        oldEinzahlungLabel.setText(String.valueOf(investment.getOldEinzahlung()));
        gesamtEinzahlungLabel.setText(String.format("Einzahlung: %.2f€", investment.getOldEinzahlung()));

        newAktuelllerStandField.setText(String.valueOf(investment.getOldAktuellerStand()));
        datumDatePicker.setPromptText(String.valueOf(investment.getOldDatum()));
    }

    public void goBack(ActionEvent actionEvent) {
        stage.setScene(oldScene);
        stage.setMaximized(false);
        stage.centerOnScreen();
        stage.show();
    }

    public void speichernEinzahlung(ActionEvent actionEvent) {
        if (newEinzahlungField.getText() != "") {
            investment.setNewEinzahlung(investment.getOldEinzahlung() + Double.parseDouble(newEinzahlungField.getText()));
            gesamtEinzahlungLabel.setTextFill(Color.web("#e00909"));
        } else {
            investment.setNewEinzahlung(investment.getOldEinzahlung());
            gesamtEinzahlungLabel.setTextFill(Color.web("0x333333ff"));
        }
        gesamtEinzahlungLabel.setText(String.format("Einzahlung: %.2f€", investment.getNewEinzahlung()));
    }

    public void speichernAnteil(ActionEvent actionEvent) {
        if (newAnteileField.getText() != "") {
            investment.setNewAnteile(investment.getOldAnteile() + Double.parseDouble(newAnteileField.getText()));
            gesamtAnteilLabel.setTextFill(Color.web("#e00909"));
        } else {
            investment.setNewAnteile(investment.getOldAnteile());
            gesamtAnteilLabel.setTextFill(Color.web("0x333333ff"));
        }
        gesamtAnteilLabel.setText(String.format("Anteile: %.2f", investment.getNewAnteile()));
    }

    public void speichernStand(ActionEvent actionEvent) {
        speichernEinzahlung(actionEvent);
        speichernAnteil(actionEvent);
        if (investment.getArt() != Art.Robo && investment.getArt() != Art.Tagesgeld && newAktuelllerStandField.getText() != "") {
            investment.setNewAktuellerStand(investment.getNewAnteile() * Double.parseDouble(newAktuelllerStandField.getText()));
            gewinnLabel.setTextFill(Color.web("#e00909"));
            renditeLabel.setTextFill(Color.web("#e00909"));
        } else if (newAktuelllerStandField.getText() != "") {
            investment.setNewAktuellerStand(Double.parseDouble(newAktuelllerStandField.getText()));
            gewinnLabel.setTextFill(Color.web("#e00909"));
            renditeLabel.setTextFill(Color.web("#e00909"));
        } else {
            investment.setNewAktuellerStand(investment.getOldAktuellerStand());
            gewinnLabel.setTextFill(Color.web("0x333333ff"));
            renditeLabel.setTextFill(Color.web("0x333333ff"));
        }
        investment.setNewGewinn(investment.getNewAktuellerStand() - investment.getNewEinzahlung());
        investment.setNewRendite((investment.getNewAktuellerStand() / investment.getNewEinzahlung() - 1) * 100);

        gewinnLabel.setText(String.format("Gewinn: %.2f€", investment.getNewGewinn()));
        renditeLabel.setText(String.format("Rendite: %.2f%%", investment.getNewRendite()));
    }

    public void datepick(ActionEvent actionEvent) {
        if (datumDatePicker.getValue() == null)
            investment.setNewDatum(null);
        else
            investment.setNewDatum(Date.valueOf(datumDatePicker.getValue().getYear() + "-" + datumDatePicker.getValue().getMonthValue() + "-" + datumDatePicker.getValue().getDayOfMonth()));
    }

    public void uebernahme(ActionEvent actionEvent) {
        speichernStand(actionEvent);
        int input = 1;
        if (investment.getOldEinzahlung() != investment.getNewEinzahlung())
            input = JOptionPane.showConfirmDialog(null, String.format("Neue Einzahlung speichern\nAlter Wert: %.2f€\nNeuer Wert: %.2f€", investment.getOldEinzahlung(), investment.getNewEinzahlung()), "Einzahlung", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (input == 0){
            System.out.println("Speichern");
            input = 1;
            // TODO: 04.02.2021 Speichern
        }
        else
            System.out.println("Nicht Speichern");
        // TODO: 04.02.2021 Speichern

        if (investment.getOldAnteile() != investment.getNewAnteile())
            input = JOptionPane.showConfirmDialog(null, String.format("Neue Anteile speichern\nAlter Wert: %.2f\nNeuer Wert: %.2f", investment.getOldAnteile(), investment.getNewAnteile()), "Anteile", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (input == 0){
            System.out.println("Speichern");
            // TODO: 04.02.2021 Speichern
            input = 1;
        }
        else
            System.out.println("Nicht Speichern");
        // TODO: 04.02.2021 Speichern

        if (investment.getOldAktuellerStand() != investment.getNewAktuellerStand() && investment.getNewDatum() == null)
            input = JOptionPane.showConfirmDialog(null, String.format("Wollen sie den neuen Stand (%.2f€) für heute eintragen", investment.getNewAktuellerStand()), "Aktueller", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        else if (investment.getOldAktuellerStand() != investment.getNewAktuellerStand() && investment.getNewDatum() != null)
            input = JOptionPane.showConfirmDialog(null, String.format("Wollen sie den neuen Stand (%.2f€) den %s eintragen", investment.getNewAktuellerStand(), investment.getNewDatum()), "Aktueller", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (input == 0){
            System.out.println("Speichern");
            input = 1;
            // TODO: 04.02.2021 Speichern
        }
        else
            System.out.println("Nicht Speichern");
        // TODO: 04.02.2021 Speichern
    }
}
