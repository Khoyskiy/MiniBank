module com.minibank.minibank {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.minibank.minibank to javafx.fxml;
    exports com.minibank.minibank;
}