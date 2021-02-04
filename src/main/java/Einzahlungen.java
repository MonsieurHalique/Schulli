import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Einzahlungen {

    private  int invlfdnr;
    private double einzahlungen;
    private Date datum;
    private double anteile;

    private Investments investment = Investments.getLastInstance();

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

    public void setEinzahlungen(double einzahlungen) {
        this.einzahlungen = einzahlungen;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public void setAnteile(double anteile) {
        this.anteile = anteile;
    }
}
