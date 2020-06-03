package com.codecool.ccms.dao;

public interface Dao<T> {
    void connect();
    void add(String table, String[] columns, String[] values);
    void remove(String table, String id);
    void update(String table, String column, String newValue, String condition);

}
