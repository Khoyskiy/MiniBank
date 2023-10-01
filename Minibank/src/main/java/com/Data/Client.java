package com.Data;

import java.sql.Date;

public class Client {
    private int idClient;
    private String First_name;
    private String Last_name;
    private Date BirthDay;
    private String Password;
    private int idCard;
    private String Email;

    public Client(String first_name, String last_name, Date birthDay, String password, String email) {
        First_name = first_name;
        Last_name = last_name;
        BirthDay = birthDay;
        Password = password;
        Email = email;
    }

    public Client() {

    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", First_name='" + First_name + '\'' +
                ", Last_name='" + Last_name + '\'' +
                ", BirthDay='" + BirthDay + '\'' +
                ", Password='" + Password + '\'' +
                ", idCard=" + idCard +
                ", Email='" + Email + '\'' +
                '}';
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getFirst_name() {
        return First_name;
    }

    public void setFirst_name(String first_name) {
        First_name = first_name;
    }

    public String getLast_name() {
        return Last_name;
    }

    public void setLast_name(String last_name) {
        Last_name = last_name;
    }

    public Date getBirthDay() {
        return BirthDay;
    }

    public void setBirthDay(Date birthDay) {
        BirthDay = birthDay;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getIdCard() {
        return idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
