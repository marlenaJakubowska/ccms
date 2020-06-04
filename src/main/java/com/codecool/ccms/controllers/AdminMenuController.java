package com.codecool.ccms.controllers;

import com.codecool.ccms.dao.UserDaoImpl;
import com.codecool.ccms.models.User;
import com.codecool.ccms.view.View;

public class AdminMenuController extends MenuController{
    private static View view = new View();
    private final UserDaoImpl userDaoImpl;

    public AdminMenuController(User user, View view) {
        super(user, view);
        System.out.println("testing mentorMenuController");
        userDaoImpl = new UserDaoImpl();
    }
}
