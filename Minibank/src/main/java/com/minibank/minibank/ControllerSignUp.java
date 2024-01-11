package com.minibank.minibank;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.Data.Card;
import com.Data.Client;
import com.minibank.DataBaseC.DataBaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class ControllerSignUp {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Back;

    @FXML
    private DatePicker signUpBirthday;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField signUpEmail;

    @FXML
    private TextField signUpFN;

    @FXML
    private TextField signUpLN;

    @FXML
    private TextField signUpPIN;

    @FXML
    private PasswordField signUppassword_field;

    @FXML
    private Line t;

    @FXML
    void initialize() {
        assert Back != null : "fx:id=\"Back\" was not injected: check your FXML file 'SignUp.fxml'.";
        assert signUpBirthday != null : "fx:id=\"signUpBirthday\" was not injected: check your FXML file 'SignUp.fxml'.";
        assert signUpButton != null : "fx:id=\"signUpButton\" was not injected: check your FXML file 'SignUp.fxml'.";
        assert signUpEmail != null : "fx:id=\"signUpEmail\" was not injected: check your FXML file 'SignUp.fxml'.";
        assert signUpFN != null : "fx:id=\"signUpFN\" was not injected: check your FXML file 'SignUp.fxml'.";
        assert signUpLN != null : "fx:id=\"signUpLN\" was not injected: check your FXML file 'SignUp.fxml'.";
        assert signUpPIN != null : "fx:id=\"signUpPIN\" was not injected: check your FXML file 'SignUp.fxml'.";
        assert signUppassword_field != null : "fx:id=\"signUppassword_field\" was not injected: check your FXML file 'SignUp.fxml'.";
        assert t != null : "fx:id=\"t\" was not injected: check your FXML file 'SignUp.fxml'.";
        DataBaseHandler dbHandler = new DataBaseHandler();

        signUpBirthday.valueProperty().addListener((obs, oldValue, newValue) -> {
                int age = LocalDate.now().getYear() - signUpBirthday.getValue().getYear();
                System.out.println(age);
                if (age<14 || age>150) {
                    signUpBirthday.setValue(null);
                    showErrorDialog("Must be over 14 years old", "Error. Incorrect value", Alert.AlertType.ERROR);
                    System.err.println("Date");
                }
        });

        signUppassword_field.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (event.getCharacter().trim().isEmpty()) {
                event.consume();
            }
        });

        signUpFN.textProperty().addListener((obs, oldValue, newValue) -> {
            boolean isvalidF;
            isvalidF = isNameValid(signUpFN.getText());
            if(!isvalidF){
                showErrorDialog("Incorrect First name value", "Error. Numbers are not possible",Alert.AlertType.ERROR);
                System.err.println("Name");
            }
        });

        signUpEmail.textProperty().addListener((obs, oldValue, newValue) -> {
            boolean isvalidEmail;
            isvalidEmail = isEmailValid(signUpEmail.getText());
            if(isvalidEmail){
                showErrorDialog("Incorrect Email", "Error. Right input   .....@gmail.com",Alert.AlertType.ERROR);
                System.err.println("Name");
            }
        });

        signUpPIN.textProperty().addListener((obs, oldValue, newValue) -> {
            boolean isvalidPIN;
            isvalidPIN = isNameValid(signUpPIN.getText());
            if(isvalidPIN){
                showErrorDialog("Letters are not possible in the PINCODE", "Error. Letters are not possible",Alert.AlertType.ERROR);
                System.err.println("Name");
            }
        });

        signUpLN.textProperty().addListener((obs, oldValue, newValue) -> {
            boolean  isvalidL;
            isvalidL = isNameValid(signUpLN.getText());
            if(!isvalidL){
                showErrorDialog("Incorrect Last name value", "Error. Numbers are not possible",Alert.AlertType.ERROR);
                System.err.println("Name");
            }
        });
        Back.setOnAction(event -> {
            Back.getScene().getWindow().hide();
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

        signUpButton.setOnAction(event -> {
            Date sqlDate = Date.valueOf(LocalDate.now().plusYears(3));
            Date sqlDate1 = Date.valueOf(signUpBirthday.getValue());
            boolean isvalidPin = isPinValid(signUpPIN.getText());
            if(!isvalidPin){
                showErrorDialog("Incorrect PINCODE value", "Error. 4 Numbers PIN",Alert.AlertType.ERROR);
                System.err.println("PIN");
                throw new IllegalArgumentException("Age cannot be negative");
            }
            Card card = new Card(signUpPIN.getText(),sqlDate);
            Client client = new Client(signUpFN.getText(),signUpLN.getText(),sqlDate1,signUppassword_field.getText().trim(), signUpEmail.getText());
            dbHandler.singUpCard(card,client);
            signUpButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/minibank/minibank/hello-view.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }catch (IllegalArgumentException e)
            {
                System.err.println("Pin");
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
    }
    private void showErrorDialog(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.show();
    }
    public boolean isNameValid(String value) {
        String regex = ".*\\d.*";
        boolean containsDigits = value.matches(regex);
        return !containsDigits;
    }
    public boolean isPinValid(String value) {
        String regex = "^\\d{4}$";
        return  value.matches(regex);
    }
    public boolean isEmailValid(String value) {
        String regex = "^(?i)[A-Za-z0-9+_.-]+@gmail\\.com $";
        return value.matches(regex);
    }
}
