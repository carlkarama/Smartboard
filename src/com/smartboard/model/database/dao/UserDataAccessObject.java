package com.smartboard.model.database.dao;

import com.smartboard.model.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDataAccessObject extends DataAccessObject<User> {

    private static final String INSERT = "INSERT INTO user(userID, firstName, lastName) VALUES (NULL, ?, ?)";

    public UserDataAccessObject(Connection connection) {
        super(connection);
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
        return null;
    }

    @Override
    public User create(User dto) {
        try(PreparedStatement preparedStatement = this.connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, dto.getFirstName());
            preparedStatement.setString(2, dto.getLastName());
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
