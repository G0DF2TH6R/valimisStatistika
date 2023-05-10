package com.valimisstatistika.valimisstatistika2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Statistika {



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


}
