import java.util.ArrayList;
import java.util.List;

public class Investments {
    /**
     * * Attribute
     */
    private static List<Investments> list = new ArrayList<Investments>();
    private int anr;
    private int pnr;

    /**
     * * Constructor
     */
    private Investments(int anr, int pnr) {
        this.anr = anr;
        this.pnr = pnr;
        list.add(this);
    }

    public int getAnr() {
        return anr;
    }

    public int getPnr() {
        return pnr;
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
