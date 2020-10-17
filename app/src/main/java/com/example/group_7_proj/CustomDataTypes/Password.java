package com.example.group_7_proj.CustomDataTypes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Password {
    private String value;

    public Password(String password){
        this.value = password;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public  boolean isInvalid(){
        return isLessThan8Chars() || isWeak() || isEmpty(); /*Abdullah*/
    }

    public boolean isEmpty(){
        boolean empty = true;
        if(!(this.value.isEmpty())) empty = false;
        return empty;
    }

    public boolean isLessThan8Chars(){
        boolean tooShort = true;
        if (this.value.length() >= 8){
            tooShort = false;
        }
        return tooShort;
    }

    //Regex Pattern -min of 8 length, max of 40('random' limit); accepts at least 1 capital letter, 1 lowercase letter, at least 1 number, at least 1 symbol
    //Symbols = !@#$%^&*()_+-=.,?;:'"`~{}[]\/
    public boolean isWeak(){
        boolean weak = true;
        Pattern passwordPattern = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*().,?;':]).{8,20})");
        Matcher toMatch = passwordPattern.matcher(this.value);
        weak = !toMatch.matches();
        return weak;
    }

    String returnValue(){
        return this.value;
    }

}
