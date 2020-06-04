package com.codecool.ccms.controllers;

import com.codecool.ccms.dao.UserDaoImpl;
import com.codecool.ccms.models.Role;
import com.codecool.ccms.models.User;
import com.codecool.ccms.models.factory.UserFactory;
import com.codecool.ccms.view.View;

public class MentorMenuController {

    private static View view = new View();

    public static UserDaoImpl userDao = new UserDaoImpl();
    private static UserFactory userFactory = new UserFactory(userDao);

    public MentorMenuController(UserDaoImpl userDaoImpl) {

    }

    public void addStudent() {
        System.out.println("Chosen option - add student");
        String name = view.takeUserInput("Enter name: ");
        System.out.println(name);
        String surname = view.takeUserInput("Enter surname: ");
        System.out.println(surname);
        String email = name + surname + "@cc.com";
        System.out.println(email);
        String password = "password";
        System.out.println(password);
        Role role = Role.valueOf("STUDENT");
        System.out.println(role.toString());
        int id = -1; //default value
        User user = userFactory.create(id, name, surname,email,password, role);
        userDao.add(user);
    }
}
