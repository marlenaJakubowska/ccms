package com.codecool.ccms.dao;

import com.codecool.ccms.models.Role;
import com.codecool.ccms.models.User;
import com.codecool.ccms.models.factory.UserFactory;

import java.sql.*;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.List;

public class UserDaoImpl extends DaoImpl<User> implements UserDao  {

    private static int nextInt;

    public UserDaoImpl() {
        nextInt = getCurrentMaxId();
    }

    private int getCurrentMaxId() {
        String query = "SELECT MAX(id) AS maxId FROM User;";
        connect();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            int maxId = resultSet.getInt("maxId") + 1;
            statement.close();
            connection.close();
            return maxId;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    private List<User> getUsers(String query) {
        List<User> users = new ArrayList<>();
        connect();
        try {
            System.out.println(query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                Role role = Role.valueOf(resultSet.getInt("roleId"));
                System.out.println(role);
                User user = new UserFactory(this).create(id, name, surname, email, password, role);
                users.add(user);
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
        String id = String.valueOf(nextInt++);
        String role = String.valueOf(user.getRole().getRoleId());
        createUserColumns(new String[] {id, user.getName(), user.getSurname(), user.getEmail(),
                user.getPassword(), role});
    }

    private void createUserColumns(String[] values) {
        String[] columns = {"id", "name", "surname", "email", "password", "roleId"};
        for (int i = 0; i < 6; i++) {
            values[i] = String.format("'%s'", values[i]);
        }
        add("User", columns, values);
    }

    @Override
    public void remove(int userId) {
        String query = "DELETE FROM User WHERE id = '" + userId + "'";
        executeQuery(query);
    }

    public void prepareToEdit(String table, String id, String column, String newValue) {
        String condition = String.format("id = %s", id);
        edit(table, column, newValue, condition);

    }

    public void editUser(String id, String column, String newValue) {
        newValue = String.format("'%s'", newValue);
        prepareToEdit("User", id, column, newValue);
    }

    @Override
    public List<User> getAll() {
        System.out.println("test3.1");
        return getUsers("SELECT * FROM User;");
    }
}
