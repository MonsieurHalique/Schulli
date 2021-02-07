import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Einzahlungen {

    private static List<Einzahlungen> list = new ArrayList<Einzahlungen>();

    private  int invlfdnr;
    private double einzahlungen;
    private Date datum;
    private double anteile;

    private Einzahlungen(int invlfdnr, Date datum, double anteile, double einzahlungen) {
        this.invlfdnr = invlfdnr;
        this.einzahlungen = einzahlungen;
        this.datum = datum;
        this.anteile = anteile;
        list.add(this);
    }

    // TODO: 04.02.2021
    //private Einzahlung einzahlung = Einzahlungen.getLastInstance();



    //Getter

    public int getInvlfdnr() {
        // TODO: 04.02.2021 compare to  investments.LFDNR
        //investment.getLfdnr() != einzahlung.getinvlfdnr();


        return invlfdnr;
    }

    public double getEinzahlungen() {

        return einzahlungen;
    }

    public Date getDatum() {
        // TODO: 04.02.2021

        
        return datum;
    }

    public double getAnteile() {
        
        
        return anteile;
    }


    
    //Setter
    
    public void setInvlfdnr(int invlfdnr) {
        this.invlfdnr = invlfdnr;
    }

    public void setEinzahlungen(int anInt, double aDouble, Date date, double einzahlungen) {
        this.einzahlungen = einzahlungen;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public void setAnteile(double anteile) {
        this.anteile = anteile;
    }


    public static Einzahlungen getInstance(int invlfdnr, Date datum, double anteile, double einzahlungen) {
        Einzahlungen tmp;

        if (list.isEmpty()) {
            return new Einzahlungen(invlfdnr,datum,anteile,einzahlungen);
        }

        for (int i = 0; i < list.size(); i++) {
            tmp = list.get(i);
            if (tmp.invlfdnr == invlfdnr && tmp.datum == datum) {
                list.remove(i);
                list.add(tmp);
                return tmp;
            }
        }
        return new Einzahlungen(invlfdnr,datum,anteile,einzahlungen);
    }

    public static Einzahlungen getLastInstance() {
        return list.get(list.size() - 1);
    }
}
