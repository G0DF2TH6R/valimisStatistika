package com.valimisstatistika.valimisstatistika2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Graafika extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {


        VBox vBox = new VBox();
        vBox.setSpacing(10);

        Text pealkiri = new Text("Tere tulemast meie valimisteemalisse programmi!");
        pealkiri.setStyle("-fx-font-size: 12pt");
        Text tuhjus = new Text("");
        Text tuhjus1 = new Text("");
        Text tuhjus2 = new Text("");
        Text error0 = new Text("Eesnimi");
        TextField eesnimi = new TextField("");
        Text error1 = new Text("Perekonnanimi");
        TextField perenimi = new TextField("");
        Text error2 = new Text("Isikukood");
        TextField isikukood = new TextField("");
        Button nupp = new Button("Logi sisse");
        nupp.setPadding(new Insets(0, 20, 0, 20));
        nupp.setStyle("-fx-background-color: #00adff; -fx-text-fill: white");
        nupp.setCursor(Cursor.HAND);
        vBox.setAlignment(Pos.BASELINE_CENTER);

        nupp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("logimiskatse");
                if (!idKontroll(isikukood.getText())) {
                    error2.setFill(Color.BLACK);
                    error2.setText("Isikukood");
                    System.out.println("Töötab");
                    // Siit tuleks panna uus scene peale jms.


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
                            error0.setText("*Vigane sisestus!");
                            error0.setFill(Color.RED);
                            break;
                        }
                    }
                } else {
                    error0.setText("*Vigane sisestus!");
                    error0.setFill(Color.RED);
                }

                if (perenimi.getText().length() >= 2) {
                    error1.setText("Perekonnanimi");
                    error1.setFill(Color.BLACK);
                    for (int i = 0; i <= perenimi.getText().length(); i++) {
                        char kontroll = perenimi.getText().charAt(i);
                        if (mittesobivad.contains(Character.toString(kontroll)) && perenimi.getText().length() < 2) {
                            error1.setText("*Vigane sisestus!");
                            error1.setFill(Color.RED);
                            break;
                        }
                    }
                }else {
                    error1.setText("*Vigane sisestus!");
                    error1.setFill(Color.RED);
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

        primaryStage.setTitle("Login");
        primaryStage.setScene(login);
        primaryStage.setResizable(false);
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
}
