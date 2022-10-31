package com.project1.crud;

public class Data {
    String id,title,notes;
    public Data(){}
    public Data(String id,String title,String notes){
        this.id=id;
        this.title=title;
        this.notes=notes;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
