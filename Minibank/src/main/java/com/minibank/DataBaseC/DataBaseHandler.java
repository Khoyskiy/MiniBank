package com.minibank.DataBaseC;
import com.Data.Card;
import com.Data.Client;
import javafx.scene.control.Alert;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Map;

public class DataBaseHandler extends Config {
    Connection dbconnection;

    public Connection getDbconnection() throws
            ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;
        dbconnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbconnection;
    }

    public void singUpUser(Client client, int idCard) {
        String insert = "INSERT INTO client(First_name,Last_name,BirthDay,Password,idCard,Email) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbconnection().prepareStatement(insert);
            preparedStatement.setString(1, client.getFirst_name());
            preparedStatement.setString(2, client.getLast_name());
            preparedStatement.setDate(3, client.getBirthDay());
            preparedStatement.setString(4, client.getPassword());
            preparedStatement.setInt(5, idCard);
            preparedStatement.setString(6, client.getEmail());
            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Input data already exists");

            alert.showAndWait();
            throw new RuntimeException(e);
        }
    }

    public void singUpCard(Card card, Client client) {
        String insert = "INSERT INTO card(Pincode,Date_end) VALUES(?,?)";
        try {
            PreparedStatement preparedStatement = getDbconnection().prepareStatement(insert);
            preparedStatement.setString(1, card.getPinCode());
            preparedStatement.setDate(2, card.getDateEnd());
            preparedStatement.executeUpdate();
            PreparedStatement id = getDbconnection().prepareStatement("SELECT idCard FROM card");
            ResultSet rs = id.executeQuery();

            int idc = 0;
            while (rs.next()) {
                idc = rs.getInt("idCard");
            }
            singUpUser(client, idc);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getUser(String Pass) {
        ResultSet resultSet = null;
        String select = "SELECT * FROM client WHERE Password =?";

        try {
            PreparedStatement preparedStatement = getDbconnection().prepareStatement(select);
            preparedStatement.setString(1, Pass);

            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void ReNameFirst(String FirstName, Client client)
    {
        String update = "UPDATE client SET First_name =? WHERE idClient =?";
        try {
            PreparedStatement preparedStatement = getDbconnection().prepareStatement(update);
            preparedStatement.setString(1, FirstName);
            preparedStatement.setInt(2, client.getIdClient());
            preparedStatement.executeUpdate();
            client.setFirst_name(FirstName);

        } catch (SQLException | ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Input data already exists");

            alert.showAndWait();
            throw new RuntimeException(e);
        }
    }
    public void ReNameLast(String LastName, Client client)
    {
        String update = "UPDATE client SET Last_name =? WHERE idClient =?";
        try {
            PreparedStatement preparedStatement = getDbconnection().prepareStatement(update);
            preparedStatement.setString(1, LastName);
            preparedStatement.setInt(2, client.getIdClient());
            preparedStatement.executeUpdate();
            client.setLast_name(LastName);

        } catch (SQLException | ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Input data already exists");

            alert.showAndWait();
            throw new RuntimeException(e);
        }
    }
    public void ReNamePass(String Password, Client client)
    {
        String update = "UPDATE client SET Password =? WHERE idClient =?";
        try {
            PreparedStatement preparedStatement = getDbconnection().prepareStatement(update);
            preparedStatement.setString(1, Password);
            preparedStatement.setInt(2, client.getIdClient());
            preparedStatement.executeUpdate();
            client.setPassword(Password);

        } catch (SQLException | ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Input data already exists");

            alert.showAndWait();
            throw new RuntimeException(e);
        }
    }
    public void ReNameEmail(String Email, Client client)
    {
        String update = "UPDATE client SET Email =? WHERE idClient =?";
        try {
            PreparedStatement preparedStatement = getDbconnection().prepareStatement(update);
            preparedStatement.setString(1, Email);
            preparedStatement.setInt(2, client.getIdClient());
            preparedStatement.executeUpdate();
            client.setEmail(Email);

        } catch (SQLException | ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Input data already exists");

            alert.showAndWait();
            throw new RuntimeException(e);
        }
    }
    public void DeleteUser(Client client)
    {
        String deleteclient = "DELETE FROM client WHERE idClient = ?";
        String deletecard = "DELETE FROM card WHERE idCard = ?";
        try {
            PreparedStatement preparedStatement = getDbconnection().prepareStatement(deleteclient);
            preparedStatement.setInt(1, client.getIdClient());
            preparedStatement.execute();
            PreparedStatement preparedStatementcard = getDbconnection().prepareStatement(deletecard);
            preparedStatementcard.setInt(1, client.getIdCard());
            preparedStatementcard.execute();

        } catch (SQLException | ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Input data already exists");

            alert.showAndWait();
            throw new RuntimeException(e);
        }
    }
}
