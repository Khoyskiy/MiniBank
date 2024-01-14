package com.Data;

import java.sql.Date;

public class Card {
    private int idCard;
    private String PinCode;
    private Date DateEnd;
    private int CVC;
    private double money;
    private long CardNumber;

    public Card(String pinCode, Date dateEnd) {
        PinCode = pinCode;
        DateEnd = dateEnd;
    }
    public Card() {
    }

    @Override
    public String toString() {
        return "Card{" +
                "idCard=" + idCard +
                ", PinCode='" + PinCode + '\'' +
                ", DateEnd='" + DateEnd + '\'' +
                ", CVC=" + CVC +
                ", money=" + money +
                ", CardNumber=" + CardNumber +
                '}';
    }

    public Date getDateEnd() {
        return DateEnd;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }
    public int getIdCard() {return idCard;}

    public String getPinCode() {
        return PinCode;
    }

    public void setPinCode(String pinCode) {
        PinCode = pinCode;
    }

    public void setDateEnd(Date dateEnd) {
        DateEnd = dateEnd;
    }

    public int getCVC() {
        return CVC;
    }
    public void setCVC(int cvc) { CVC= cvc;}

    public double getMoney() {
        return this.money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public long getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(long cardNumber) {
        CardNumber = cardNumber;
    }
}
