package com.example.group_7_proj.CustomDataTypes;

public class GeoLocation {
    private Double longitude;
    private Double latitude;

    public GeoLocation(Double longitude, Double latitude){
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Double getLongitude() { return longitude; }

    public Double getLatitude() { return latitude; }

    public void setLongitude(Double longitude){
        this.longitude = longitude;
    }
    public void setLatitude(Double latitude){
        this.latitude = latitude;
    }

    public boolean equals(GeoLocation gL){
        return(this.longitude==gL.getLongitude() && this.latitude==gL.getLatitude());
    }

}
