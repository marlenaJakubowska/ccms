package com.codecool.ccms.dao;

import com.codecool.ccms.view.View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Dao<T> implements IDao {

   protected Connection connection;
   protected Statement statement;

   public void connect() {
      try {
         Class.forName("org.sqlite.JDBC");
         connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/school");
         statement = connection.createStatement();
      } catch (ClassNotFoundException e) {
         e.getStackTrace();
      } catch (SQLException e) {
         System.out.println("Could not connect to DB " + e.getMessage());
      }
   }

   private void executeQuery(String  query){
      connect();
      try {
         statement.execute(query);
         statement.close();
         connection.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   protected void add(String table, String[] columns, String[] values) {
      String columnsAsQuery = String.join(",", columns);
      String valuesAsQuery = String.join(",", values);
      String query = String.format("INSERT INTO %s (%s) VALUES (%s);", table,
              columnsAsQuery, valuesAsQuery);
      executeQuery(query);
   }

   public void removeById(String table, String id) {
      String query = String.format("DELETE FROM %s WHERE Id = %s;", table, id);
      executeQuery(query);
   }

   protected void updateById(String table, String id, String column, String newValue) {
      String condition = String.format("id = %s", id);
      update(table, column, newValue, condition);
   }

   protected void update(String table, String column, String newValue, String condition) {
      if (column.toLowerCase().equals("id")) {
         System.out.println("Unable to change id");
         return;
      }
      String query = String.format("UPDATE %s SET %s = %s WHERE %s;", table, column, newValue, condition);
      executeQuery(query);
      }



   }