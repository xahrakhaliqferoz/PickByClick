package com.abidingtech.pick_by_click;

public class User {
    private String name;
    private String email;
    private String profilePictureUrl;

    // Add constructors, getter and setter methods

    // Example constructor and getter/setter for the name attribute
    public User() {
    }

    public User(String name, String email, String profilePictureUrl) {
        this.name = name;
        this.email = email;
        this.profilePictureUrl = profilePictureUrl;
    }

    public User(String userId, String name, String email, String profileImageUrl) {

    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Add getter and setter methods for other attributes
}


