package com.example.lab2.model;

public class ToDoModel {
   private int id;
   private String title;
   private String content;
   private String date;
   private int  type;

    public ToDoModel(int id, String title, String content, String date, int type) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.type = type;
    }

    public ToDoModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
