package com.codecool.ccms.models;

public class Student extends User {


    public Student(int id, String name, String surname, String email, String password, Role role) {
        super(id, name, surname, email, password, role);
    }

}
