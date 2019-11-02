package com.example.smschool;

public class sochelper {
    public String writepost,subandtopic;

    public sochelper() {

    }

    public sochelper(String writepost, String subandtopic){
        this.subandtopic=writepost;
        this.writepost=subandtopic;
    }

    public String getWritepost() {
        return writepost;
    }

    public void setWritepost(String writepost) {
        this.writepost = writepost;
    }

    public String getSubandtopic() {
        return subandtopic;
    }

    public void setSubandtopic(String subandtopic) {
        this.subandtopic = subandtopic;
    }
}
