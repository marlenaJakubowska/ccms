package com.codecool.ccms.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.jakewharton.fliptables.FlipTable;
import com.jakewharton.fliptables.FlipTableConverters;

public class View {

    Scanner scanner = new Scanner(System.in);

    public void printTableFromDB(ResultSet resultSet) throws SQLException {
        System.out.println(FlipTableConverters.fromResultSet(resultSet));
    }

    public String takeUserInput(String message) {
        String userInput;
        Scanner scan = new Scanner(System.in);
        boolean invalidInput;
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9@.]");

        do{
            System.out.println(message);
            userInput = scan.nextLine();
            invalidInput = pattern.matcher(userInput).find();

            if(invalidInput){
                System.out.println("That's not a valid input");
            }
        }while (invalidInput);
        return userInput;
    }

    public void printArray(List<String[]> list) {
        list.forEach(arr -> System.out.println(Arrays.toString(arr)));
    }

    public static void printLogin() {
        System.out.println("Enter your login");
        System.out.println("Enter your password");
    }

    public void printManagerMenu(){
        String[] headers = {"Options you can access:"};
        String[][] data = {
                {"[1] Add new mentor"},
                {"[2] Remove mentor"},
                {"[3] Edit mentor"},
                {"[4] Show list of users by role"},
                {"[5] Show list of all users"},
        };
        System.out.println(FlipTable.of(headers,data));
    }

    public void printOfficeWorkerMenu(){
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
                {"[2] Add student to class"},
                {"[3] Edit student data"},
                {"[4] Remove student from class"},
                {"[5] Check attendance of students"},
                {"[6] Grade an assignments"},
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

    public void printStudentAttendance(List<String> list) {
        for (String pair : list) {
            System.out.println(pair);
        }
    }

}
