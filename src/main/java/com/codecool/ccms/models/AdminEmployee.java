package com.codecool.ccms.models;

public class AdminEmployee extends User {

    public AdminEmployee(int id, String firstName, String surname, String email, String password, Role role) {
        super(id, firstName, surname, email, password, role);
    }
}
