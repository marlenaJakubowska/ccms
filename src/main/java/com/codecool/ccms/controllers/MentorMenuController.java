package com.codecool.ccms.controllers;

import com.codecool.ccms.dao.UserDaoImpl;
import com.codecool.ccms.models.Role;
import com.codecool.ccms.models.User;
import com.codecool.ccms.models.factory.UserFactory;
import com.codecool.ccms.view.View;

public class MentorMenuController extends MenuController {

    private static View view = new View();
    private final UserDaoImpl userDaoImpl;
    private final UserFactory userFactory;

    public MentorMenuController(User user, View view) {
        super(user, view);
        createMainMenuMap();

        userDaoImpl = new UserDaoImpl();
        userFactory = new UserFactory(userDaoImpl);
    }

    private void addStudent() {
        addUser(Role.valueOf("STUDENT"));
    }

    private void editStudent() {
        int role = Role.STUDENT.getRoleId();
        String roleString = String.valueOf(role);
        userDaoImpl.createPrintQueryUserTable("*", "roleId = " + roleString);
        editUser();
    }

    private void removeStudent() {
        removeUserByRole(Role.valueOf("STUDENT"));

    }

    private void createMainMenuMap() {
        mainMenuMap.put("1", this::displayAllStudents);
        mainMenuMap.put("2", this::addStudent);
        mainMenuMap.put("3", this::editStudent);
        mainMenuMap.put("4", this::removeStudent);
        //mainMenuMap.put("5", this::checkAttendance);   // TO DO
        //mainMenuMap.put("6", this::addAssignment);   // TO DO
        //mainMenuMap.put("7", this::gradeAssignment);   // TO DO

    }

}
