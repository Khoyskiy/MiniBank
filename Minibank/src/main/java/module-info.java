module com.minibank.minibank {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.mail;


    opens com.minibank.minibank to javafx.fxml;
    exports com.minibank.minibank;
}