import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        try {
            dz = DatenbankZugriff.getInstance();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        investment = Investments.getLastInstance();
        pnrNameLabel.setText(investment.getPnrName());
        einzahlungLabel.setText(String.valueOf(investment.getOldGesamtEinzahlung()));
        gesStandLabel.setText(String.valueOf(investment.getOldGesamtStand()));

        anrNameLabel.setText(investment.getAnrName());
        artLabel.setText(String.valueOf(investment.getArt()));

        if (investment.getArt() == Art.Robo || investment.getArt() == Art.Tagesgeld) {
            strategieLabel.setText(String.valueOf(investment.getStrategie()));
        } else {
            oldAnteilLabel.setText(String.valueOf(investment.getOldAnteile()));
            gesamtAnteilLabel.setText(String.format("Anteile: %.2f", investment.getOldAnteile()));
        }
        oldEinzahlungLabel.setText(String.valueOf(investment.getOldEinzahlung()));

        newAktuelllerStandField.setText(String.valueOf(investment.getOldAktuellerStand()));
        datumDatePicker.setPromptText(String.valueOf(investment.getOldDatum()));

        drucken();
    }

    public void goBack(ActionEvent actionEvent) {
        stage.setScene(oldScene);
        stage.setMaximized(false);
        stage.centerOnScreen();
        stage.show();
    }

    public void speichernEinzahlung(ActionEvent actionEvent) {
        if (newEinzahlungField.getText().equals(""))
            investment.setNewEinzahlung(0);
        else if (Double.parseDouble(newEinzahlungField.getText()) == 0)
            investment.setNewEinzahlung(0);
        else
            investment.setNewEinzahlung(investment.getOldEinzahlung() + Double.parseDouble(newEinzahlungField.getText()));
        drucken();
    }

    public void speichernAnteil(ActionEvent actionEvent) {
        if (newAnteileField.getText().equals(""))
            investment.setNewAnteile(0);
        else if (Double.parseDouble(newAnteileField.getText()) == 0)
            investment.setNewAnteile(0);
        else
            investment.setNewAnteile(investment.getOldAnteile() + Double.parseDouble(newAnteileField.getText()));
        drucken();
    }

    public void speichernStand(ActionEvent actionEvent) {
        if (newAktuelllerStandField.getText().equals("")) {
            investment.setNewAktuellerStand(0);
            return;
        }

        speichernEinzahlung(actionEvent);
        speichernAnteil(actionEvent);
        if (investment.getArt() != Art.Robo && investment.getArt() != Art.Tagesgeld)
            investment.setNewAktuellerStand(investment.getNewAnteile() * Double.parseDouble(newAktuelllerStandField.getText()));
        else
            investment.setNewAktuellerStand(Double.parseDouble(newAktuelllerStandField.getText()));
        drucken();
    }

    public void datepick(ActionEvent actionEvent) {
        if (datumDatePicker.getValue() == null) {
            investment.setNewDatum(null);
            investment.setNewDatumEinzahlung(0);
            investment.setNewDatumAnteile(0);
        } else {
            investment.setNewDatum(Date.valueOf(datumDatePicker.getValue().getYear() + "-" + datumDatePicker.getValue().getMonthValue() + "-" + datumDatePicker.getValue().getDayOfMonth()));
            getFittingEinzahlung();
        }
        drucken();
    }

    private void getFittingEinzahlung() {
        Schulli[] value_arr = {Einzahlung.einzahlungen, Einzahlung.anteile};
        String where = Einzahlung.invlfdnr + " = " + investment.getInvLFDNR() + " and " + Einzahlung.datum + " <= '" + investment.getNewDatum() + "'";
        Schulli[] orderBy = {Einzahlung.datum};
        try {
            ResultSet rs = dz.fkt_Lesen(value_arr, Database.einzahlung, where, orderBy, true);
            rs.next();
            investment.setNewDatumEinzahlung(rs.getDouble(1));
            investment.setNewDatumAnteile(rs.getDouble(2));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void drucken() {
        feldpruefung();

        if (investment.getNewAnteile() > 0) {
            gesamtAnteilLabel.setTextFill(Color.web("#e00909"));
            gesamtAnteilLabel.setText(String.format("Anteile: %.2f", investment.getNewAnteile()));
        } else {
            gesamtAnteilLabel.setTextFill(Color.web("0x333333ff"));
            gesamtAnteilLabel.setText(String.format("Anteile: %.2f", investment.getOldAnteile()));
        }

        if (investment.getNewDatumEinzahlung() > 0) {
            gesamtEinzahlungLabel.setTextFill(Color.web("#e00909"));
            gesamtEinzahlungLabel.setText(String.format("Einzahlung: %.2f€", investment.getNewDatumEinzahlung()));
        } else if (investment.getNewEinzahlung() > 0) {
            gesamtEinzahlungLabel.setTextFill(Color.web("#e00909"));
            gesamtEinzahlungLabel.setText(String.format("Einzahlung: %.2f€", investment.getNewEinzahlung()));
        } else {
            gesamtEinzahlungLabel.setTextFill(Color.web("0x333333ff"));
            gesamtEinzahlungLabel.setText(String.format("Einzahlung: %.2f€", investment.getOldEinzahlung()));
        }

        berechnen();

        if (investment.getNewAktuellerStand() > 0) {
            gewinnLabel.setTextFill(Color.web("#e00909"));
            renditeLabel.setTextFill(Color.web("#e00909"));
            gewinnLabel.setText(String.format("Gewinn: %.2f€", investment.getNewGewinn()));
            renditeLabel.setText(String.format("Rendite: %.2f%%", investment.getNewRendite()));
        } else {
            gewinnLabel.setTextFill(Color.web("0x333333ff"));
            renditeLabel.setTextFill(Color.web("0x333333ff"));
            gewinnLabel.setText(String.format("Gewinn: %.2f€", investment.getOldGewinn()));
            renditeLabel.setText(String.format("Rendite: %.2f%%", investment.getOldRendite()));
        }
    }

    public void berechnen() {
        if (investment.getNewAktuellerStand() <= 0) {
            investment.setNewGewinn(0);
            investment.setNewRendite(0);
            return;
        }

        double tmpEinzahlung = 0;
        double tmpAnteile = 1;

        if (investment.getNewDatumEinzahlung() > 0) {
            tmpEinzahlung = investment.getNewDatumEinzahlung();
        } else {
            if (investment.getNewEinzahlung() > 0) {
                tmpEinzahlung = investment.getNewEinzahlung();
            } else {
                tmpEinzahlung = investment.getOldEinzahlung();
            }
        }
        if (investment.getArt() != Art.Robo && investment.getArt() != Art.Tagesgeld) {
            if (investment.getNewDatumAnteile() > 0) {
                tmpAnteile = investment.getNewDatumAnteile();
            } else {
                if (investment.getNewAnteile() > 0) {
                    tmpAnteile = investment.getNewAnteile();
                } else {
                    tmpAnteile = investment.getOldAnteile();
                }
            }
        }
        investment.setNewGewinn(investment.getNewAktuellerStand() * tmpAnteile - tmpEinzahlung);
        investment.setNewRendite(((investment.getNewAktuellerStand() * tmpAnteile) / tmpEinzahlung - 1) * 100);
    }

    public void feldpruefung() {
        if (investment.getArt() == Art.Robo || investment.getArt() == Art.Tagesgeld) {
            oldAnteilLabel.setVisible(false);
            newAnteileField.setVisible(false);
            gesamtAnteilLabel.setVisible(false);
            strategieLabel.setVisible(true);
        } else {
            oldAnteilLabel.setVisible(true);
            newAnteileField.setVisible(true);
            gesamtAnteilLabel.setVisible(true);
            strategieLabel.setVisible(false);
        }

        newEinzahlungField.setDisable(investment.getNewDatumEinzahlung() > 0);
        newAnteileField.setDisable(investment.getNewDatumAnteile() > 0);
    }

    public void uebernahme(ActionEvent actionEvent) {
        speichernStand(actionEvent);
        int input = 1;
        if (investment.getOldEinzahlung() != investment.getNewEinzahlung())
            input = JOptionPane.showConfirmDialog(null, String.format("Neue Einzahlung speichern\nAlter Wert: %.2f€\nNeuer Wert: %.2f€", investment.getOldEinzahlung(), investment.getNewEinzahlung()), "Einzahlung", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (input == 0) {
            System.out.println("Speichern");
            input = 1;
            // TODO: 04.02.2021 Speichern
        } else
            System.out.println("Nicht Speichern");
        // TODO: 04.02.2021 Speichern

        if (investment.getOldAnteile() != investment.getNewAnteile())
            input = JOptionPane.showConfirmDialog(null, String.format("Neue Anteile speichern\nAlter Wert: %.2f\nNeuer Wert: %.2f", investment.getOldAnteile(), investment.getNewAnteile()), "Anteile", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (input == 0) {
            System.out.println("Speichern");
            // TODO: 04.02.2021 Speichern
            input = 1;
        } else
            System.out.println("Nicht Speichern");
        // TODO: 04.02.2021 Speichern

        if (investment.getOldAktuellerStand() != investment.getNewAktuellerStand() && investment.getNewDatum() == null)
            input = JOptionPane.showConfirmDialog(null, String.format("Wollen sie den neuen Stand (%.2f€) für heute eintragen", investment.getNewAktuellerStand()), "Aktueller", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        else if (investment.getOldAktuellerStand() != investment.getNewAktuellerStand() && investment.getNewDatum() != null)
            input = JOptionPane.showConfirmDialog(null, String.format("Wollen sie den neuen Stand (%.2f€) den %s eintragen", investment.getNewAktuellerStand(), investment.getNewDatum()), "Aktueller", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (input == 0) {
            System.out.println("Speichern");
            input = 1;
            // TODO: 04.02.2021 Speichern
        } else
            System.out.println("Nicht Speichern");
        // TODO: 04.02.2021 Speichern
    }
}
