package com.minibank.DataBaseC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseHandler extends Config{
    Connection dbconnection;
    public Connection getDbconnection()throws
            ClassNotFoundException, SQLException{
        String connectionString = "jbcd:mysql//"+ dbHost +":"
                + dbPort + "/" + dbName ;
        dbconnection = DriverManager.getConnection(connectionString, dbUser,dbPass);

        return dbconnection;
    }

}
