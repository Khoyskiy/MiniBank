package com.minibank.minibank;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import com.Data.Client;
import com.minibank.DataBaseC.DataBaseHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ControllerEnter {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button autoSingButton;
    @FXML
    private RadioButton Kepp_log;

    @FXML
    private Button logInButton;

    @FXML
    private PasswordField password_field;

    @FXML

    void initialize() {
        assert Kepp_log != null : "fx:id=\"Kepp_log\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert password_field != null : "fx:id=\"password_field\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert logInButton != null : "fx:id=\"logInButton\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert autoSingButton != null : "fx:id=\"autoSingButton\" was not injected: check your FXML file 'hello-view.fxml'.";

        DataBaseHandler db = new DataBaseHandler();
        logInButton.setOnAction( event -> {
            String PassText = password_field.getText().trim();
            if(!PassText.isEmpty()){
                ResultSet resultSet = db.getUser(PassText);
                Client client = new Client();

                int counter = 0;
                while(true)
                {
                    try {
                        if (!resultSet.next()) break;
                        client.setEmail(resultSet.getString("email"));
                        client.setFirst_name(resultSet.getString("First_name"));
                        client.setLast_name(resultSet.getString("Last_name"));
                        client.setIdClient(resultSet.getInt("idClient"));
                        client.setBirthDay(resultSet.getDate("BirthDay"));
                        client.setPassword(resultSet.getString("Password"));
                        counter++;
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                if(counter >= 1 ){
                    if(!Kepp_log.isSelected()) {
                        logInButton.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/com/minibank/minibank/PINemail.fxml"));
                        try {
                            loader.load();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        ControllerPINemail controllerPINemail = loader.getController();
                        controllerPINemail.setClient(client);
                        controllerPINemail.work();
                        Parent root = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();
                    }
                    else
                    {
                        logInButton.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/com/minibank/minibank/Bank.fxml"));
                        try {
                            loader.load();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        ControllerBank controllerBank = loader.getController();
                        controllerBank.setClient(client);
                        controllerBank.work();
                        Parent root = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();
                    }
                }
                else{ showErrorDialog("ERR password", "Error Incorrect password entered");}
            }
            else
                showErrorDialog("Empty password", "Error. Password can't be empty ");
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
            stage.show();
        });

    }
    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.show();
    }
}