package com.codecool.ccms.controllers;

import com.codecool.ccms.dao.UserDaoImpl;
import com.codecool.ccms.models.Role;
import com.codecool.ccms.models.User;
import com.codecool.ccms.models.factory.UserFactory;
import com.codecool.ccms.view.View;

public class ManagerMenuController extends MenuController{

    private static View view = new View();
    private final UserDaoImpl userDaoImpl;
    private final UserFactory userFactory;

    public ManagerMenuController(User user, View view) {
        super(user,view);
        createMainMenuMap();
        userDaoImpl = new UserDaoImpl();
        userFactory = new UserFactory(userDaoImpl);
    }

    public void addMentor() {
        addUser(Role.valueOf("MENTOR"));
    }

    public void displayAllUsers() {
        userDaoImpl.sendPrintQueryToDB("SELECT * FROM User");
    }

    public void displayUserByRole() {
        String role = view.takeUserInput("Enter user role. 1 - manager," +
                " 2 - mentor, 3 - administration, 4 - student");
        userDaoImpl.createPrintQueryUserTable("*", "roleId = " + role);
    }

    private void createMainMenuMap() {
        mainMenuMap.put("1", this::addMentor);
        mainMenuMap.put("2", this::removeUser);
        //mainMenuMap.put("3", this::editMentor);   // TO DO
        mainMenuMap.put("4", this::displayUserByRole);   // TO DO
        mainMenuMap.put("5", this::displayAllUsers);
    }
}

