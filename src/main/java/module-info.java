module com.valimisstatistika.valimisstatistika2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.valimisstatistika.valimisstatistika2 to javafx.fxml;
    exports com.valimisstatistika.valimisstatistika2;
}