package com.codecool.ccms;

import com.codecool.ccms.controllers.LoginController;

public class App
{
    public static void main( String[] args )
    {
        LoginController loginController = new LoginController();
        loginController.init();
    }
}
