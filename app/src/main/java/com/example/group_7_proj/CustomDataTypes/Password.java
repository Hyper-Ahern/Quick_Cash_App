package com.example.group_7_proj.CustomDataTypes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Password {
    private String value;

    public Password(String password){
        this.value = password;
    }

    public boolean isEmpty(){
        boolean empty = true;
        if(!(this.value.isEmpty())) empty = false;
        return empty;
    }

    public boolean isLessThan8Chars(String password){
        boolean valid = false;
<<<<<<< Updated upstream
        if (!(password.length() < 8)){
=======
        if (!(this.value.length() < 8)){
>>>>>>> Stashed changes
            valid = true;
        }
        return valid;
    }

    //Regex Pattern -min of 8 length, max of 40('random' limit); accepts at least 1 capital letter, 1 lowercase letter, at least 1 number, at least 1 symbol
    //Symbols = !@#$%^&*()_+-=.,?;:'"`~{}[]\/
    public boolean isWeak(){
<<<<<<< Updated upstream
        boolean valid = false;
        Pattern passwordPattern = Pattern.compile("[A-Za-z0-9!@#$%^&*()_+-=.,?;:'\"`~{}\\[\\]\\\\/]{8,40}");
=======
        boolean weak = true;
        Pattern passwordPattern = Pattern.compile("[A-Za-z0-9!@#$%^&*()_+-=.,?;:'\"`~{}\\[\\]\\\\]{8,40}");
>>>>>>> Stashed changes
        Matcher toMatch = passwordPattern.matcher(this.value);
        weak = !toMatch.matches();
        return weak;
    }

    String returnValue(){
        return this.value;
    }

}
