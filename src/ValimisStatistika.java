import java.time.chrono.Era;
import java.util.ArrayList;

public class ValimisStatistika {
    public static void main(String[] args) {
        Valija x = new Valija("Mati", "Torm", "50202100267");
        Erakond ref = new Erakond("Reformierakond");
        Erakond sots = new Erakond("Sotsid");
        ArrayList<Erakond> erakonnad = new ArrayList<>();
        erakonnad.add(sots);
        erakonnad.add(ref);
        x.valiErakond(ref);
        ref.getValijad();
        x.valiErakond(sots);
        sots.getValijad();
        System.out.println(Statistika.valijateArv(erakonnad));
    }
}
