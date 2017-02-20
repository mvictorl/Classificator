package com.mvictorl.beans;

public class Filial {
    private int id;
    private String name;
    private String sh_name;

    public Filial() { }

    public Filial(int id, String name, String sh_name) {
        this.id = id;
        this.name = name;
        this.sh_name = sh_name;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getSh_name() { return sh_name; }

    public void setSh_name(String sh_name) { this.sh_name = sh_name; }
}
