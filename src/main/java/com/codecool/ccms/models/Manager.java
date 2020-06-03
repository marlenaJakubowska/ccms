package com.codecool.ccms.models;

public class Manager extends User {

    public Manager(int id, String name, String surname, String email, String password, Role role) {
        super(id, name, surname, email, password, role);
    }
}
