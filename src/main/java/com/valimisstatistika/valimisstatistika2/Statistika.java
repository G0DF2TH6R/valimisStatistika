package com.valimisstatistika.valimisstatistika2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Statistika {

    //Leiab kõik potensiaalsed koalitsioonid antud erakondade põhjal
    public static void koalitsioonid(ArrayList<Erakond> erakonnad) {

    }

    public static Map<String, Integer> riigikogu(Map<String, Integer> tulemused, int kohtadeArv) {
        Map<String, Integer> riigikoguKohad = new HashMap<>();
        try {

            for (String s : tulemused.keySet()) {
                riigikoguKohad.put(s, 0);
            }


            for (int i = 0; i < kohtadeArv; i++) {
                long suurimValijateArv = 0;
                String suurimErakondValijad = "";


                for (String erakond : tulemused.keySet()) {
                    if (tulemused.get(erakond) > suurimValijateArv) {
                        suurimValijateArv = tulemused.get(erakond);
                        suurimErakondValijad = erakond;
                    }
                }


                riigikoguKohad.replace(suurimErakondValijad, riigikoguKohad.get(suurimErakondValijad) + 1);
                tulemused.replace(suurimErakondValijad, (int) Math.round(tulemused.get(suurimErakondValijad) / (riigikoguKohad.get(suurimErakondValijad) + 1.0)));
            }

        } catch (NullPointerException ignored) {
        }
        return riigikoguKohad;
    }


    /**
     * Tagastab nimekirja erakondadest, kes ületasid antud lävendi.
     *
     * @param protsent  antud lävendiprotsent.
     * @param erakonnad nimekiri erakondadest.
     * @return nimekiri erakondades, kes ületasid antud lävendi.
     */
    public static ArrayList<Erakond> lävend(double protsent, ArrayList<Erakond> erakonnad) {
        ArrayList<Erakond> vastus = new ArrayList<>();
        int koguValijateArv = valijateArv(erakonnad);
        for (Erakond erakond : erakonnad) {
            double erakonnaProtsent = (double) erakond.getValijateArv() / koguValijateArv * 100;
            if (erakonnaProtsent >= protsent) {
                vastus.add(erakond);
            }
        }
        return vastus;
    }

    /**
     * Leiab valijate arvu
     *
     * @param erakonnad antud erakondade nimekiri
     * @return valijate arv kõikidest antud erakondadest
     */
    public static int valijateArv(ArrayList<Erakond> erakonnad) {
        int vastus = 0;
        for (Erakond erakond : erakonnad) {
            vastus += erakond.getValijateArv();
        }
        return vastus;
    }


}
