package com.example.group_7_proj;

import org.junit.Test;
import static org.junit.Assert.*;

public class PaymentValidationTest {

    //This test case checks card number
    @Test
    public void checkCardNumber() {
        PaymentValidator v1 = new PaymentValidator("");
        PaymentValidator v2 = new PaymentValidator("1234123412341234");
        PaymentValidator v3 = new PaymentValidator("12341234");

        assertFalse(v1.cardValidate());
        assertTrue(v2.cardValidate());
        assertFalse(v3.cardValidate());
    }

    //This test case checks expiry date
    @Test
    public void checkExpiryDate() {
        PaymentValidator v1 = new PaymentValidator("");
        PaymentValidator v2 = new PaymentValidator("11/22");
        PaymentValidator v3 = new PaymentValidator("1133");

        assertFalse(v1.expiryDateValidate());
        assertTrue(v2.expiryDateValidate());
        assertFalse(v3.expiryDateValidate());
    }

    //This test case checks the CVV
    @Test
    public void checkCVV() {
        PaymentValidator v1 = new PaymentValidator("");
        PaymentValidator v2 = new PaymentValidator("123");
        PaymentValidator v3 = new PaymentValidator("11");

        assertFalse(v1.CVVValidate());
        assertTrue(v2.CVVValidate());
        assertFalse(v3.CVVValidate());
    }

    //This test case checks the card holder name
    @Test
    public void checkCardHolderName() {
        PaymentValidator v1 = new PaymentValidator("");
        PaymentValidator v2 = new PaymentValidator("john doe");

        assertFalse(v1.cardHolderNameValidate());
        assertTrue(v2.cardHolderNameValidate());
    }
}
