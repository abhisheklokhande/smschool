package com.example.smschool;

import android.media.Image;

public class student {

    public String firstname, surname, email, gender, collegeid;


    public student() {

    }

    public student(String firstname, String surname, String email, String gender, String collegeid) {
        this.firstname = firstname;
        this.surname = surname;
        this.email = email;
        this.gender = gender;
        this.collegeid = collegeid;

    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCollegeid() {
        return collegeid;
    }

    public void setCollegeid(String collegeid) {
        this.collegeid = collegeid;
    }
}
