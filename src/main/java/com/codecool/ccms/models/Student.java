package com.codecool.ccms.models;

public class Student extends User {


    public Student(int id, String firstName, String surname, String email, String password, Role role) {
        super(id, firstName, surname, email, password, role);
    }

}
