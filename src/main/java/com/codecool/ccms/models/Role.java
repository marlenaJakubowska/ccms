package com.codecool.ccms.models;

import java.util.HashMap;
import java.util.Map;

public enum Role {
    MANAGER(1), MENTOR(2), ADMINEMPLOYEE(3), STUDENT(4);

    private final int roleId;
    private static final Map<Integer, Role> map = new HashMap<>();

    Role(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }

    public static Role valueOf(int roleId) {
        for (Role role : Role.values()) {
            map.put(role.roleId, role);
        }
        return map.get(roleId);
    }
}
