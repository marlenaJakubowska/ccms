package com.codecool.ccms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DaoImpl<T> implements Dao<T> {

    protected Connection connection;
    protected Statement statement;

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
        System.out.println("connected to db"); //for testing
    }

    public void executeQuery(String  query){
        System.out.println("test7");
        connect();
        System.out.println("test8");
        try {
            statement.execute(query);
            System.out.println("test9");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("test10");
            e.printStackTrace();
        }
    }

    public void add(String table, String[] columns, String[] values) {
        System.out.println("test5");
        String columnsAsQuery = String.join(",", columns);
        String valuesAsQuery = String.join(",", values);
        String query = String.format("INSERT INTO %s (%s) VALUES (%s);", table,
                columnsAsQuery, valuesAsQuery);
        System.out.println(query);
        System.out.println("test6");
        connect();
        System.out.println("test2.1");
        try {
            statement.execute(query);
            System.out.println("test2.2");
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




//
//    public void getLastIncrementedId(String table) {
//    String query = "'SELECT id from'" + table + "'order by id DESC limit 1'";
//    executeQuery(query);
//
//    }
}


