package com.codecool.ccms;


import com.codecool.ccms.controllers.ManagerMenuController;
import com.codecool.ccms.dao.UserDaoImpl;
import com.codecool.ccms.models.User;

public class App
{
    public static void main( String[] args )
    {
        UserDaoImpl userDao = new UserDaoImpl();
        ManagerMenuController managerMenuController = new ManagerMenuController(userDao);
        managerMenuController.addMentor();
        for (User user : userDao.getAll()) {
            System.out.println(user.toString());

        }
    }
}
