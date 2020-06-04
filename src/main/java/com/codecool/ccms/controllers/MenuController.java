package com.codecool.ccms.controllers;

import com.codecool.ccms.dao.UserDaoImpl;
import com.codecool.ccms.models.Role;
import com.codecool.ccms.models.User;
import com.codecool.ccms.models.factory.UserFactory;
import com.codecool.ccms.view.View;

import java.util.HashMap;
import java.util.Map;

public abstract class MenuController {

    protected final View view;
    private final UserDaoImpl userDaoImpl;
    protected User user;
    private final UserFactory userFactory;
    private Role role;
    protected Map<String, Runnable> mainMenuMap;

    public MenuController(User user, View view) {
        this.user = user;
        this.view = view;
        mainMenuMap = new HashMap<>();
        this.userDaoImpl = new UserDaoImpl();
        this.userFactory = new UserFactory(userDaoImpl);

    }

    public void addUser(Role role) {
        System.out.println("Add new user");
        String name = view.takeUserInput("Enter name: ");
        String surname = view.takeUserInput("Enter surname: ");
        String email = name + surname + "@cc.com";
        String password = "password";
        this.role = role;
        //System.out.println(role.toString());
        int id = -1; //default value
        User user = userFactory.create(id, name, surname,email,password, role);
        userDaoImpl.add(user);
    }

    public void handleMenu(Map<String, Runnable> menuMap, Runnable uiMenu) {
        boolean isRunning = true;
        do {
            uiMenu.run();
            String input = view.takeUserInput("Choose option ");
            if (input.equals("0")) {
                isRunning = false;
                continue;
            }
            try {
                menuMap.get(input).run();
            } catch (NullPointerException e) {
                System.out.println("No such option");
            }
        } while (isRunning);
    }

    public Map<String, Runnable> getMainMenuMap() {
        return mainMenuMap;
    }

    public void displayAllStudents() {
        userDaoImpl.sendPrintQueryToDB("SELECT name, surname, email FROM User WHERE roleID = 4");
    }
}
