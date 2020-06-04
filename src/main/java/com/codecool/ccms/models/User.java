package com.codecool.ccms.models;

public class User {
    private final int id;
    private final String name;
    private final String surname;
    private final String email;
    private final String password;
    private final Role role;

    public User(int id, String name, String surname, String email, String password, Role role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public String[] toStringList() {
        String id = Integer.toString(this.getId());
        String[] stringList = {id, this.getName(), this.getSurname(), this.getEmail(),
                this.getRole().toString()};
        return stringList;
    }

}
