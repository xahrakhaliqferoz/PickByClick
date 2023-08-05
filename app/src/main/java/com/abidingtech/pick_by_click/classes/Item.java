package com.abidingtech.pick_by_click.classes;

public class Item {
    String name;
    String phone;
    int image;
    String time;

    public Item(String name,String phone,int image,String time) {
        this.name = name;
        this.phone = phone;
        this.image = image;
        this.time=time;
}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public int getImage() {
//        return image;
//    }
//
//    public void setImage(int image) {
//        this.image = image;
//    }
//
//    public String getTime() {
//        return time;
//    }
//
//    public void setTime(String time) {
//        this.time = time;
//    }
}

