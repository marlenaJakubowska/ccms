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
        System.out.println("Chosen option - add mentor");
        String name = view.takeUserInput("Enter name: ");
        System.out.println(name);
        String surname = view.takeUserInput("Enter surname: ");
        System.out.println(surname);
        String email = name + surname + "@cc.com";
        System.out.println(email);
        String password = "password";
        System.out.println(password);
        Role role = Role.valueOf("MENTOR");
        System.out.println(role.toString());
        int id = 1; //get last id from user table
        User user = userFactory.create(id, name, surname,email,password, role);
        System.out.println("test1");
        userDao.add(user);
    }

}

