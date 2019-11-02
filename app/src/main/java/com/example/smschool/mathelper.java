package com.example.smschool;

public class mathelper {
    public String writepost,subandtopic;

    public mathelper() {

    }

    public mathelper(String writepost, String subandtopic){
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
