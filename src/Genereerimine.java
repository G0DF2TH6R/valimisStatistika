import java.util.ArrayList;

import static java.lang.Math.random;

public class Genereerimine {
    public static ArrayList<Valija> valijateGenereerimine(int n, ArrayList<Erakond> erakondadeList){
        ArrayList<Valija> valijadList = new ArrayList<Valija>(n);
        for (int i = 0; i <n; i++) {
            Valija uus =new Valija("a", "b", "c");
            int erakondadeIndex = (int)(random()*erakondadeList.size());
            Erakond valitavErakond = erakondadeList.get(erakondadeIndex) ;
            uus.valiErakond(valitavErakond);
        }
        return valijadList;
    }
}
