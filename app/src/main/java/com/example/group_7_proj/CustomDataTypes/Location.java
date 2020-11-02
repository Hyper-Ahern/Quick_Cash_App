package com.example.group_7_proj.CustomDataTypes;

public class Location {
    private double longitude;
    private double latitude;

    public Location(double longi, double lati){
        longitude = longi;
        latitude = lati;
    }

    public double getLongitude() { return longitude; }

    public double getLatitude() { return latitude; }

}
