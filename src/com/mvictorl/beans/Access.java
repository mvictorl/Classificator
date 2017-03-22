package com.mvictorl.beans;

public class Access {
    private String url;
    private int role;
    private int editable;

    public Access() {
        this.editable = 0;
    }

    public Access(String url, int role) {
        this.url = url;
        this.role = role;
        this.editable = 0;
    }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    public int getRole() { return role; }

    public void setRole(int role) { this.role = role; }

    public int getEditable() { return editable; }

    public void setEditable(int editable) { this.editable = editable; }
}
