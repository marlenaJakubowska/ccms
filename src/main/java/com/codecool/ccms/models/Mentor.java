package com.codecool.ccms.models;

public class Mentor extends User {

    public Mentor(int id, String firstName, String surname, String email, String password, Role role) {
        super(id, firstName, surname, email, password, role);
    }
}
