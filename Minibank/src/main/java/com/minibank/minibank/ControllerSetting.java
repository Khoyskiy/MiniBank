package com.minibank.minibank;

import java.net.URL;
import java.util.ResourceBundle;

import com.Data.Client;
import com.minibank.DataBaseC.DataBaseHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControllerSetting {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Text_First;

    @FXML
    private TextField Text_Last;

    @FXML
    private TextField Text_Pass;

    @FXML
    private TextField Text_email;

    @FXML
    private Button Setting_Save;

    @FXML
    private TextField CorPass;

    private Client current;

    @FXML
    void initialize() {
        assert Text_First != null : "fx:id=\"Text_First\" was not injected: check your FXML file 'Bank_Setting.fxml'.";
        assert Text_Last != null : "fx:id=\"Text_Last\" was not injected: check your FXML file 'Bank_Setting.fxml'.";
        assert Text_Pass != null : "fx:id=\"Text_Pass\" was not injected: check your FXML file 'Bank_Setting.fxml'.";
        assert Text_email != null : "fx:id=\"Text_email\" was not injected: check your FXML file 'Bank_Setting.fxml'.";
        assert Setting_Save != null : "fx:id=\"Setting_Save\" was not injected: check your FXML file 'Bank_Setting.fxml'.";
        assert CorPass != null : "fx:id=\"CorPass\" was not injected: check your FXML file 'Bank_Setting.fxml'.";
    }
    DataBaseHandler db = new DataBaseHandler();
    public void work(){
        Setting_Save.setOnAction(event -> {
            String PassText = CorPass.getText().trim();
            String FirstText = Text_First.getText().trim();
            String NPassText = Text_Pass.getText().trim();
            String LastText = Text_Last.getText().trim();
            String EmailText = Text_email.getText().trim();
            if(PassText.equals(current.getPassword()))
            {
                if(!FirstText.isEmpty())
                {
                    db.ReNameFirst(FirstText,current);
                }
                if(!LastText.isEmpty())
                {
                    db.ReNameLast(LastText,current);
                }
                if(!NPassText.isEmpty())
                {
                    db.ReNamePass(PassText,current);
                }
                if(!EmailText.isEmpty())
                {
                    db.ReNameEmail(EmailText,current);
                }
            }
            else
                showErrorDialog("Incorrect password", "Error. Password ");
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
}
