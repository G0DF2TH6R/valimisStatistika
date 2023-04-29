package com.valimisstatistika.valimisstatistika2;

import java.io.*;
import java.util.ArrayList;

import static java.lang.Math.random;

public class Genereerimine {

    /**
     * Genereerib soovitud arvu valija tüüpi objekte antud erakondadele.
     *
     * @param n              soovitud arv valijaid.
     * @param erakondadeList antud nimekiri erakondi, kuhu genereeritakse valijaid.
     * @return Nimekiri loodud valijatest ning keda nad valisid.
     */
    public static void valijateGenereerimine(int n, ArrayList<Erakond> erakondadeList, String path) throws IOException {
        ArrayList<Valija> valijadList = new ArrayList<Valija>(n);
        BufferedWriter bw = new BufferedWriter(new FileWriter(path));

        for (int i = 0; i < n; i++) {
            Valija uus = new Valija("a", "b", "c");
            int erakondadeIndex = (int) (random() * erakondadeList.size());
            Erakond valitavErakond = erakondadeList.get(erakondadeIndex);
            uus.valiErakond(valitavErakond);

            bw.write(valitavErakond.getNimi() + "\n");
        }

        bw.close();
    }


}
