package com.codecool.ccms.controllers;

import com.codecool.ccms.dao.UserDaoImpl;
import com.codecool.ccms.models.User;
import com.codecool.ccms.view.View;

public class AdminMenuController extends MenuController {
    private static View view = new View();
    private final UserDaoImpl userDaoImpl;

    public AdminMenuController(User user, View view) {
        super(user, view);
        userDaoImpl = new UserDaoImpl();
        createMainMenuMap();
    }

    private void createMainMenuMap() {
        mainMenuMap.put("1", this::displayAllStudents);
    }
}
