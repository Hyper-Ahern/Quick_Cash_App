package com.example.group_7_proj.CustomDataTypes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//requires username of email, required @ symbol, cannot be empty
public class Email {
    private String value;

    public Email(String email){
        this.value = email;
    }

    //Regex Pattern - min of 5 chars length, max of 40('random' limit); alphanumeric only
    boolean matchesFormat(String email){
        boolean valid = false;
        Pattern emailPattern = Pattern.compile("\\b[\\w.!#$%&’*+\\/=?^`{|}~-]+@[\\w-]+(?:\\.[\\w-]+)*\\b");
        Matcher toMatch = emailPattern.matcher(email);
        valid = toMatch.matches();
        return valid;
    }

    String returnValue(){
        return this.value;
    }

}
