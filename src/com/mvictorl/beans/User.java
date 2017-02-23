package com.mvictorl.beans;

public class User {
    private int id;
    private String name;
    private String password;
    private Role role;
    private Worker worker;

    public User() {
        worker = null;
    }

    public Worker getWorker() { return worker; }

    public void setWorker(Worker worker) { this.worker = worker; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }

    public void setRole(Role role) { this.role = role; }
}