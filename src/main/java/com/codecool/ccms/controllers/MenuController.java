package com.codecool.ccms.controllers;

import com.codecool.ccms.dao.UserDaoImpl;
import com.codecool.ccms.models.Role;
import com.codecool.ccms.models.User;
import com.codecool.ccms.models.factory.UserFactory;
import com.codecool.ccms.view.View;


public abstract class MenuController {

    private static View view = new View();

    public static UserDaoImpl userDao = new UserDaoImpl();
    private static UserFactory userFactory = new UserFactory(userDao);
    private Role role;

    public void addUser(Role role) {
        System.out.println("Chosen option - add mentor");
        String name = view.takeUserInput("Enter name: ");
        String surname = view.takeUserInput("Enter surname: ");
        String email = name + surname + "@cc.com";
        String password = "password";
        this.role = role;
        //System.out.println(role.toString());
        int id = -1; //default value
        User user = userFactory.create(id, name, surname,email,password, role);
        userDao.add(user);

    }
}
