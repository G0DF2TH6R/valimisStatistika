import java.util.ArrayList;

import static java.lang.Math.random;

public class Genereerimine {

    /**
     * Genereerib soovitud arvu valija tüüpi objekte antud erakondadele.
     * @param n soovitud arv valijaid.
     * @param erakondadeList  antud nimekiri erakondi, kuhu genereeritakse valijaid.
     * @return Nimekiri loodud valijatest ning keda nad valisid.
     */
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
