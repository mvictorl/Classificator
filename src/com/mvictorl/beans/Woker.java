package com.mvictorl.beans;

public class Woker {
    private int id;
    private String surname;
    private String name;
    private String patronymic;
    private int parent;
    private Division division;
    private boolean userExist;

    public Woker() {
        parent = 0;
        division = null;
        userExist = false;
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

    public boolean isUserExist() { return userExist; }

    public void setUserExist(boolean userExist) { this.userExist = userExist; }
}
