package com.codecool.ccms.dao;

public interface Dao<T> {
    void remove(int id);
    void edit(String table, String column, String newValue, String condition);
    void add(T t);

}
