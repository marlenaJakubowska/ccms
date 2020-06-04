package com.codecool.ccms.controllers;

import com.codecool.ccms.dao.UserDaoImpl;
import com.codecool.ccms.models.Role;
import com.codecool.ccms.models.User;
import com.codecool.ccms.models.factory.UserFactory;
import com.codecool.ccms.view.View;

public class MentorMenuController extends MenuController {

    private static View view = new View();
    private final UserDaoImpl userDaoImpl;
    private final UserFactory userFactory;

    public MentorMenuController(User user, View view) {
        super(user, view);
        System.out.println("testing mentorMenuController");
        userDaoImpl = new UserDaoImpl();
        userFactory = new UserFactory(userDaoImpl);
    }

    public void addStudent() {
        addUser(Role.valueOf("STUDENT"));
    }

    public void displayAllStudents() {
        userDaoImpl.sendPrintQueryToDB("SELECT name, surname, email FROM User WHERE roleID = 4");
    }

}
