package com.mvictorl.beans;

public class Worker {
    private int id;
    private String surname;
    private String name;
    private String patronymic;
    private int parent;
    private Division division;
    private int user_id;

    public Worker() {
        parent = 0;
        division = null;
        user_id = -1;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getSurname() { return surname; }

    public void setSurname(String surname) { this.surname = surname; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPatronymic() { return patronymic; }

    public void setPatronymic(String patronymic) { this.patronymic = patronymic; }

    public int getParent() { return parent; }

    public void setParent(int parent) { this.parent = parent; }

    public Division getDivision() { return division; }

    public void setDivision(Division division) { this.division = division; }

    public int getUser_id() { return user_id; }

    public void setUser_id(int user_id) { this.user_id = user_id; }
}