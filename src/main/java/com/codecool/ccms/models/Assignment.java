package com.codecool.ccms.models;

import com.codecool.ccms.dao.DaoImpl;
import com.codecool.ccms.dao.UserDao;

import java.util.List;

public class Assignment extends DaoImpl<User> implements UserDao {
    private final int id;
    private final String name;
    private final String description;
    private final String grade;
    private final String author;

    public Assignment(int id, String name, String description,String grade,String author) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.grade = grade;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getGrade(){ return grade;}

    public String getAuthor(){ return author;}


    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void add(User user) {

    }
}