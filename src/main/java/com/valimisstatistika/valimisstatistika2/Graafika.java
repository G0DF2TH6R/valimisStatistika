package com.valimisstatistika.valimisstatistika2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Graafika extends Application {

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
                        primaryStage.setScene(ValikG());
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
            //primaryStage.setScene(Esileht());
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

    public static Scene ValikG() {

        BorderPane bp = new BorderPane();
        bp.setMinWidth(500);
        bp.setMinHeight(500);
        VBox vb = new VBox();
        Text vali = new Text("Vali erakond antud nimekirjast: ");
        bp.setTop(vali);

        ToggleGroup tg = new ToggleGroup();

        for (Erakond erakond : ValimisStatistika.getErakonnad()) {
            RadioButton rb = new RadioButton();
            rb.setText(erakond.getNimi());
            rb.setToggleGroup(tg);
            vb.getChildren().add(rb);
        }


        bp.setCenter(vb);

        Scene valimine = new Scene(bp);

        return valimine;
    }
    public static Scene Esileht(){
        BorderPane bp = new BorderPane();
        Text tere = new Text("Tere tulemast e-valimis keskonda");
        tere.setTextAlignment(TextAlignment.CENTER);
        bp.setTop(tere);
        Text seletus = new Text("On võimalik valida üks endale kõige sobivam erakond. " +
                "Peale valiku tegemist on võimlik vaadata jooksvalt statistikat.");
        seletus.wrappingWidthProperty();
        bp.setCenter(seletus);

        Button edasi = new Button("Edasi");
        edasi.setPadding(new Insets(0, 20, 0, 20));
        edasi.setStyle("-fx-background-color: #00adff; -fx-text-fill: white");
        edasi.setCursor(Cursor.HAND);
        edasi.setAlignment(Pos.BASELINE_CENTER);
        bp.setBottom(edasi);

        edasi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });


        Scene esileht = new Scene(bp, 500,500);
        return esileht;
    }
}
