package com.codecool.ccms.dao;

public interface Dao<T> {
    void remove(T t);
    void update(String id, String... values);
    void add(T t);

}
