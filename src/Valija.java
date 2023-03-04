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

    @Override
    public String toString() {
        return "Valija{" +
                "valik=" + valik.getNimi() +
                '}';
    }
}
