package com.codecool.ccms.controllers;

import com.codecool.ccms.dao.UserDaoImpl;
import com.codecool.ccms.models.*;
import com.codecool.ccms.view.View;

import java.util.List;

public class LoginController {

    private View view;
    private MenuController menuController;

    public LoginController() {
        view = new View();
        User user = logIn();
        setMenuController(user);
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

    private void setMenuController(User loggedUser) {
        if (loggedUser instanceof Manager) {
            menuController = new ManagerMenuController(loggedUser, view);
            menuController.handleMenu(menuController.getMainMenuMap(), view::printManagerMenu);
        } else if(loggedUser instanceof Mentor){
            menuController = new MentorMenuController(loggedUser, view);
            menuController.handleMenu(menuController.getMainMenuMap(), view::printMentorMenu);
        } else if(loggedUser instanceof Student) {
            menuController = new StudentMenuController(loggedUser, view);
            menuController.handleMenu(menuController.getMainMenuMap(), view::printStudentMenu);
        } else {
            menuController = new AdminMenuController(loggedUser, view);
            menuController.handleMenu(menuController.getMainMenuMap(), view::printOfficeWorkerMenu);
        }
    }

}
