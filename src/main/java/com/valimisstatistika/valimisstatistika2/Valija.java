package com.valimisstatistika.valimisstatistika2;

public class Valija {
    private String eesnimi;
    private String perekonnanimi;
    private String isikukood;
    private Erakond valik;

    public Valija(String eesnimi, String perekonnanimi, String isikukood) {
        this.eesnimi = eesnimi;
        this.perekonnanimi = perekonnanimi;
        this.isikukood = isikukood;
    }

    /**
     * Valija valib erakonna, lisades valija objekti selle erakonna valijate nimekirja.
     * @param valitavErakond erakond tüüpi objekt.
     */
    public void valiErakond(Erakond valitavErakond) {
        if (valik == null) {
            this.valik = valitavErakond;
            valitavErakond.lisaValija(this);
        } else {
            valik.eemaldaValija(this);
            this.valik = valitavErakond;
            valik.lisaValija(this);
        }
    }

    public Erakond getValik() {
        return valik;
    }

    @Override
    public String toString() {
        return "Olete valinud " + valik.getNimi();
    }
}
