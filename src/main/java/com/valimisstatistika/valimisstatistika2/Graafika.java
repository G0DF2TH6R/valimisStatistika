package com.valimisstatistika.valimisstatistika2;

import javafx.application.Application;

import java.io.*;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.collections.FXCollections;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Graafika extends Application {
    private static Valija aktiivneValija;
    private static File f;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {


        Label pealkiri = new Label("Tere tulemast E-valimiste keskkonda!");
        pealkiri.setStyle("-fx-font-size: 24px; -fx-font-weight: bold");
        Label selgitus = new Label("Siin saate hääletada mugavalt ja turvaliselt.\n" +
                "Sisselogimiseks klõpsake allolevale nupule.");


        Button edasiNupp = new Button("Alusta");
        edasiNupp.setOnAction(event -> {
            f = new File("statistika.txt");
            if (!f.exists()) {
                try {
                    Genereerimine.valijateGenereerimine(200, ValimisStatistika.getErakonnad(), f.getPath());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            VBox vBox = new VBox();
            vBox.setSpacing(10);

            Text pealkiri2 = new Text("Tere tulemast meie valimisteemalisse programmi!");
            pealkiri.setStyle("-fx-font-size: 12pt");
            Text tuhjus = new Text("");
            Text tuhjus1 = new Text("");
            Text tuhjus2 = new Text("");
            Text error0 = new Text("Eesnimi");
            TextField eesnimi = new TextField("ass");
            Text error1 = new Text("Perekonnanimi");
            TextField perenimi = new TextField("ass");
            Text error2 = new Text("Isikukood");
            TextField isikukood = new TextField("50111023847");
            Button nupp = new Button("Logi sisse");
            nupp.setPadding(new Insets(0, 20, 0, 20));
            nupp.setStyle("-fx-background-color: #00adff; -fx-text-fill: white");
            nupp.setCursor(Cursor.HAND);
            vBox.setAlignment(Pos.BASELINE_CENTER);

            nupp.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    boolean edukas0 = false;
                    boolean edukas1 = true;
                    boolean edukas2 = true;

                    System.out.println("logimiskatse");
                    if (!idKontroll(isikukood.getText())) {
                        edukas0 = true;
                        error2.setFill(Color.BLACK);
                        error2.setText("Isikukood");
                    } else {

                        error2.setText("*Vigane sisestus!");
                        error2.setFill(Color.RED);
                    }

                    String mittesobivad = "!@#$%&*()'+,-./:;<=>?[]^_`{|}1234567890";
                    if (eesnimi.getText().length() >= 2) {
                        error0.setText("Eesnimi");
                        error0.setFill(Color.BLACK);
                        for (int i = 0; i < eesnimi.getText().length(); i++) {
                            char kontroll = eesnimi.getText().charAt(i);
                            if (mittesobivad.contains(Character.toString(kontroll))) {
                                edukas1 = false;
                                error0.setText("*Vigane sisestus!");
                                error0.setFill(Color.RED);
                                break;
                            }
                        }
                    } else {
                        edukas1 = false;
                        error0.setText("*Vigane sisestus!");
                        error0.setFill(Color.RED);
                    }

                    if (perenimi.getText().length() >= 2) {
                        error1.setText("Perekonnanimi");
                        error1.setFill(Color.BLACK);
                        for (int i = 0; i < perenimi.getText().length(); i++) {
                            char kontroll = perenimi.getText().charAt(i);
                            if (mittesobivad.contains(Character.toString(kontroll)) && perenimi.getText().length() < 2) {
                                edukas2 = false;
                                error1.setText("*Vigane sisestus!");
                                error1.setFill(Color.RED);
                                break;
                            }
                        }
                    } else {
                        edukas2 = false;
                        error1.setText("*Vigane sisestus!");
                        error1.setFill(Color.RED);
                    }

                    if (edukas0 && edukas1 && edukas2) {
                        aktiivneValija = new Valija(eesnimi.getText(),perenimi.getText(),isikukood.getText());

                        primaryStage.setScene(ValikG(primaryStage));
                        primaryStage.setResizable(true);
                    }
                }
            });

            vBox.getChildren().add(pealkiri);
            vBox.getChildren().add(tuhjus);
            vBox.getChildren().add(error0);
            vBox.getChildren().add(eesnimi);
            vBox.getChildren().add(error1);
            vBox.getChildren().add(perenimi);
            vBox.getChildren().add(error2);
            vBox.getChildren().add(isikukood);
            vBox.getChildren().add(tuhjus1);
            vBox.getChildren().add(nupp);
            vBox.getChildren().add(tuhjus2);

            Scene login = new Scene(vBox, 500, 330);

            primaryStage.setTitle("valimised");
            primaryStage.setScene(login);
            primaryStage.setResizable(false);
        });

        // Paigutame elemendid keskmisse kasti
        VBox keskKast = new VBox(10);
        keskKast.setAlignment(Pos.CENTER);
        keskKast.getChildren().addAll(pealkiri, selgitus, edasiNupp);

        // Loome esilehe layouti ja lisame keskmise kasti sinna keskele
        BorderPane esileht = new BorderPane();
        esileht.setCenter(keskKast);
        esileht.setPadding(new Insets(20));

        // Loome stseeni ja näitame seda
        Scene stseen1 = new Scene(esileht, 600, 400, Color.LIGHTBLUE);

        primaryStage.setScene(stseen1);
        primaryStage.show();
    }


    public static boolean idKontroll(String isikuKood) {


        if (!(isikuKood.length() == 11)) return true;

        try {
            Long d = Long.parseLong(isikuKood);
        } catch (NumberFormatException nfe) {
            return true;
        }


        String[] osad = {isikuKood.substring(0, 1), isikuKood.substring(1, 3), isikuKood.substring(3, 5), isikuKood.substring(5, 7)};

        if (!(3 < Integer.parseInt(osad[0]) && Integer.parseInt(osad[0]) <= 6)) return true;
        if (!(0 < Integer.parseInt(osad[0]) && Integer.parseInt(osad[0]) <= 12)) return true;
        if (!(0 < Integer.parseInt(osad[0]) && Integer.parseInt(osad[0]) <= 31)) return true;

        return false;
    }

    public static Scene ValikG(Stage primariStage) {

        VBox juur = new VBox(5);
        juur.setPadding(new Insets(20)); // lisatud vahemik servadest
        juur.setAlignment(Pos.CENTER); // kohanda VBox keskele

        BorderPane bp = new BorderPane();
        bp.setPrefSize(500, 500); // asendatud setMinWidth() ja setMinHeight() meetodid prefSize() meetodiga

        VBox vb = new VBox();
        Text vali = new Text("Vali erakond antud nimekirjast: ");
        vali.setStyle("-fx-font-size: 24px; -fx-font-weight: bold");
        bp.setTop(vali);
        BorderPane.setAlignment(vali, Pos.CENTER); // kohanda Text objekt keskele

        Button lõpetaNupp = new Button("Lõpeta");
        lõpetaNupp.setStyle("-fx-background-color: #00adff; -fx-text-fill: white");


        Button statistikaNupp = new Button("Vaata statistikat");
        statistikaNupp.setStyle("-fx-background-color: #00adff; -fx-text-fill: white");

        statistikaNupp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    primariStage.setScene(graafilisedAndmed(primariStage));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        lõpetaNupp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });

        ToggleGroup tg = new ToggleGroup();

        for (Erakond erakond : ValimisStatistika.getErakonnad()) {
            RadioButton rb = new RadioButton();
            rb.setText(erakond.getNimi());
            rb.setToggleGroup(tg);
            vb.getChildren().add(rb);
        }
        Button kinnita = new Button("Kinnita valik");
        kinnita.setStyle("-fx-background-color: #00adff; -fx-text-fill: white");
        kinnita.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    File valijaHaal = new File("valija.txt");
                    BufferedWriter bwr = new BufferedWriter(new FileWriter(valijaHaal));
                    for (Erakond erakond : ValimisStatistika.getErakonnad()) {
                        if(erakond.getNimi().equals(tg.getSelectedToggle().toString().substring(tg.getSelectedToggle().toString().indexOf("'")+1,tg.getSelectedToggle().toString().lastIndexOf("'")))){
                            aktiivneValija.valiErakond(erakond);
                            bwr.write(aktiivneValija.getValik().getNimi());
                        }
                    }
                    bwr.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }



                System.out.println(tg.getSelectedToggle().toString());
                System.out.println(aktiivneValija.getValik());
            }
        });

        vb.getChildren().add(kinnita);



        TilePane tp =new TilePane(vb);

        tp.setAlignment(Pos.CENTER);

        bp.setCenter(tp);


        HBox nupud = new HBox(50);
        nupud.getChildren().addAll(lõpetaNupp, statistikaNupp);
        nupud.setAlignment(Pos.CENTER); // kohanda HBox keskele
        nupud.setPadding(new Insets(20, 0, 0, 0)); // lisatud vahemik ülevalt
        bp.setBottom(nupud);

        juur.getChildren().add(bp);

        Scene valimine = new Scene(juur, 500, 500);

        return valimine;
    }

    public static Scene graafilisedAndmed(Stage PrimaryStage) throws IOException {
        List<Erakond> erakonnad = ValimisStatistika.getErakonnad();

        Map<String, Integer> tulemused = new HashMap<>();

        BufferedReader br = new BufferedReader(new FileReader(f));
        String rida = br.readLine();

        for (Erakond erakond : erakonnad) {
            tulemused.put(erakond.getNimi(), 0);
        }

        while (rida != null) {
            for (Erakond erakond : erakonnad) {
                if (rida.trim().equals(erakond.getNimi())) {
                    int ajutine = tulemused.get(erakond.getNimi());
                    tulemused.remove(erakond.getNimi());
                    tulemused.put(erakond.getNimi(), ajutine + 1);
                }
                rida = br.readLine();
            }
        }

        BufferedReader valija = new BufferedReader(new FileReader("valija.txt"));
        String valijaValik = valija.readLine();

        for (Erakond erakond : erakonnad) {
            if (valijaValik.trim().equals(erakond.getNimi())) {
                int ajutine = tulemused.get(erakond.getNimi());
                tulemused.remove(erakond.getNimi());
                tulemused.put(erakond.getNimi(), ajutine + 1);
            }
        }

        String[] parteid = tulemused.keySet().toArray(new String[0]);

        ObservableList<PieChart.Data> piechartData = FXCollections.observableArrayList();


        for (int i = 0; i < tulemused.size(); i++) {
            piechartData.add(new PieChart.Data(parteid[i], tulemused.get(parteid[i])));
        }



        final PieChart chart = new PieChart(piechartData);


        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Erakondade häälesaak");
        xAxis.setLabel("Erakond");
        yAxis.setLabel("Valijate arv");

        for (int i = 0; i < parteid.length; i++) {
            XYChart.Series series = new XYChart.Series();
            series.setName(parteid[i]);
            series.getData().add(new XYChart.Data(parteid[i], tulemused.get(parteid[i])));
            bc.getData().add(series);
        }

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PrimaryStage.setScene(ValikG(PrimaryStage));
            }
        };

        EventHandler<ActionEvent> katkesta = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        };

        Button tagasi = new Button("Tagasi");
        tagasi.setOnAction(event);
        tagasi.setStyle("-fx-background-color: #00adff; -fx-text-fill: white");

        Button lopeta = new Button("Lõpeta");
        lopeta.setOnAction(katkesta);
        lopeta.setStyle("-fx-background-color: #00adff; -fx-text-fill: white");




        TilePane tp = new TilePane();

        tp.setPrefColumns(2);

        tp.getChildren().add(chart);
        tp.getChildren().add(bc);
        tp.getChildren().add(tagasi);
        tp.getChildren().add(lopeta);
        Scene root = new Scene(tp);
        PrimaryStage.setResizable(false);

        return root;
    }
}
