package com.example.ds.project;

public class RestItem {
    String restName;
    String restGenre;
    int resid;

    public RestItem(String restName, String restGenre, int resid) {
        this.restName = restName;
        this.restGenre = restGenre;
        this.resid = resid;
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getRestGenre() {
        return restGenre;
    }

    public void setRestGenre(String restGenre) {
        this.restGenre = restGenre;
    }
    public int getResid() {
        return resid;
    }

    public void setResid(int resid) {
        this.resid = resid;
    }
}
