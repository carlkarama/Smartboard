package com.smartboard.model.database.dao;

import com.smartboard.model.workspace.Project;
import com.smartboard.view.FactoryAlertViewCreator;

import java.sql.*;
import java.util.List;

public class ProjectDataAccessObject extends DataAccessObject<Project> {

    private static final String INSERT_PROJECT = "INSERT INTO project(project) VALUES (?)";
    private static final String DELETE_PROJECT = "DELETE FROM project WHERE project = ?";
    private static final String INSERT_USER_PROJECT = "INSERT INTO save(projectID, userID) VALUES (LAST_INSERT_ID(), ?)";
    private static final String SELECT_PROJECT_ID = "SELECT projectID FROM project WHERE projectID = ?";
    private static final String SELECT_PROJECTS = "SELECT project FROM project INNER JOIN save ON save.projectID = project.projectID WHERE userID = ?";
    private static final String SELECT_USER_ID = "SELECT userID FROM user WHERE username = ?";


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

    /**
     * @implSpec This method creates new projects. dto instance variable is an abbreviation of DataTransferObject.<br>
     * @implNote {@code create} overrides create(T dto) <br>
     * {@code INSERT_PROJECT} is an SQL query that inserts a new rows to project table <br>
     * {@code INSERT_USER_PROJECT} is an SQL query that inserts foreign keys to save table by referencing user and project tables <br>*/
    @Override
    public boolean create(Project dto) {

        // Initialise prepared statements in try catch block to automatically close open resources
        try(PreparedStatement projectPreparedStatement = connection.prepareStatement(INSERT_PROJECT, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement savePreparedStatement = connection.prepareStatement(INSERT_USER_PROJECT, Statement.RETURN_GENERATED_KEYS)) {

            projectPreparedStatement.setString(1, dto.getProject());
            projectPreparedStatement.execute();

            if (projectPreparedStatement.getGeneratedKeys().next()) {

                try {
                    savePreparedStatement.setInt(1, dto.getProfile().getId());
                    savePreparedStatement.executeUpdate();
                    return true;
                } catch (SQLIntegrityConstraintViolationException sqlException) {
                    FactoryAlertViewCreator.getAlert("error:sqlicve");
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

        try(PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_PROJECT_ID, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, sequence);
            preparedStatement.execute();

            if (preparedStatement.getResultSet().next()) {

                System.out.println("Get Last ID:  " + preparedStatement.getResultSet().getInt("projectID"));

                return preparedStatement.getResultSet().getInt("projectID");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return 0;
    }

    public int getProjectID(String project) {
        String PROJECT_ID = "SELECT projectID FROM project WHERE project = ?";

        try(PreparedStatement preparedStatement = this.connection.prepareStatement(PROJECT_ID, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, project);
            preparedStatement.execute();

            if (preparedStatement.getGeneratedKeys().next()) {
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
