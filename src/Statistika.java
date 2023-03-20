import java.util.ArrayList;

public class Statistika {

    //Leiab kõik potensiaalsed koalitsioonid antud erakondade põhjal
    public static void koalitsioonid(ArrayList<Erakond> erakonnad) {

    }

    /**
     * Koostab antud erakondade ja kohtade arvu põhjal parlamendi, kus on erakondadele
     * kohad jaotatud D'Hondt algoritmi põhjal
     * @param erakonnad Parlamenti pääsenud erakondade nimekiri.
     * @param kohtadeArv täisarv, mis määrab erakondadele jaotavad kohad.
     */
    public static void riigikogu(ArrayList<Erakond> erakonnad, int kohtadeArv) {
        int allesKohtadearv = kohtadeArv;
        int erakondadeArv = erakonnad.size();
        int[] erakondadeKohad = new int[erakondadeArv];
        long[] valijateArvud = new long[erakondadeArv];

        for (int i = 0; i < erakondadeArv; i++) {
            valijateArvud[i] = erakonnad.get(i).getValijateArv();
            erakondadeKohad[i] = 0;
        }


        for (int i = 0; i < kohtadeArv; i++) {
            long suurimValijateArv = 0;
            int erakonnaIndeks = 0;
            for (Erakond erakond : erakonnad) {
                if (valijateArvud[erakonnad.indexOf(erakond)] > suurimValijateArv) {
                    suurimValijateArv = valijateArvud[erakonnad.indexOf(erakond)];
                    erakonnaIndeks = erakonnad.indexOf(erakond);
                }
            }
            erakondadeKohad[erakonnaIndeks]++;
            valijateArvud[erakonnaIndeks] = Math.round(erakonnad.get(erakonnaIndeks).getValijateArv() / (erakondadeKohad[erakonnaIndeks] + 1.0));
            allesKohtadearv--;
        }

        for (int i = 0; i < erakondadeKohad.length; i++) {
            System.out.println(erakonnad.get(i).getNimi() + ", kohtade arv: " + erakondadeKohad[i]);
        }
    }


    /**
     * Tagastab nimekirja erakondadest, kes ületasid antud lävendi.
     * @param protsent antud lävendiprotsent.
     * @param erakonnad nimekiri erakondadest.
     * @return nimekiri erakondades, kes ületasid antud lävendi.
     */
    public static ArrayList<Erakond> lävend(double protsent, ArrayList<Erakond> erakonnad) {
        ArrayList<Erakond> vastus = new ArrayList<>();
        int koguValijateArv = valijateArv(erakonnad);
        for (Erakond erakond : erakonnad) {
            double erakonnaProtsent = (double) erakond.getValijateArv() / koguValijateArv;
            if (erakonnaProtsent >= protsent) {
                vastus.add(erakond);
            }
        }
        return vastus;
    }

    /**
     * Leiab valijate arvu
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
