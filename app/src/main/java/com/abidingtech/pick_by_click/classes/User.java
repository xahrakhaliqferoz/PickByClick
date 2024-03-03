package com.abidingtech.pick_by_click.classes;

public class User {

    private String name;
    private String email;
    private String imageview;

    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.imageview=imageview;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageview() {
        return imageview;
    }

    public void setImageview(String imageview) {
        this.imageview = imageview;
    }
}