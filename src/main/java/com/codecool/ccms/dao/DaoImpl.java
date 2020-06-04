package com.codecool.ccms.dao;

import com.codecool.ccms.view.View;

import java.sql.*;

public abstract class DaoImpl<T> implements Dao<T> {

    protected Connection connection;
    protected Statement statement;

    private final View view = new View();

    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/schoolDB");
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.getStackTrace();
        } catch (SQLException e) {
            System.out.println("Could not connect to DB " + e.getMessage());
        }
    }

    public void executeQuery(String  query){
        connect();
        try {
            statement.execute(query);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(String table, String[] columns, String[] values) {
        String columnsAsQuery = String.join(",", columns);
        String valuesAsQuery = String.join(",", values);
        String query = String.format("INSERT INTO %s (%s) VALUES (%s);", table,
                columnsAsQuery, valuesAsQuery);
        System.out.println(query);
        connect();
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void edit(String table, String column, String newValue, String condition) {
        if (column.toLowerCase().equals("id")) {
            System.out.println("Unable to change id");
            return;
        }
        String query = String.format("UPDATE %s SET %s = %s WHERE %s;", table, column, newValue, condition);
        executeQuery(query);
        }


    public void createPrintQueryForDB(String table, String columns, String condition) {
        String where = condition.isEmpty() ? "" : "WHERE " + condition;
        String query = String.format("SELECT %s FROM %s %s;", columns, table, where);
        sendPrintQueryToDB(query);
    }

    public void sendPrintQueryToDB(String query) {
        connect();
        try {
            ResultSet results = statement.executeQuery(query);
            view.printTableFromDB(results);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //TO BE CONTINUED
//    public void createPrintQueryUserTable(String column, String condition) {
//        createPrintQueryForDB("User", column, condition);
//    }
//
//    public void displayVariousColumns(String table, String[] columns, String condition) {
//        String columnsAsQuery = String.join(",", columns);
//        String query = String.format("SELECT %s FROM %s %s;", columnsAsQuery, table, condition);
//    }
}


