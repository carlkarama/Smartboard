package com.smartboard.model.database.dao;

import com.smartboard.model.user.User;

import java.sql.Connection;
import java.util.List;

public class UserDataAccessObject extends DataAccessObject<User> {

    private static final String INSERT = "INSERT INTO user(userID, username, password, avatar, firstName, lastName) VALUES (NULL, ?, ?, ?, ?, ?)";

    public UserDataAccessObject(Connection connection) {
        super(connection);
    }

    public UserDataAccessObject() {

    }

    @Override
    public User findByID(long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User update(User dto) {
        return dto;
    }

    @Override
    public boolean create(User dto) {
        return false;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public int getLastValue(String sequence) {
        return 0;
    }
}
