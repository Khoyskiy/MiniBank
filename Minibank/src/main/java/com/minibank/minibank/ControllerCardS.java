package com.minibank.minibank;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.Data.Card;
import com.Data.Client;
import com.minibank.DataBaseC.DataBaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerCardS extends ControllerBank{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Text_card;

    @FXML
    private Button Send;

    @FXML
    private TextField Text_Sum;

    private Client current;

    private Card card;

    @FXML
    void initialize() {
        assert Text_card != null : "fx:id=\"Text_card\" was not injected: check your FXML file 'CardSend.fxml'.";
        assert Send != null : "fx:id=\"Send\" was not injected: check your FXML file 'CardSend.fxml'.";
        assert Text_Sum != null : "fx:id=\"Text_Sum\" was not injected: check your FXML file 'CardSend.fxml'.";

    }
    DataBaseHandler db = new DataBaseHandler();
    public void work(){
        Send.setOnAction(actionEvent -> {
            String Number = Text_card.getText().trim();
            Double Money = Double.parseDouble(Text_Sum.getText().trim());
            card = current.getCard();
            if(card.getMoney() >= Money)
            {
                try {
                    db.SendMoney(Number,card ,Money);
                    Exit();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else showErrorDialog("Not enough money", "Error.Not enough money ");
        });
    }
    public void setClient(Client client) {
        current = client;
    }
    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.show();
    }
    private void Exit()
    {
        Send.getScene().getWindow().hide();
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
}