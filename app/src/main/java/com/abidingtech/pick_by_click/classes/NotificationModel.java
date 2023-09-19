package com.abidingtech.pick_by_click.classes;

public class NotificationModel {
    public static String title;
   public static String body;
  public static long time;

    public NotificationModel() {
    }

    public NotificationModel(String title, String body, long time) {
        this.title = title;
        this.body = body;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}

