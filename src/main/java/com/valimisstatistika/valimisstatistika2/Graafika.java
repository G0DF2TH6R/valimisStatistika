package com.valimisstatistika.valimisstatistika2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
        Text error0 = new Text("");
        TextField eesnimi = new TextField("Eesnimi");
        Text error1 = new Text("");
        TextField perenimi = new TextField("Perekonnanimi");
        Text error2 = new Text("");
        TextField isikukood = new TextField("Isikukood");
        Button nupp = new Button("Logi sisse");
        nupp.setPadding(new Insets(0,20,0,20));
        nupp.setStyle("-fx-background-color: #00adff; -fx-text-fill: white");
        nupp.setCursor(Cursor.HAND);
        vBox.setAlignment(Pos.BASELINE_CENTER);

        nupp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("logimiskatse");
                if (!idKontroll(isikukood.getText())) {
                    error2.setText("");
                    System.out.println("Töötab");
                     // Siit tuleks panna uus scene peale jms.


                } else {

                    error2.setText("*Vigane sisestus!");
                    error2.setFill(Color.RED);
                }
                error0.setText("");
                error1.setText("");
                String mittesobivad = "!@#$%&*()'+,-./:;<=>?[]^_`{|}1234567890";
                for (int i = 0; i < eesnimi.getText().length(); i++) {
                    char kontroll = eesnimi.getText().charAt(i);
                    if (mittesobivad.contains(Character.toString(kontroll))) {
                        error0.setText("*Vigane sisestus!");
                        error0.setFill(Color.RED);
                        break;
                    }
                }

                for (int i = 0; i < perenimi.getText().length(); i++) {
                    char kontroll = perenimi.getText().charAt(i);
                    if (mittesobivad.contains(Character.toString(kontroll))) {
                        error1.setText("*Vigane sisestus!");
                        error1.setFill(Color.RED);
                        break;
                    }
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

        if (!(3<Integer.parseInt(osad[0]) && Integer.parseInt(osad[0])<= 6))return true;
        if (!(0<Integer.parseInt(osad[0]) && Integer.parseInt(osad[0])<= 12))return true;
        if (!(0<Integer.parseInt(osad[0]) && Integer.parseInt(osad[0])<= 31))return true;

        return false;
    }
}
