package com.codecool.ccms;

import com.codecool.ccms.controllers.ManagerMenuController;
import com.codecool.ccms.controllers.MentorMenuController;
import com.codecool.ccms.dao.UserDaoImpl;
import com.codecool.ccms.models.User;
import com.codecool.ccms.view.View;

public class App
{
    public static void main( String[] args )
    {
        UserDaoImpl userDao = new UserDaoImpl();

        ManagerMenuController managerMenuController = new ManagerMenuController(userDao;
        
        MentorMenuController mentorMenuController = new MentorMenuController(userDao);
        //managerMenuController.addMentor();
        mentorMenuController.addStudent();
        for (User user : userDao.getAll()) {
            System.out.println(user.toString());
        }
        mentorMenuController.addStudent();

    }
}
