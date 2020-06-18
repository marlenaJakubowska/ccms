package com.codecool.ccms.controllers;

import com.codecool.ccms.dao.UserDaoImpl;
import com.codecool.ccms.models.Assignment;
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
    protected Assignment assignment;

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

    public void removeUser() {
        String idInput = view.takeUserInput("Enter user id to remove: ");
        int id = Integer.parseInt(idInput);
        userDaoImpl.remove(id);
    }

    public void removeUserByRole(Role role) {
        this.role = role;
        String idInput = view.takeUserInput("Enter user id to remove: ");
        int id = Integer.parseInt(idInput);
        userDaoImpl.remove(id);
    }

    public void editUser(){
        String id = view.takeUserInput("Enter user id to edit: ");
        String column = view.takeUserInput("Enter user column: ");
        String newValue = view.takeUserInput("Enter new value: ");
        userDaoImpl.edit(id, column, newValue);
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
        userDaoImpl.sendPrintQueryToDB("SELECT id, name, surname, email FROM User WHERE roleID = 4");
    }

    public void addUserAssigment(Assignment Assigment) {
        System.out.println("Add new assigment");
        String name = view.takeUserInput("Enter name: ");
        String description = view.takeUserInput("Enter description: ");
        String author = user.getName();
        String grade = "0";
        int id = -1; //default value
        Assignment assignment = new Assignment(id, name, description,grade,author);
        userDaoImpl.addAssigment(assignment);
    }

    public void removeAssigment() {
        String idInput = view.takeUserInput("Enter assigment id to remove: ");
        int id = Integer.parseInt(idInput);
        userDaoImpl.removeAssignment(id);
    }

    public void displayYourGrades(){
        String autorToFormat = user.getName();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("'");
        stringBuilder.append(autorToFormat);
        stringBuilder.append("'");
        String autor = stringBuilder.toString();
        userDaoImpl.sendPrintQueryToDB("SELECT id, name, description, author FROM Assignmnet WHERE author ="+autor);
    }

    public void editUserAssigment(){
        String id = view.takeUserInput("Enter user id to edit: ");
        String newValue = view.takeUserInput("Enter new value: ");
        String column = "grade";
        userDaoImpl.editAssigment(id, column, newValue);
    }
}
