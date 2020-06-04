package com.codecool.ccms.controllers;

import com.codecool.ccms.dao.UserDaoImpl;
import com.codecool.ccms.models.User;
import com.codecool.ccms.view.View;

import java.util.List;

public class LoginController {

    private View view;
    private MenuController menuController;

    public LoginController() {
        view = new View();
        User user = logIn();
    }

    private User logIn() {
        User loggedUser;
        String email;
        do {
            email = view.takeUserInput("Email: ").toLowerCase();
            String password = view.takeUserInput("Password: ");
            loggedUser = loginTry(email, password);
        } while (loggedUser == null);
        System.out.println("Logged in");
        //loggedAsAdmin = loggedUser instanceof Admin;   //needed later on
        return loggedUser;
    }

    private User loginTry(String email, String password) {
        List<User> users;
        users = getSameUser(email, password);
        return users.isEmpty() ? null : users.get(0);
    }

    private List<User> getSameUser(String email, String password) {
      return new UserDaoImpl().getUsers(
                "SELECT * FROM User WHERE email = '" + email + "' AND password = '"
                        + password + "';");
    }
}
