package com.minibank.minibank;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import com.Data.Client;
import com.minibank.GmailSender.GmailSender;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControllerPINemail {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField PINemail;
    private Client current;

    @FXML
    void initialize() {
        assert PINemail != null : "fx:id=\"PINemail\" was not injected: check your FXML file 'PINemail.fxml'.";
    }
    public void work (){
        String randomNumber = RandomNumber();
        GmailSender sender = new GmailSender();
        System.out.println(current.getLast_name() +" "+current.getFirst_name());
        sender.send(randomNumber, current.getEmail());
        PINemail.textProperty().addListener((obs, oldValue, newValue) -> {
            if(PINemail.getText().equals(randomNumber)){
                PINemail.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/minibank/minibank/Bank.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                ControllerBank controllerBank = loader.getController();
                controllerBank.setClient(current);
                controllerBank.work();
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }
        });
    }
    private String RandomNumber() {
        int length = 6;
        Random random = new Random();

        StringBuilder randomNumber = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                int digit = random.nextInt(9) + 1;
                randomNumber.append(digit);
            } else {
                int digit = random.nextInt(10);
                randomNumber.append(digit);
            }
        }
        return randomNumber.toString();
    }

    public void setClient(Client client) {
        current = client;
    }
}

