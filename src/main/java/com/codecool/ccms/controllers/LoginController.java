package com.codecool.ccms.controllers;

import com.codecool.ccms.dao.UserDaoImpl;
import com.codecool.ccms.models.*;
import com.codecool.ccms.view.View;

import java.util.List;

public class LoginController {

    private View view;
    private MenuController menuController;
    private boolean loggedAsManager;
    private boolean loggedAsMentor;
    private boolean loggedAsStudent;
    private UserDaoImpl userDaoImpl;


    public LoginController() {
        view = new View();
        User user = logIn();
    }

    private void setMenuController(User loggedUser) {
        if (loggedUser instanceof Manager) {
            new ManagerMenuController(loggedUser, view);
        } else if(loggedUser instanceof Mentor){
            new MentorMenuController(loggedUser, view);
        } else if(loggedUser instanceof Student) {
            new StudentMenuController(loggedUser, view);
        } else {
            new AdminMenuController(loggedUser, view);
        }

    }

    private User logIn() {
        User loggedUser;
        String email;
        do {
            email = view.takeUserInput("Email: ");
            String password = view.takeUserInput("Password: ");
            loggedUser = loginTry(email, password);
        } while (loggedUser == null);
        System.out.println("Logged in");
        setMenuController(loggedUser);
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
