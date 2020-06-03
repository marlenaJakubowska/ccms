package com.codecool.ccms.dao;

import com.codecool.ccms.models.User;

import java.util.List;

public interface UserDao extends Dao<User> {

    List<User> getAll();
}
