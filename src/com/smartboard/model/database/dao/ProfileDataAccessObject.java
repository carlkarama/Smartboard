package com.smartboard.model.database.dao;

import com.smartboard.model.security.Session;
import com.smartboard.model.user.Profile;
import com.smartboard.view.FactoryAlertViewCreator;

import java.sql.*;
import java.util.List;

/** @author Carl Karama
 *  @see DataAccessObject
 *  @implNote The ProfileDAO provides an abstraction between JDBC & the Profile class.
 *  @version 1.0 */

public class ProfileDataAccessObject extends DataAccessObject<Profile> {

    // SQL statement to insert basic user
    private static final String INSERT_USER = "INSERT INTO user(userID, username,  password, avatar, firstName, lastName) VALUES (NULL, ?, ?, ?, ?, ?)";

    // get the userID of the user
    String GET_ID = "SELECT userID FROM user WHERE username = ?";

    // SQL statement to insert basic user membership
    String INSERT_MEMBERSHIP = "INSERT INTO membership(userID, roleID, sessionID) VALUES(LAST_INSERT_ID(), 1, 1)";

    public ProfileDataAccessObject(Connection connection) {
        super(connection);
    }

    public ProfileDataAccessObject() {

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
    public boolean create(Profile dto) {

        // Initialise prepared statements in try catch block to automatically close open resources
        try(PreparedStatement userPreparedStatement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement membershipPreparedStatement = connection.prepareStatement(INSERT_MEMBERSHIP)) {

            userPreparedStatement.setString(1, dto.getUsername());
            userPreparedStatement.setString(2, dto.getPassword());
            userPreparedStatement.setString(3, dto.getProfilePicturePath());
            userPreparedStatement.setString(4, dto.getUser().getFirstName());
            userPreparedStatement.setString(5, dto.getUser().getLastName());
            userPreparedStatement.execute();

            if (userPreparedStatement.getGeneratedKeys().next()) {
                try {
                    membershipPreparedStatement.executeUpdate();
                    return true;
                } catch (SQLException sqlException) {
                    FactoryAlertViewCreator.getAlert("error:sqle");
                }
            } else if (userPreparedStatement.getGeneratedKeys().isBeforeFirst()) {
                   FactoryAlertViewCreator.getAlert("error:uae");
            }
        } catch (SQLException sqlException) {
            FactoryAlertViewCreator.getAlert("error:sqle");
        }
        return false;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public int getLastValue(String sequence) {

        try(PreparedStatement preparedStatement = this.connection.prepareStatement(GET_ID, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, sequence);
            preparedStatement.execute();

            if (preparedStatement.getResultSet().next()) {
                return preparedStatement.getResultSet().getInt("userID");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return 0;
    }

    public Profile login(Profile dto) {
        // SQL statement to find a user's login credentials
        String LOGIN_USER = "SELECT username, password FROM user WHERE username LIKE ? AND password LIKE ?";

        try(PreparedStatement preparedStatement = this.connection.prepareStatement(LOGIN_USER, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, dto.getUsername());
            preparedStatement.setString(2, dto.getPassword());
            preparedStatement.execute();

            if (preparedStatement.getResultSet().next()) {
                //get userID
                int userID = getLastValue(dto.getUsername());

                if (userID == 0) {
                    //display error message
                    FactoryAlertViewCreator.getAlert("error:uinf");
                } else {
                    //System.out.println(userID);
                    dto.setSession(Session.LOGGED_IN);
                    dto.setId(userID);
                    return dto;
                }
            } else {
                FactoryAlertViewCreator.getAlert("error:uopis");
            }
        } catch (SQLException sqlException) {
            FactoryAlertViewCreator.getAlert("error:sqle");
        }
        return null;
    }
}
