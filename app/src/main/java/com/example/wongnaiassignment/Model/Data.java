package com.example.wongnaiassignment.Model;

import java.io.Serializable;

public class Data implements Serializable {
    private String picture,title,desc;

    public Data() {}

    public Data(String picture, String title, String desc) {
        this.picture = picture;
        this.title = title;
        this.desc = desc;
    }

    public String getPicture() {
        return picture;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Data{" +
                "picture='" + picture + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
