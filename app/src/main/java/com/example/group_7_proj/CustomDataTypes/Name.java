package com.example.group_7_proj.CustomDataTypes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Name {
    private String value;

    public Name(String name){
        this.value = name;
    }

    public void setName(String name){
        this.value = name;
    }

    public boolean isEmpty(){
        boolean empty = true;
        if(!(this.value.isEmpty())) empty = false;
        return empty;
    }

    public boolean tooShort(){
        boolean tooShort = true;
        if (this.value.length() >= 4){
            tooShort = false;
        }
        return tooShort;
    }

    //Regex Pattern - min of 5 chars length, max of 40('random' limit); alphanumeric only
    public boolean matchesFormat(){
        boolean valid = false;
        Pattern usernamePattern = Pattern.compile("[A-Za-z0-9]{4,40}");
        Matcher toMatch = usernamePattern.matcher(this.value);
        valid = toMatch.matches();
        return valid;
    }

    public String returnValue(){
        return this.value;
    }
}
