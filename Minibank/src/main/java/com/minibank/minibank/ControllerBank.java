package com.minibank.minibank;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.Data.Client;
import com.minibank.DataBaseC.DataBaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerBank {

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Wallet;

    @FXML
    private Button History;

    @FXML
    private Button Exit;

    @FXML
    private Label Name_client;

    @FXML
    private Button Delete;

    @FXML
    private Button Settings;

    @FXML
    private MenuButton MenuB;

    @FXML
    private MenuItem MenuB_card;

    @FXML
    private MenuItem MenuB_rec;
    private Client current;

    @FXML
    void initialize() {
        assert Wallet != null : "fx:id=\"Wallet\" was not injected: check your FXML file 'Bank.fxml'.";
        assert History != null : "fx:id=\"History\" was not injected: check your FXML file 'Bank.fxml'.";
        assert Exit != null : "fx:id=\"Exit\" was not injected: check your FXML file 'Bank.fxml'.";
        assert Name_client != null : "fx:id=\"Name_client\" was not injected: check your FXML file 'Bank.fxml'.";
        assert Delete != null : "fx:id=\"Delete\" was not injected: check your FXML file 'Bank.fxml'.";
        assert Settings != null : "fx:id=\"Settings\" was not injected: check your FXML file 'Bank.fxml'.";
        assert MenuB != null : "fx:id=\"MenuB\" was not injected: check your FXML file 'Bank.fxml'.";
        assert MenuB_card != null : "fx:id=\"MenuB_card\" was not injected: check your FXML file 'Bank.fxml'.";
        assert MenuB_rec != null : "fx:id=\"MenuB_rec\" was not injected: check your FXML file 'Bank.fxml'.";
        DataBaseHandler db = new DataBaseHandler();
        Exit.setOnAction(event -> {
           Exit();
        });
        Delete.setOnAction(event -> {
            db.DeleteUser(current);
            Exit();
        });
    }

    @FXML
    private void openSettings() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/minibank/minibank/SettingAnchor.fxml"));
            AnchorPane settingsPane = loader.load();
            ControllerSetting controller = loader.getController();
            controller.setClient(current);
            controller.work();
            settingsPane.setLayoutX(317.0);
            settingsPane.setLayoutY(96);
            mainAnchorPane.getChildren().add(settingsPane);

            System.out.println("Well Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void work(){
        Name_client.setText(current.getLast_name()+ " "+current.getFirst_name());
    }
    public void setClient(Client client) {
        current = client;
    }
    private void Exit()
    {
        Exit.getScene().getWindow().hide();
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
        stage.show();
    }
}