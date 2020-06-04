package com.codecool.ccms.controllers;

import com.codecool.ccms.dao.UserDaoImpl;
import com.codecool.ccms.models.Role;
import com.codecool.ccms.models.User;
import com.codecool.ccms.models.factory.UserFactory;
import com.codecool.ccms.view.View;

public class MentorMenuController extends MenuController {

    private static View view = new View();

    public static UserDaoImpl userDao = new UserDaoImpl();
    private static UserFactory userFactory = new UserFactory(userDao);

    public MentorMenuController(UserDaoImpl userDaoImpl) {

    }

    public void addStudent() {
        addUser(Role.valueOf("STUDENT"));
    }

    public void displayAllStudents() {
        userDao.sendPrintQueryToDB("SELECT name, surname, email FROM User WHERE roleID = 4");
    }

}
