package com.smartboard.model.database.dao;

import com.smartboard.exception.DuplicateUsernameException;
import com.smartboard.model.user.Profile;

import java.sql.*;
import java.util.List;

public class ProfileDataAccessObject extends DataAccessObject<Profile> {

    private static final String INSERT_PROFILE = "INSERT INTO profile(userID, username, password, avatar) VALUES (?, ?, ?, ?)";

    private static final String INSERT_USER = "INSERT INTO user(userID, firstName, lastName) VALUES (NULL, ?, ?)";

    private static final String INSERT_MEMBERSHIP = "INSERT INTO membership(userID, roleID, sessionID) VALUES (?, ?, ?)";

    public ProfileDataAccessObject(Connection connection) {
        super(connection);
    }

    @Override
    public Profile findByID(long id) {
        return null;
    }

    @Override
    public List<Profile> findAll() {
        return null;
    }

    @Override
    public Profile update(Profile dto) {
        return null;
    }

    @Override
    public Profile create(Profile dto) {

        ResultSet generatedKeys;

        try (PreparedStatement userPreparedStatement = this.connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement profilePreparedStatement = this.connection.prepareStatement(INSERT_PROFILE)) {

            userPreparedStatement.setString(1, dto.getUser().getFirstName());
            userPreparedStatement.setString(2, dto.getUser().getLastName());
            userPreparedStatement.execute();

            int affectedRows = userPreparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            generatedKeys = userPreparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                dto.getUser().setUserID(generatedKeys.getInt(1));
                System.out.println(generatedKeys.getInt(1));
                profilePreparedStatement.setInt(1, dto.getUser().getUserID());
                profilePreparedStatement.setString(2, dto.getUsername());
                profilePreparedStatement.setString(3, dto.getPassword());
                profilePreparedStatement.setString(4, dto.getProfilePicturePath());
                profilePreparedStatement.execute();
            } else {
                throw new DuplicateUsernameException("Error: The username entered already exists!");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
