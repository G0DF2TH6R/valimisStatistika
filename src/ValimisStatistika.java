import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Thread.sleep;


public class ValimisStatistika {
    public ValimisStatistika() {
    }

    private static boolean idKontroll(String isikuKood) {


        if (!(isikuKood.length() == 11)) return true;

        try {
            Long d = Long.parseLong(isikuKood);
        } catch (NumberFormatException nfe) {
            return true;
        }


        String[] osad = {isikuKood.substring(0, 1), isikuKood.substring(1, 3), isikuKood.substring(3, 5), isikuKood.substring(5, 7)};

        if (!(3<Integer.parseInt(osad[0]) && Integer.parseInt(osad[0])<= 6))return true;
        if (!(0<Integer.parseInt(osad[0]) && Integer.parseInt(osad[0])<= 12))return true;
        if (!(0<Integer.parseInt(osad[0]) && Integer.parseInt(osad[0])<= 31))return true;

        return false;
    }

    public static void main(String[] args) {
        Valija x = new Valija("Mati", "Torm", "50202100267");
        Erakond keskerakond = new Erakond("Eesti Keskerakond");
        Erakond sotsiaaldemokraadid = new Erakond("Sotsiaaldemokraatlik Erakond");
        Erakond isamaa = new Erakond("ISAMAA Erakond");
        Erakond rohelised = new Erakond("Erakond Eestimaa Rohelised");
        Erakond parempoolsed = new Erakond("Erakond Parempoolsed");
        Erakond ekre = new Erakond("Eesti Konservatiivne Rahvaerakond");
        Erakond eesti200 = new Erakond("Erakond Eesti 200");
        Erakond reformierakond = new Erakond("Eesti Reformierakond");

        ArrayList<Erakond> erakonnad = new ArrayList();

        erakonnad.add(sotsiaaldemokraadid);
        erakonnad.add(reformierakond);
        erakonnad.add(isamaa);
        erakonnad.add(rohelised);
        erakonnad.add(keskerakond);
        erakonnad.add(ekre);
        erakonnad.add(parempoolsed);
        erakonnad.add(eesti200);


        Genereerimine.valijateGenereerimine(101, erakonnad);
        //  System.out.println(Statistika.valijateArv(erakonnad));
        ArrayList<Erakond> test = new ArrayList<>();
        //Statistika.riigikogu(test, 101);


        Scanner sc = new Scanner(System.in);

        System.out.println("Sisesta oma eesnimi: ");
        String eesnimi = sc.nextLine();
        System.out.println("Sisesta oma perenimi: ");
        String perenimi = sc.nextLine();
        System.out.println("Sisesta oma isikukood: ");
        String isikukood = sc.nextLine();
        while (idKontroll(isikukood)) {
            System.out.println("Sisesta oma korrektne isikukood: ");
            isikukood = sc.nextLine();
        }

        Valija valija = new Valija(eesnimi,perenimi,isikukood);

        while (true){


            System.out.println("Vajuta 1, kui soovid erakonda valida või muuta.");
            System.out.println("Vajuta 2, kui soovid näha kelle poolt hääle andsid.");
            System.out.println("Vajuta 3, kui soovid näha statistikat.");
            //System.out.println("Vajuta 4, kui soovid näha statistikat.");
            System.out.println("Vajuta 4, kui soovid programmi lõpetada.");


            System.out.println("Vali sobiv tegevus: ");
            String tegevus = sc.nextLine();

            if (tegevus == "4"){
                break;
            }

            switch (tegevus){
                case "1":
                    for (int i = 0; i < erakonnad.size() ; i++) {
                        System.out.println("Vajuta "+ i +" , kui soovid valida " + erakonnad.get(i).getNimi());
                    }
                    System.out.println("Vali sobiv erakond: ");
                    int erakondIndex = Integer.parseInt(sc.nextLine());

                    valija.valiErakond(erakonnad.get(erakondIndex));
                    break;
                case "2":
                    if (valija.getValik() == null){
                        System.out.println("Valik on tegemata.");
                        break;
                    }
                    System.out.println(valija);
                    break;
                case "3":
                    System.out.println("Veel tulekul graafiline väljastus!");
                    System.out.println();
                    System.out.println("Riigikogu mandaadid:");
                    test = Statistika.lävend(5, erakonnad);
                    Statistika.riigikogu(test, 101);

                    break;
                /*case "4":
                    System.out.println("Sisesta oma eesnimi: ");
                    eesnimi = sc.nextLine();
                    System.out.println("Sisesta oma perenimi: ");
                    perenimi = sc.nextLine();
                    System.out.println("Sisesta oma isikukood: ");
                    isikukood = sc.nextLine();
                    while (idKontroll(isikukood)) {
                        System.out.println("Sisesta oma korrektne isikukood: ");
                        isikukood = sc.nextLine();
                    }

                    valija = new Valija(eesnimi,perenimi,isikukood);
*/

            }



        }


    }
}