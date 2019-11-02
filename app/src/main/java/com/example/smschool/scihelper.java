package com.example.smschool;

public class scihelper {
    public String writepost,subandtopic;

    public scihelper() {

    }

    public scihelper(String writepost, String subandtopic){
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
