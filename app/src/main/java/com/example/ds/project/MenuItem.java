package com.example.ds.project;

public class MenuItem {
    String menuName;
    String menuPrice;
    int menuId;

    public MenuItem(){}

    public MenuItem(String menuName, String menuPrice,int menuId) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuId=menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(String menuPrice) {
        this.menuPrice = menuPrice;
    }
}
