import java.util.ArrayList;

public class Statistika {
    public void koalitsioonid(ArrayList<Erakond> erakonnad) {

    }

    public void lävend(int protsent, ArrayList<Erakond> erakonnad) {

    }

    public static int valijateArv(ArrayList<Erakond> erakonnad) {
        int vastus = 0;
        for (Erakond erakond : erakonnad) {
            vastus += erakond.getValijateArv();
        }
        return vastus;
    }

    
}
