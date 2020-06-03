package com.codecool.ccms.models;

public class Manager extends User {

    public Manager(int id, String firstName, String surname, String email, String password, Role role) {
        super(id, firstName, surname, email, password, role);
    }
}
