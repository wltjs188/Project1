package com.example.ds.project;

public class RestItem {
    public String genre;
    public String location;
    public int menuId;
    public String name;

    public RestItem(){}
    public RestItem(String genre,String location,int menuId,String name){
        this.genre=genre;
        this.location=location;
        this.menuId=menuId;
        this.name=name;
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



}
