import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Investments {
    /**
     * * Attribute
     */
    private static List<Investments> list = new ArrayList<Investments>();

    private int pnr;
    private String pnrName;
    private double gesamtEinzahlung;
    private double gesamtStand;

    private int anr;
    private String anrName;
    private double einzahlung;
    private Art art;
    private double strategie;
    private double anteile;
    private boolean boolSparrate;
    private double sparrate;
    private double kosten;
    private double steuern;

    private double aktuellerStand;
    private double gewinn;
    private double rendite;
    private Date datum;

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
    public int getAnr() {
        return anr;
    }

    public int getPnr() {
        return pnr;
    }

    public String getPnrName() {
        return pnrName;
    }

    public double getGesamtEinzahlung() {
        return gesamtEinzahlung;
    }

    public double getGesamtStand() {
        return gesamtStand;
    }

    public String getAnrName() {
        return anrName;
    }

    public double getEinzahlung() {
        return einzahlung;
    }

    public Art getArt() {
        return art;
    }

    public double getStrategie() {
        return strategie;
    }

    public double getAnteile() {
        return anteile;
    }

    public boolean isBoolSparrate() {
        return boolSparrate;
    }

    public double getSparrate() {
        return sparrate;
    }

    public double getKosten() {
        return kosten;
    }

    public double getSteuern() {
        return steuern;
    }

    public double getAktuellerStand() {
        return aktuellerStand;
    }

    public double getGewinn() {
        return gewinn;
    }

    public double getRendite() {
        return rendite;
    }

    public Date getDatum() {
        return datum;
    }

    /**
     * * Setter
     */
    public void setPortfolio(String name, double einzahlung, double aktuellerStand) {
        this.pnrName = name;
        this.gesamtEinzahlung = einzahlung;
        this.gesamtStand = aktuellerStand;

    }

    public void setInvestments(String anrName, double einzahlung, Art art, double strategie, double anteile, boolean boolSparrate, double sparrate, double kosten, double steuern) {
        this.anrName = anrName;
        this.einzahlung = einzahlung;
        this.art = art;
        this.strategie = strategie;
        this.anteile = anteile;
        this.boolSparrate = boolSparrate;
        this.sparrate = sparrate;
        this.kosten = kosten;
        this.steuern = steuern;
    }

    public void setDaten(double aktuellerStand, double gewinn, double rendite, Date datum) {
        this.aktuellerStand = aktuellerStand;
        this.gewinn = gewinn;
        this.rendite = rendite;
        this.datum = datum;
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

    @Override
    public String toString() {
        return "Investments{" +
                "pnr=" + pnr +
                ", pnrName='" + pnrName + '\'' +
                ", gesamtEinzahlung=" + gesamtEinzahlung +
                ", gesamtStand=" + gesamtStand +
                ", anr=" + anr +
                ", anrName='" + anrName + '\'' +
                ", einzahlung=" + einzahlung +
                ", art=" + art +
                ", strategie=" + strategie +
                ", anteile=" + anteile +
                ", boolSparrate=" + boolSparrate +
                ", sparrate=" + sparrate +
                ", kosten=" + kosten +
                ", steuern=" + steuern +
                ", aktuellerStand=" + aktuellerStand +
                ", gewinn=" + gewinn +
                ", rendite=" + rendite +
                ", datum=" + datum +
                '}';
    }
}
