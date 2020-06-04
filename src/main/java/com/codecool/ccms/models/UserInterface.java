package com.codecool.ccms.models;

import com.jakewharton.fliptables.FlipTable;

public class UserInterface {
    public static void login() {
        System.out.println("Enter your login");
        System.out.println("Enter your password");
    }

    public void menagerMenu(){
        String[] headers = {"Options you can access:"};
        String[][] data = {
                {"[1] Add new mentor"},
                {"[2] Remove mentor"},
                {"[3] Edit mentor"},
                {"[4] Show mentors list"},
                {"[5] Show students list"},
        };
        System.out.println(FlipTable.of(headers,data));
    }

    public void officeworkerMenu(){
        String[] headers = {"Options you can access:"};
        String[][] data = {
                {"[1] Show students list"},
        };
        System.out.println(FlipTable.of(headers,data));
    }

    public void mentorMenu() {
        String[] headers = {"Options you can access:"};
        String[][] data = {
                {"[1] Show students list"},
                {"[2] Add an assignments"},
                {"[3] Grade an assignments"},
                {"[4] Check attendance of students"},
                {"[5] Add student to class"},
                {"[6] Remove student from class"},
                {"[7] Edit student data"}
        };
        System.out.println(FlipTable.of(headers,data));
    }

    public void studentMenu(){
        String[] headers = {"Options you can access:"};
        String[][] data = {
                {"[1] Submit assigment"},
                {"[2] Show your grades"}
        };
        System.out.println(FlipTable.of(headers,data));
    }
}

