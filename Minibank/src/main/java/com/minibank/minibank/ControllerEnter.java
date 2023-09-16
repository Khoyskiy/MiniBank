package com.minibank.minibank;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class ControllerEnter {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button autoSingButton;

    @FXML
    private Button logInButton;

    @FXML
    private PasswordField password_field;

    @FXML
    void initialize() {
        logInButton.setOnAction( event -> {
            String PassText = password_field.getText().trim();
            if(!PassText.equals("")){
                //loginClient(PassText);
            }
            else
                showErrorDialog("Empty password", "Error. Password can't be empty ");
                System.err.println("Empty Password");
        });


        autoSingButton.setOnAction(event ->{
            autoSingButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/minibank/minibank/SignUp.fxml"));

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
    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

}