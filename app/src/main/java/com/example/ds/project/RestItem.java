package com.example.ds.project;

public class RestItem {
    public String genre;
    public String location;
    public int menuId;
    public String name;
    public String imageUrl;
    public double latitude;
    public double longitude;
    public String address;

    public RestItem(){}
    public RestItem(String genre,String location,int menuId,String name,String imageUrl, double latitude, double longitude, String address){
        this.genre=genre;
        this.location=location;
        this.menuId=menuId;
        this.name=name;
        this.imageUrl=imageUrl;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }

    public String getGenre() {
        return genre;
    }
    public String getLocation() {
        return location;
    }
    public int getMenuId() {
        return menuId;
    }
    public String getName() {
        return name;
    }
    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public String getAddress(){
        return address;
    }



}
