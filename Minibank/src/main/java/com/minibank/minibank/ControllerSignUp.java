package com.minibank.minibank;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class ControllerSignUp {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker signUpBirthday;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField signUpFN;

    @FXML
    private TextField signUpLN;

    @FXML
    private TextField signUpPIN;

    @FXML
    private TextField signUpPas;

    @FXML
    private PasswordField signUppassword_field;

    @FXML
    private Line t;

    @FXML
    void initialize() {
        assert signUpBirthday != null : "fx:id=\"signUpBirthday\" was not injected: check your FXML file 'SignUp.fxml'.";
        assert signUpButton != null : "fx:id=\"signUpButton\" was not injected: check your FXML file 'SignUp.fxml'.";
        assert signUpFN != null : "fx:id=\"signUpFN\" was not injected: check your FXML file 'SignUp.fxml'.";
        assert signUpLN != null : "fx:id=\"signUpLN\" was not injected: check your FXML file 'SignUp.fxml'.";
        assert signUpPIN != null : "fx:id=\"signUpPIN\" was not injected: check your FXML file 'SignUp.fxml'.";
        assert signUpPas != null : "fx:id=\"signUpPas\" was not injected: check your FXML file 'SignUp.fxml'.";
        assert signUppassword_field != null : "fx:id=\"signUppassword_field\" was not injected: check your FXML file 'SignUp.fxml'.";
        assert t != null : "fx:id=\"t\" was not injected: check your FXML file 'SignUp.fxml'.";


        signUpButton.setOnAction(event -> {
            signUpButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/minibank/minibank/hello-view.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }

}
