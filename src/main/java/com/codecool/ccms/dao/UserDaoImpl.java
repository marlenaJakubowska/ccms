package com.codecool.ccms.dao;

import com.codecool.ccms.models.Mentor;
import com.codecool.ccms.models.Role;
import com.codecool.ccms.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends DaoImpl<User> implements UserDao  {

    protected Connection connection;
    protected Statement statement;


    //ADD FACTORY
    private List<User> getUsers(String query) {
        List<User> users = new ArrayList<>();
        connect();
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String firstName = resultSet.getString("First_name");
                String surname = resultSet.getString("Surname");
                String email = resultSet.getString("Email");
                String password = resultSet.getString("Password");
                Role role = Role.valueOf(resultSet.getInt("roleId"));
                //factory here
//                User user = new Mentor(); //test
//                users.add(user);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void add(User user) {
        createUserColumns(new String[] {user.getFirstName(), user.getSurname(), user.getEmail(),
                user.getPassword(), user.getRole().toString()});
    }

    private void createUserColumns(String[] values) {
        String[] columns = {"First_name", "Surname", "Email", "Password", "roleId"};
        for (int i = 0; i < 5; i++) {
            values[i] = String.format("'%s'", values[i]);
        }
        add("User", columns, values);
    }

    @Override
    public void remove(User user) {
        String query = "DELETE FROM User WHERE id = '" + user.getId() +
                "' AND roleId = '" + user.getRole().toString() + "'";
        executeQuery(query);

    }

    @Override
    public void update(String id, String... values) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }

//TO BE IMPLEMENTED
//    @Override
//    public void remove(String table, String id) {
//        String query = String.format("DELETE FROM %s WHERE Id = %s;", table, id);
//        executeQuery(query);
//
//    }
//
//    protected void updateById(String table, String id, String column, String newValue) {
//        String condition = String.format("id = %s", id);
//        update(table, column, newValue, condition);
//    }
//
//    @Override
//    public void update(String table, String column, String newValue, String condition) {
//        if (column.toLowerCase().equals("id")) {
//            System.out.println("Unable to change id");
//            return;
//        }
//        String query = String.format("UPDATE %s SET %s = %s WHERE %s;", table, column, newValue, condition);
//        executeQuery(query);
//    }
//
//    @Override
//    public List<User> getAll() {
//        return getUsers("SELECT * FROM users;");
//    }
}
