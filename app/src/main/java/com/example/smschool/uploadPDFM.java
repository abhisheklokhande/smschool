package com.example.smschool;

public class uploadPDFM {

    public String name;
    public String link;

    public uploadPDFM() {
    }

    public uploadPDFM(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLrl(String link) {
        this.link = link;
    }
}