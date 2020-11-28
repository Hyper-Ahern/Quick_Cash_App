package com.example.group_7_proj.CustomDataTypes;

public class PaypalIndex {

    private String JID;
    private String UID;


    public PaypalIndex() {
    }

    public PaypalIndex(String JID) {
        this.JID = JID;
    }

    public PaypalIndex(String JID, String UID) {
        this.JID = JID;
        this.UID = UID;

    }

    public String getJID() {
        return JID;
    }

    public void setJID(String JID) {
        this.JID = JID;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }


}

