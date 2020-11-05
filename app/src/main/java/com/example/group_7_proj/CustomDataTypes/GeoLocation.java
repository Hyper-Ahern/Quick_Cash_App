package com.example.group_7_proj.CustomDataTypes;

public class GeoLocation {
    private double longitude;
    private double latitude;

    public GeoLocation(double longi, double lati){
        longitude = longi;
        latitude = lati;
    }

    public double getLongitude() { return longitude; }

    public double getLatitude() { return latitude; }

    public void setLongitude(double longi){
        longitude = longi;
    }
    public void setLatitude(double lati){
        latitude = lati;
    }

}
