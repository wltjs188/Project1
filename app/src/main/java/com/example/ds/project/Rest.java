package com.example.ds.project;

public class Rest {
    public String genre;
    public String location;
    public int menuId;
    public String name;

    public Rest(){}
    public Rest(String genre,String location,int menuId,String name){
        this.genre=genre;
        this.location=location;
        this.menuId=menuId;
        this.name=name;
    }

    public String getName() {
        return name;
    }
    public String getLocation(){
        return location;
    }
}
