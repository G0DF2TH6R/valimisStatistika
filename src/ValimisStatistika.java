import java.util.ArrayList;
import java.util.prefs.PreferencesFactory;

public class ValimisStatistika {
    public ValimisStatistika() {
    }

    public static void main(String[] args) {
        Valija x = new Valija("Mati", "Torm", "50202100267");
        Erakond keskerakond = new Erakond("Eesti Keskerakond");
        Erakond sotsiaaldemokraadid = new Erakond("Sotsiaaldemokraatlik Erakond");
        Erakond isamaa = new Erakond("ISAMAA Erakond");
        Erakond rohelised = new Erakond("Erakond Eestimaa Rohelised");
        Erakond parempoolsed = new Erakond("Erakond Parempoolsed");
        Erakond ekre = new Erakond("Eesti Konservatiivne Rahvaerakond");
        Erakond eesti200 = new Erakond("Erakond Eesti 200");
        Erakond reformierakond = new Erakond("Eesti Reformierakond");

        ArrayList<Erakond> erakonnad = new ArrayList();

        erakonnad.add(sotsiaaldemokraadid);
        erakonnad.add(reformierakond);
        erakonnad.add(isamaa);
        erakonnad.add(rohelised);
        erakonnad.add(keskerakond);
        erakonnad.add(ekre);
        erakonnad.add(parempoolsed);
        erakonnad.add(eesti200);



        Genereerimine.valijateGenereerimine(101, erakonnad);
      //  System.out.println(Statistika.valijateArv(erakonnad));
        System.out.println("lavendiga:");
        ArrayList<Erakond> test = Statistika.lävend(5,erakonnad);
        Statistika.riigikogu(test, 101);


    }
}