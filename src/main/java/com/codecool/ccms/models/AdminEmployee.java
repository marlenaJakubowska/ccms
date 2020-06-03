package com.codecool.ccms.models;

public class AdminEmployee extends User {

    public AdminEmployee(int id, String name, String surname, String email, String password, Role role) {
        super(id, name, surname, email, password, role);
    }
}
