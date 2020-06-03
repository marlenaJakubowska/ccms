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
}
