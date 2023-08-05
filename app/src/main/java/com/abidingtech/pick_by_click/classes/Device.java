package com.abidingtech.pick_by_click.classes;


public class Device {
    private static String name;
    private static String id;

    public Device() {
    }
    public Device(String name, String id) {
        this.name = name;
        this.id = id;
    }
    public static String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
