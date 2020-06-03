package com.codecool.ccms.dao;

import com.codecool.ccms.models.Role;
import com.codecool.ccms.models.User;
import com.codecool.ccms.models.factory.UserFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends DaoImpl<User> implements UserDao  {

    protected Connection connection;
    protected Statement statement;

    private List<User> getUsers(String query) {
        List<User> users = new ArrayList<>();
        connect();
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                Role role = Role.valueOf(resultSet.getInt("roleId"));
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
        System.out.println("test2");
        String id = String.valueOf(user.getId());
        String role = String.valueOf(user.getRole());
        createUserColumns(new String[] {id, user.getName(), user.getSurname(), user.getEmail(),
                user.getPassword(), role});
    }

    private void createUserColumns(String[] values) {
        System.out.println("test3");
        String[] columns = {"id", "name", "surname", "email", "password", "roleId"};
        for (int i = 0; i < 6; i++) {
            values[i] = String.format("'%s'", values[i]);
        }
        System.out.println("test4");
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
        prepareToEdit("Users", id, column, newValue);
    }


    @Override
    public List<User> getAll() {
        return getUsers("SELECT * FROM users;");
    }
}
