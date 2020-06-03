package com.codecool.ccms.models.factory;

import com.codecool.ccms.models.Role;

public interface AbstractFactory<T> {
    T create(int id, String name, String surname, String email, String password, Role  role);
}
