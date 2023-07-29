package com.abidingtech.pick_by_click;

public class Devices {

    String name,id;

    public Devices() {
    }

    public Devices(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getDeviceName() {
        return name;
    }

    public void setDeviceName(String name) {
        this.name = name;
    }

    public String getDeviceId() {
        return id;
    }

    public void setDeviceId(String id) {
        this.id = id;
    }
}
