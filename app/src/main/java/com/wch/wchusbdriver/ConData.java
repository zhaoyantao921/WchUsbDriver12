package com.wch.wchusbdriver;

/**
 * 封装用户信息
 */
public class ConData {

    private int  id;
    private String content_date;
    private String content;

    public ConData(String content_date, String content) {
        this.content_date=content_date;
        this.content=content;
    }

    public ConData() {

    }

    public String getContent() {
        return content;
    }

    public String getDATE() {
        return content_date;
    }

    public int getId() {
        return id;
    }

    public void setDATE(String content_date) {
        this.content_date = content_date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
