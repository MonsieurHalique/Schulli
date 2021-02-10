import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Investments {
    /**
     * * Attribute
     */
    private static List<Investments> list = new ArrayList<Investments>();

    private int lfdnr;
    private int pnr;
    private String pnrName;
    private double oldGesamtEinzahlung;
    private double newGesamtEinzahlung;
    private double oldGesamtStand;
    private double newGesamtStand;

    private int anr;
    private String anrName;
    private double oldEinzahlung;
    private double newEinzahlung;
    private Art art;
    private double strategie;
    private double newAnteile;
    private double oldAnteile;
    private boolean boolSparrate;
    private double oldSparrate;
    private double newSparrate;
    private double kosten;
    private double steuern;

    private double oldAktuellerStand;
    private double newAktuellerStand;
    private double oldGewinn;
    private double newGewinn;
    private double oldRendite;
    private double newRendite;
    private Date oldDatum;
    private Date newDatum;

    /**
     * * Constructor
     */
    private Investments(int anr, int pnr) {
        this.anr = anr;
        this.pnr = pnr;
        list.add(this);
    }



    /**
     * * Getter
     */
    public int getLfdnr() {
        return lfdnr;
    }

    public int getPnr() {
        return pnr;
    }

    public String getPnrName() {
        return pnrName;
    }

    public double getOldGesamtEinzahlung() {
        return oldGesamtEinzahlung;
    }

    public double getNewGesamtEinzahlung() {
        return newGesamtEinzahlung;
    }

    public double getOldGesamtStand() {
        return oldGesamtStand;
    }

    public double getNewGesamtStand() {
        return newGesamtStand;
    }

    public int getAnr() {
        return anr;
    }

    public String getAnrName() {
        return anrName;
    }

    public double getOldEinzahlung() {
        return oldEinzahlung;
    }

    public double getNewEinzahlung() {
        return newEinzahlung;
    }

    public Art getArt() {
        return art;
    }

    public double getStrategie() {
        return strategie;
    }

    public double getNewAnteile() {
        return newAnteile;
    }

    public double getOldAnteile() {
        return oldAnteile;
    }

    public boolean isBoolSparrate() {
        return boolSparrate;
    }

    public double getOldSparrate() {
        return oldSparrate;
    }

    public double getNewSparrate() {
        return newSparrate;
    }

    public double getKosten() {
        return kosten;
    }

    public double getSteuern() {
        return steuern;
    }

    public double getOldAktuellerStand() {
        return oldAktuellerStand;
    }

    public double getNewAktuellerStand() {
        return newAktuellerStand;
    }

    public double getOldGewinn() {
        return oldGewinn;
    }

    public double getNewGewinn() {
        return newGewinn;
    }

    public double getOldRendite() {
        return oldRendite;
    }

    public double getNewRendite() {
        return newRendite;
    }

    public Date getOldDatum() {
        return oldDatum;
    }

    public Date getNewDatum() {
        return newDatum;
    }



    /**
     * * Setter
     */
    public void setLfdnr(int lfdnr) {
        this.lfdnr = lfdnr;
    }

    public void setPnr(int pnr) {
        this.pnr = pnr;
    }

    public void setPnrName(String pnrName) {
        this.pnrName = pnrName;
    }

    public void setOldGesamtEinzahlung(double oldGesamtEinzahlung) {
        this.oldGesamtEinzahlung = oldGesamtEinzahlung;
    }

    public void setNewGesamtEinzahlung(double newGesamtEinzahlung) {
        this.newGesamtEinzahlung = newGesamtEinzahlung;
    }

    public void setOldGesamtStand(double oldGesamtStand) {
        this.oldGesamtStand = oldGesamtStand;
    }

    public void setNewGesamtStand(double newGesamtStand) {
        this.newGesamtStand = newGesamtStand;
    }

    public void setAnr(int anr) {
        this.anr = anr;
    }

    public void setAnrName(String anrName) {
        this.anrName = anrName;
    }

    public void setOldEinzahlung(double oldEinzahlung) {
        this.oldEinzahlung = oldEinzahlung;
    }

    public void setNewEinzahlung(double newEinzahlung) {
        this.newEinzahlung = newEinzahlung;
    }

    public void setArt(Art art) {
        this.art = art;
    }

    public void setStrategie(double strategie) {
        this.strategie = strategie;
    }

    public void setNewAnteile(double newAnteile) {
        this.newAnteile = newAnteile;
    }

    public void setOldAnteile(double oldAnteile) {
        this.oldAnteile = oldAnteile;
    }

    public void setBoolSparrate(boolean boolSparrate) {
        this.boolSparrate = boolSparrate;
    }

    public void setOldSparrate(double oldSparrate) {
        this.oldSparrate = oldSparrate;
    }

    public void setNewSparrate(double newSparrate) {
        this.newSparrate = newSparrate;
    }

    public void setKosten(double kosten) {
        this.kosten = kosten;
    }

    public void setSteuern(double steuern) {
        this.steuern = steuern;
    }

    public void setOldAktuellerStand(double oldAktuellerStand) {
        this.oldAktuellerStand = oldAktuellerStand;
    }

    public void setNewAktuellerStand(double newAktuellerStand) {
        this.newAktuellerStand = newAktuellerStand;
    }

    public void setOldGewinn(double oldGewinn) {
        this.oldGewinn = oldGewinn;
    }

    public void setNewGewinn(double newGewinn) {
        this.newGewinn = newGewinn;
    }

    public void setOldRendite(double oldRendite) {
        this.oldRendite = oldRendite;
    }

    public void setNewRendite(double newRendite) {
        this.newRendite = newRendite;
    }

    public void setOldDatum(Date oldDatum) {
        this.oldDatum = oldDatum;
    }

    public void setNewDatum(Date newDatum) {
        this.newDatum = newDatum;
    }

    public void setPortfolio(String name, double einzahlung, double aktuellerStand) {
        this.pnrName = name;
        this.oldGesamtEinzahlung = einzahlung;
        this.oldGesamtStand = aktuellerStand;

    }

    public void setInvestments(int invLFDNR, String anrName, double einzahlung, Art art, double strategie, double anteile, boolean boolSparrate, double sparrate, double kosten, double steuern) {
        this.invLFDNR = invLFDNR;
        this.anrName = anrName;
        this.oldEinzahlung = einzahlung;
        this.art = art;
        this.strategie = strategie;
        this.oldAnteile = anteile;
        this.boolSparrate = boolSparrate;
        this.oldSparrate = sparrate;
        this.kosten = kosten;
        this.steuern = steuern;
    }

    public void setDaten(double aktuellerStand, double gewinn, double rendite, Date datum) {
        this.oldAktuellerStand = aktuellerStand;
        this.oldGewinn = gewinn;
        this.oldRendite = rendite;
        this.oldDatum = datum;
    }

    public static Investments getInstance(int anr, int pnr) {
        Investments tmp;

        if (list.isEmpty()) {
            return new Investments(anr, pnr);
        }

        for (int i = 0; i < list.size(); i++) {
            tmp = list.get(i);
            if (tmp.anr == anr && tmp.pnr == pnr) {
                list.remove(i);
                list.add(tmp);
                return tmp;
            }
        }
        return new Investments(anr, pnr);
    }

    public static Investments getLastInstance() {
        return list.get(list.size() - 1);
    }
}
