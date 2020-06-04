package com.codecool.ccms.controllers;

import com.codecool.ccms.dao.UserDaoImpl;
import com.codecool.ccms.models.User;
import com.codecool.ccms.view.View;

public class StudentMenuController extends MenuController{

    private static View view = new View();
    private final UserDaoImpl userDaoImpl;

    public StudentMenuController(User user, View view) {
        super(user, view);
        userDaoImpl = new UserDaoImpl();
        createMainMenuMap();
    }

    private void createMainMenuMap() {
        //mainMenuMap.put("1", this::submitAssignment); //TO DO
        //mainMenuMap.put("2", this::showGrades); //TO DO
    }
}
