package com.codecool.ccms.dao;

import com.codecool.ccms.models.User;

import java.util.List;

public interface UserDao extends Dao<User> {

    void addUserToDB(String[] values);
    List<User> getAll();

}
