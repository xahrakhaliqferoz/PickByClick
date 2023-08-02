package com.abidingtech.pick_by_click;

public class UserNotifi {

    protected String name;
    private String email;
    private String imageview;
    private String ImageUrl;

    public UserNotifi() {
        // Required empty constructor for Firebase
    }

    public UserNotifi(String name, String email) {
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

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public int getID() {

        return 0;
    }
}
