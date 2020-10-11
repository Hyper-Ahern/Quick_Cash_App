package com.example.group_7_proj;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class PaymentValidator {

    private String cardNum;
    private String expiryDate;
    private String CVV;
    private String cardHolderName;

    public PaymentValidator() {
    }

    public PaymentValidator(String cardNum, String expiryDate, String CVV, String cardHolderName) {
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

    public boolean isCardNumeric() {
        if (cardNum == null) {
            return false;
        }
        try {
            double dbl = Double.parseDouble(cardNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public boolean isCardCorrectLength() {
        if (cardNum.length() == 16) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean cardValidate() {
        boolean numCheck = isCardNumeric();
        boolean cardLengthCheck = isCardCorrectLength();
        if (numCheck && cardLengthCheck) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isDateEmpty() {
        if (expiryDate.length() == 5) {
            return true;
        }
        else {
            return false;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean isDateValid() {
        LocalDate currDate = LocalDate.now();
        LocalDate dateParsed = null;

        if (expiryDate.contains("/")) {
            YearMonth date = YearMonth.parse(expiryDate, DateTimeFormatter.ofPattern("MM/yy"));
            dateParsed = date.atEndOfMonth();
        } else {
            return false;
        }
        return !currDate.isBefore(dateParsed);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean expiryDateValidate() {
        boolean isDateEmpty = isDateEmpty();
        boolean isExpired = isDateValid();
        if (isDateEmpty && isDateValid()) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isCVVNumeric() {
        if (CVV == null) {
            return false;
        }
        try {
            double dbl = Double.parseDouble(CVV);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public boolean isCVVCorrectLength() {
        if (CVV.length() == 3) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean CVVValidate() {
        boolean CVVCheck = isCVVNumeric();
        boolean CVVLengthCheck = isCVVCorrectLength();
        if (CVVCheck && CVVLengthCheck) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean cardHolderNameValidate() {
        if (cardHolderName.contains(" ") && cardHolderName.length() > 3) {
            return true;
        }
        else {
            return false;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean paymentValidate() {
        boolean cardValid = cardValidate();
        boolean dateValid = expiryDateValidate();
        boolean CVVValid = CVVValidate();
        boolean nameValid = cardHolderNameValidate();

        if (cardValid && dateValid && CVVValid && nameValid) {
            return true;
        }
        else {
            return false;
        }
    }

}
