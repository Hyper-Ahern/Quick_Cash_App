package com.example.group_7_proj.CustomDataTypes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Username {
    private String value;

    public Username(String username){
        this.value = username;
    }

    boolean tooShort(String username){
        boolean valid = false;
        if (!(username.length() < 5)){
            valid = true;
        }
        return valid;
    }

    //Regex Pattern - min of 5 chars length, max of 40('random' limit); alphanumeric only
    boolean matchesFormat(String username){
        boolean valid = false;
        Pattern usernamePattern = Pattern.compile("[A-Za-z0-9]{5,40}");
        Matcher toMatch = usernamePattern.matcher(username);
        valid = toMatch.matches();
        return valid;
    }

    String returnValue(){
        return this.value;
    }
}
