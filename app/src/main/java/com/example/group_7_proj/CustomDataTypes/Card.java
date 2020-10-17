package com.example.group_7_proj.CustomDataTypes;

public class Card {

    private String cardNum;
    private String expiryDate;
    private String CVV;
    private String cardHolderName;

    public Card() {
    }

    public Card(String cardNum, String expiryDate, String CVV, String cardHolderName) {
        this.cardNum = cardNum;
        this.expiryDate = expiryDate;
        this.CVV = CVV;
        this.cardHolderName = cardHolderName;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }
}
