package com.smartboard.model.database.dao;

import com.smartboard.model.workspace.Project;
import com.smartboard.view.FactoryAlertViewCreator;

import java.sql.*;
import java.util.List;

public class ProjectDataAccessObject extends DataAccessObject<Project> {

    private static final String INSERT_PROJECT = "INSERT INTO project(project) VALUES (?)";
    private static final String DELETE_PROJECT = "DELETE FROM project WHERE project = ?";
    private static final String INSERT_USER_PROJECT = "INSERT INTO save(projectID, username) VALUES (?, ?)";
    private static final String GET_ID = "SELECT projectID FROM project WHERE projectID = ?";


    public ProjectDataAccessObject(Connection connection) {
        super(connection);
    }

    @Override
    public Project findByID(long id) {
        return null;
    }

    @Override
    public List<Project> findAll() {
        return null;
    }

    @Override
    public Project update(Project dto) {
        return null;
    }

    @Override
    public boolean create(Project dto) {

        // Initialise prepared statements in try catch block to automatically close open resources
        try(PreparedStatement projectPreparedStatement = connection.prepareStatement(INSERT_PROJECT, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement userPreparedStatement = connection.prepareStatement(INSERT_USER_PROJECT)) {

            projectPreparedStatement.setString(1, dto.getProject());
            projectPreparedStatement.execute();

            if (projectPreparedStatement.getGeneratedKeys().next()) {
                try {

                    int projectID = getLastValue(dto.getProject());
                    int userID = dto.getUser().getId();

                    userPreparedStatement.setInt(1, projectID);
                    userPreparedStatement.setInt(2, userID);

                    return userPreparedStatement.execute();

                } catch (SQLException e) {
                    //e.printStackTrace();
                    FactoryAlertViewCreator.getAlert("error:sqle");
                }
            } else if (projectPreparedStatement.getGeneratedKeys().isBeforeFirst()) {
                FactoryAlertViewCreator.getAlert("error:pae");
            } else {
                FactoryAlertViewCreator.getAlert("error:fpc");
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

        try(PreparedStatement preparedStatement = this.connection.prepareStatement(GET_ID, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, sequence);
            preparedStatement.execute();

            if (preparedStatement.getResultSet().next()) {
                return preparedStatement.getResultSet().getInt("projectID");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return 0;
    }

    public boolean delete(Project dto) {

        // Initialise prepared statements in try catch block to automatically close used resources
        try(PreparedStatement deleteProjectPreparedStatement = connection.prepareStatement(DELETE_PROJECT, Statement.RETURN_GENERATED_KEYS)) {
            deleteProjectPreparedStatement.setString(1, dto.getProject());
            deleteProjectPreparedStatement.execute();
            return true;
        } catch (SQLException sqlException) {
            FactoryAlertViewCreator.getAlert("error:sqle");
        }
        return false;
    }
}
