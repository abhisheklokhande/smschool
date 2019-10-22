package com.example.smschool;

public class feedbackhelper {

    public String title,feedback;

    public feedbackhelper() {

    }

    public feedbackhelper(String title, String feedback){
        this.feedback=feedback;
        this.title=title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
