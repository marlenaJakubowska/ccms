package com.codecool.ccms.dao;

import com.codecool.ccms.models.Assignment;
import com.codecool.ccms.models.Role;
import com.codecool.ccms.models.User;
import com.codecool.ccms.models.Attendance;
import com.codecool.ccms.models.factory.UserFactory;
import com.codecool.ccms.view.View;

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

    public List<User> getUsers(String query) {
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
                //System.out.println(role); dupa debug
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

    public void edit(String id, String column, String newValue) {
        newValue = String.format("'%s'", newValue);
        prepareToEdit("User", id, column, newValue);
    }

    @Override
    public List<User> getAll() {
        return getUsers("SELECT * FROM User;");
    }


    //FOR PRESENTATION
    public void displayAllUsersForTest() {
        sendPrintQueryToDB("SELECT * FROM User");
    }

    public void addAttendance(String time) {
        View view = new View();
        String[] columns = {"datetime"};
        String[] values = {time};
        add("Attendance", columns, values);
        attendanceCheck();
        view.printStudentAttendance(countingPresence());
    }

    public String foreignAttendanceKey() {
        int idToParse = getCurrentMaxAttendanceId();
        String id = String.valueOf(idToParse);
        return id;
    }

    private int getCurrentMaxAttendanceId() {
        String query = "SELECT MAX(id) AS maxId FROM Attendance;";
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

    List<User> getStudents(){
        List<User> students = getUsers("SELECT * FROM User WHERE roleID = '4';");
        return students;
    }

    public List<String> getAllStudentsNames() {
        List<String> getUsersNames = new ArrayList<>();
        for (int i = 0; i < getStudents().size(); i++){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(getStudents().get(i).getName());
            stringBuilder.append(" ");
            stringBuilder.append(getStudents().get(i).getSurname());
            getUsersNames.add(stringBuilder.toString());
        }
        return getUsersNames;
    }

    public List<String> getAllStudentsID() {
        List<String> studentsIDs = new ArrayList<>();
        for (int i = 0; i < getStudents().size(); i++) {
            int idToParse = getStudents().get(i).getId();
            String id = String.valueOf(idToParse);
            studentsIDs.add(id);
        }
        return studentsIDs;
    }


    private void createAttendanceCheckColumns(String studentID, String attendanceID, String presence) {
        String[] columns = {"student_ID", "attendance_ID", "presence"};
        String[] values = {studentID, attendanceID, presence};
        for (int i = 0; i < 3; i++) {
            values[i] = String.format("'%s'", values[i]);
        }
        add("Attendance_check", columns, values);
    }

    private void attendanceCheck() {
        View view = new View();
        System.out.println("If student is present put 0, if student is absence, put 1\n");
        String studentID = "";
        String attendanceID = foreignAttendanceKey();
        String presence = "";
        for (int i = 0; i < getAllStudentsNames().size(); i++) {
            System.out.println(getAllStudentsNames().get(i));
            presence = view.takeUserInput("Is he present? : ");
            studentID = getAllStudentsID().get(i);
            createAttendanceCheckColumns(studentID, attendanceID, presence);
        }
    }

    public List<Attendance> getAttendance(String query) {
        List<Attendance> attendances = new ArrayList<>();
        connect();
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int student_id = resultSet.getInt("student_id");
                int attendance_id = resultSet.getInt("attendance_id");
                int precence = resultSet.getInt("presence");
                Attendance attendance = new Attendance(student_id, attendance_id, precence);
                attendances.add(attendance);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendances;
    }

    public void addAssigment(Assignment assignment) {
        String id = String.valueOf(nextInt++);
        createAssigmentColumns(new String[] {assignment.getName(), assignment.getDescription(),assignment.getGrade(),assignment.getAuthor()});
    }

    private void createAssigmentColumns(String[] values) {
        String[] columns = {"name", "description","grade","author"};
        for (int i = 0; i < 4; i++) {
            values[i] = String.format("'%s'", values[i]);
        }
        add("Assignmnet", columns, values);
    }

    public void removeAssignment(int assigmentId) {
        String query = "DELETE FROM Assignmnet WHERE id = '" + assigmentId + "'";
        executeQuery(query);
    }

    public void prepareToGradeAssigment(String table, String id, String column, String newValue) {
        String condition = String.format("id = %s", id);
        edit(table, column, newValue, condition);
    }

    public void editAssigment(String id, String column, String newValue) {
        newValue = String.format("'%s'", newValue);
        prepareToGradeAssigment("Assignmnet", id, column, newValue);
    }

    public void displayAllAssigment() {
        sendPrintQueryToDB("SELECT * FROM Assignmnet");
    }

    public List<Attendance> getStudentAttendances() {
        List<Attendance> attendance = getAttendance("SELECT * FROM Attendance_check;");
        return attendance;
    }

    public List<String> countingPresence() {
        List<String> presenceToPrint = new ArrayList<>();
        int absence = 0;
        int presence = 0;
        double percentageOfPresence = 0;
        for (int i = 0; i < getAllStudentsID().size(); i++) {
            StringBuilder pair = new StringBuilder();
            String studentID = getAllStudentsID().get(i);
            String name = getAllStudentsNames().get(i);
            for (int j = 0; j < getStudentAttendances().size(); i++) {
                int presences = getStudentAttendances().get(j).getPresence();
                switch (presences) {
                    case 0 : {
                        presence++;
                        break;
                    }
                    case 1 : {
                        absence++;
                        break;
                    }
            }
        }
            percentageOfPresence = (presence * 100) / (absence + presence);
            String percentageValue = String.valueOf(percentageOfPresence);
            pair.append(name);
            pair.append(" ");
            pair.append(percentageValue);
            presenceToPrint.add(pair.toString());
        }
        return presenceToPrint;
    }


}
