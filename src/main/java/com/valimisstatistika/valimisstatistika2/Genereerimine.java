package com.valimisstatistika.valimisstatistika2;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.random;

public class Genereerimine {


    public static void valijateGenereerimine(int n, ArrayList<Erakond> erakondadeList, String path) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(path));
        Map<String, Integer> toetajad = new HashMap<>();

        for (Erakond erakond : erakondadeList) {
            toetajad.put(erakond.getNimi(), 0);
        }

        for (int i = 0; i < n; i++) {
            int erakondadeIndex = (int) (random() * erakondadeList.size());
            Erakond valitavErakond = erakondadeList.get(erakondadeIndex);
            toetajad.replace(valitavErakond.getNimi(), toetajad.get(valitavErakond.getNimi()) + 1);

        }

        for (String s : toetajad.keySet()) {
            bw.write(s + ";" + toetajad.get(s) + "\n");
        }

        bw.close();
    }


}
