import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EingabeController {
    /**
     * * Attribute
     */
    private static DatenbankZugriff dz;
   
    
    public MenuItem back;
    public Label old_Anteil;
    public Label old_Einzahlung;
    public TextField newEinzahlung;
    private Stage stage;
    private Scene oldScene;

    private Investments investment;

    public Label pnrNameLabel;
    public Label einzahlungLabel;
    public Label gesStandLabel;

    public Label anrNameLabel;
    public Label artLabel;
    public Label strategieLabel;
    public TextField einzahlungField;
    public Label oldEinzahlungLabel;

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
        if (investment.getArt() == Art.Robo)
            strategieLabel.setText(String.valueOf(investment.getStrategie()));
        else
            strategieLabel.setVisible(false);
        oldEinzahlungLabel.setText(String.valueOf(investment.getEinzahlung()));
    }

    public void goBack() {
        stage.setScene(oldScene);
        stage.setMaximized(false);
        stage.centerOnScreen();
        stage.show();
    }

    public void speichern(ActionEvent actionEvent) {
        System.out.println(einzahlungField.getText() + " <- neu || alt -> " + investment.getEinzahlung());
    }
}



