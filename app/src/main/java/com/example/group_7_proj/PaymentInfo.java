package com.example.group_7_proj;

import java.text.DateFormat;

public class PaymentInfo {
    private String cardHolderName;
    private String expirationDate;
    private int cvv;
    private String paypal;

    public PaymentInfo() {
    }

    public PaymentInfo(String cardHolderName, String expirationDate, int cvv, String paypal) {
        this.cardHolderName = cardHolderName;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.paypal = paypal;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getPaypall() {
        return paypal;
    }

    public void setPaypall(String paypal) {
        this.paypal = paypal;
    }
}
