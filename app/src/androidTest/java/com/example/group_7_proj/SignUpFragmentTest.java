
package com.example.group_7_proj;

import com.example.group_7_proj.CustomDataTypes.Email;
import com.example.group_7_proj.CustomDataTypes.Password;

import com.example.group_7_proj.CustomDataTypes.User;

import com.example.group_7_proj.CustomDataTypes.Name;

import org.junit.Test;

import static org.junit.Assert.*;

//email, name validation by class

public class SignUpFragmentTest {

    // check name
    @Test
    public void usernameIsEmpty() {
        Name un1 = new Name("");
        assertTrue(un1.isEmpty());
        Name un2 = new Name("abC5");
        assertFalse(un2.isEmpty());
    }

    @Test
    public void userNameTooShort(){
        String falseInput = "aub5";
        boolean falseOutput;
        boolean falseExpected = false;
        Name un1 = new Name(falseInput);
        falseOutput = un1.tooShort();
        assertEquals(falseExpected, falseOutput);

        String trueInput = "ab7";
        boolean trueOutput;
        //boolean trueExpected = true;
        Name un2 = new Name(trueInput);
        trueOutput = un2.tooShort();
        assertEquals(true, trueOutput);
    }

    @Test
    public void userNameMatchesFormat() {
        String falseInput = "aB#$";
        boolean falseOutput;
        boolean falseExpected = false;
        Name un1 = new Name(falseInput);
        falseOutput = un1.matchesFormat();
        assertEquals(falseExpected, falseOutput);

        String trueInput = "abC5";
        boolean trueOutput;
        boolean trueExpected = true;
        Name un2 = new Name(trueInput);
        trueOutput = un2.matchesFormat();
        assertEquals(trueExpected, trueOutput);
    }

    // check email
    @Test
    public void emailIsEmpty() {
        Email em1 = new Email("");
        assertTrue(em1.isEmpty());
        Email em2 = new Email("abc@xyz.ca");
        assertFalse(em2.isEmpty());
    }

    @Test
    public void emailMatchesFormat() {
        String falseInput = "@xyz.ca";
        boolean falseOutput;
        boolean falseExpected = false;
        Email em1 = new Email(falseInput);
        falseOutput = em1.matchesFormat();
        assertEquals(falseExpected, falseOutput);

        String trueInput = "abc@xyz.ca";
        boolean trueOutput;
        boolean trueExpected = true;
        Email em2 = new Email(trueInput);
        trueOutput = em2.matchesFormat();
        assertEquals(trueExpected, trueOutput);
    }

    // check password
    @Test
    public void passwordIsLessThan8Char() {
        Password pw1 = new Password("aB3$");
        assertTrue(pw1.isLessThan8Chars());

        Password pw2 = new Password("abCD23$%");
        assertFalse(pw2.isLessThan8Chars());
    }

    // password must have at least one lower case, upper case letters, numbers and special character
    @Test
    public void passwordIsWeak(){
        Password pw1 = new Password("abCD2345");
        Password pw2 = new Password("abCD@#$%");
        Password pw3 = new Password("abcd23$%");
        Password pw4 = new Password("ABCD23$%");
        Password pw5 = new Password("abCD23$%");

        assertTrue(pw1.isWeak());
        assertTrue(pw2.isWeak());
        assertTrue(pw3.isWeak());
        assertTrue(pw4.isWeak());
        assertFalse(pw5.isWeak());
    }
    @Test
    public void passwordIsEmpty(){
        Password pw1 = new Password("");
        assertTrue(pw1.isEmpty());
        Password pw2 = new Password("pass");
        assertFalse(pw2.isEmpty());
    }


}
