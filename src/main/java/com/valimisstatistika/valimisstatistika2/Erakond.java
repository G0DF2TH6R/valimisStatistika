package com.valimisstatistika.valimisstatistika2;

import java.util.ArrayList;

public class Erakond {
    private String nimi;
    private ArrayList<Valija> valijad = new ArrayList<>();


    public Erakond(String nimi) {
        this.nimi = nimi;
    }

    public long getValijateArv() {
        return valijad.size();
    }


    public String getNimi() {
        return nimi;
    }

    public void getValijad() {
        for (int i = 0; i < valijad.size(); i++) {
            System.out.println(valijad.get(i).toString());
        }
    }

    public void lisaValija(Valija x) {
        valijad.add(x);
    }

    public void eemaldaValija(Valija x) {
        valijad.remove(x);
    }

    @Override
    public String toString() {
        return "Erakond{" +
                "nimi='" + nimi + '\'' +
                ", valijad=" + valijad.toString() +
                '}';
    }

}
