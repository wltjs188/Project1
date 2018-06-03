package com.example.ds.project;

import java.io.Serializable;

public class Rest{
    public String genre;
    public String location;
    public int menuId;
    public String name;
    public String imageUrl;
    public Double latitude;
    public Double longitude;


    public Rest(){}
    public Rest(String genre,String location,int menuId,String name,String imageUrl){
        this.genre=genre;
        this.location=location;
        this.menuId=menuId;
        this.name=name;
        this.imageUrl=imageUrl;
    }

    public String getName() {
        return name;
    }
    public String getLocation(){
        return location;
    }
}
