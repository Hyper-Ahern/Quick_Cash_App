
package com.example.group_7_proj;

import com.example.group_7_proj.CustomDataTypes.Email;
import com.example.group_7_proj.CustomDataTypes.Password;
import com.example.group_7_proj.CustomDataTypes.Username;

import org.junit.Test;

import static org.junit.Assert.*;

//email, username validation by class
//junit calls methods - initializing dummy data
//pass = next page displayed? no pass = error message set?

public class SignUpJUnitTest {

    // check username
    @Test
    public void usernameIsEmpty() {
        Username un1 = new Username("");
        assertTrue(un1.isEmpty());
        Username un2 = new Username("abC5");
        assertFalse(un2.isEmpty());
    }

    /*@Test
    public void usernameHasNonAlphanumericChar() {
        Username un1 = new Username("aB#$");
        assertTrue(un1.hasNonAlpChar());
        Username un2 = new Username("abC5");
        assertFalse(un1.hasNonAlpChar());
    }*/

    // check email
    @Test
    public void emailIsEmpty() {
        Email em1 = new Email("");
        assertTrue(em1.isEmpty());
        Email em2 = new Email("abc@xyz.ca");
        assertFalse(em2.isEmpty());
    }

    /*@Test
    public void emailHasNoAtSign() {
        Email em1 = new Email("abcxyz.ca");
        assertTrue(em1.hasNoAtSign());
        Email em2 = new Email("abc@xyz.ca");
        assertFalse(em2.hasNoAtSign());
    }*/

    /*@Test
    public void emailHasNoEmailUsername() {
        Email em1 = new Email("@xyz.ca");
        assertTrue(em1.hasNoEmailUsername());
        Email em2 = new Email("abc@xyz.ca");
        assertFalse(em2.hasNoEmailUsername());
    }*/


/*    // check password
    @Test
    public void passwordIsLessThan8Char() {
        Password pw1 = new Password("aB3$");
        assertTrue(pw1.isLessThan8Char());
        Password pw2 = new Password("abCD23$%");
        assertFalse(pw2.isLessThan8Char());
    }*/

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


}