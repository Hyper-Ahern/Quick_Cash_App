package com.example.group_7_proj.CustomDataTypes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Username {
    private String value;

    public Username(String username){
        this.value = username;
    }

    public boolean isEmpty(){
        boolean empty = true;
        if(!(this.value.isEmpty())) empty = false;
        return empty;
    }

    public boolean tooShort(){
        boolean valid = true;
        if (!(this.value.length() < 4)){
            valid = false;
        }
        return valid;
    }

    //Regex Pattern - min of 5 chars length, max of 40('random' limit); alphanumeric only
    public boolean matchesFormat(){
        boolean valid = false;
        Pattern usernamePattern = Pattern.compile("[A-Za-z0-9]{4,40}");
        Matcher toMatch = usernamePattern.matcher(this.value);
        valid = toMatch.matches();
        return valid;
    }

    String returnValue(){
        return this.value;
    }
}
