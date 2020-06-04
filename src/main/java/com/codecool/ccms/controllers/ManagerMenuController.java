package com.codecool.ccms.controllers;

import com.codecool.ccms.dao.UserDao;
import com.codecool.ccms.dao.UserDaoImpl;
import com.codecool.ccms.models.Role;
import com.codecool.ccms.models.User;
import com.codecool.ccms.models.factory.UserFactory;
import com.codecool.ccms.view.View;

import java.util.List;

public class ManagerMenuController extends MenuController{

    private static View view = new View();

    public static UserDaoImpl userDao = new UserDaoImpl();
    private static UserFactory userFactory = new UserFactory(userDao);

    public ManagerMenuController(UserDaoImpl userDaoImpl) {

    }

    public void addMentor() {
        addUser(Role.valueOf("MENTOR"));
    }

    public void displayAllUsers() {
        userDao.sendPrintQueryToDB("SELECT * FROM User");
    }


    public void displayUserByRole() {
        String role = view.takeUserInput("Enter user role. 1 - manager," +
                " 2 - mentor, 3 - administration, 4 - student");
        userDao.createPrintQueryUserTable("*", "roleId = " + role);
    }

//    TO BE CONTINUED
//    public void displayAllMentorsSafe() {
//        userDao.createPrintQueryUserTable();
//    }

}

