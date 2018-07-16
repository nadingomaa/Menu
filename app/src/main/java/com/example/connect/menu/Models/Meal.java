package com.example.connect.menu.Models;

public class Meal {
   private String title;
    private int price;
    private int imageResourceId=NO_IMAGE;
    private static final int NO_IMAGE=-1;

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public Meal(String title, int price, int imageResourceId) {
        this.title = title;
        this.price = price;
        this.imageResourceId = imageResourceId;
    }
    public Boolean haveImage(){
        return imageResourceId!=NO_IMAGE;
    }

}
