package com.codecool.ccms.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import com.jakewharton.fliptables.FlipTable;
import com.jakewharton.fliptables.FlipTableConverters;

public class View {

    Scanner scanner = new Scanner(System.in);

    public void printTableFromDB(ResultSet resultSet) throws SQLException {
        System.out.println(FlipTableConverters.fromResultSet(resultSet));
    }

    public String takeUserInput(String message) {
        System.out.println(message);
        boolean validInput = true;
        String userInput;
        do {
            if(!validInput) {
                System.out.println("Please enter at least one character");
            }
            validInput = false;
            userInput = scanner.nextLine();
            if(!userInput.equals("")) {
                validInput = true;
            }
        } while (!validInput);
        return userInput;
    }

    public void printArray(List<String[]> list) {
        list.forEach(arr -> System.out.println(Arrays.toString(arr)));

    }

    public static void printLogin() {
        System.out.println("Enter your login");
        System.out.println("Enter your password");
    }

    public void printMenagerMenu(){
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

    public void printOfficeworkerMenu(){
        String[] headers = {"Options you can access:"};
        String[][] data = {
                {"[1] Show students list"},
        };
        System.out.println(FlipTable.of(headers,data));
    }

    public void printMentorMenu() {
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

    public void printStudentMenu(){
        String[] headers = {"Options you can access:"};
        String[][] data = {
                {"[1] Submit assigment"},
                {"[2] Show your grades"}
        };
        System.out.println(FlipTable.of(headers,data));
    }
}
