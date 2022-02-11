package com.smartboard.model.database.dao;

import com.smartboard.model.user.Profile;
import com.smartboard.model.workspace.Column;
import com.smartboard.model.workspace.Project;
import com.smartboard.view.FactoryAlertViewCreator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** @author Carl Karama
 *  @implSpec ProjectDataAccessObject is an abstraction between the JDBC and the model {@code Project} which
 *            facilitates CRUD operations
 *  @version 1.0 */

public class ProjectDataAccessObject extends DataAccessObject<Project> {

    private static final String INSERT_PROJECT = "INSERT INTO project(project) VALUES (?)";
    private static final String DELETE_PROJECT = "DELETE FROM project WHERE project = ?";
    private static final String INSERT_USER_PROJECT = "INSERT INTO save(projectID, userID) VALUES (LAST_INSERT_ID(), ?)"; // LAST_INSERT_ID is an SQL function that fetches the last primary key inserted for a column
    private static final String SELECT_PROJECT_ID = "SELECT projectID FROM project WHERE projectID = ?";
    private static final String SELECT_PROJECTS = "SELECT project FROM project INNER JOIN save ON save.projectID = project.projectID WHERE userID = ?";
    private static final String SELECT_USER_ID = "SELECT userID FROM user WHERE username = ?";
    private static final String INSERT_STAGE = "INSERT INTO stage(stage) VALUES (?)";
    private static final String UPDATE_PROJECT = "UPDATE project SET project = ? WHERE project = (?)"; //update project set project = "SAXO" where projectID = 275

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

        try (PreparedStatement updateProjectPreparedStatement = this.connection.prepareStatement(UPDATE_PROJECT, Statement.RETURN_GENERATED_KEYS)) {

            updateProjectPreparedStatement.setString(1, dto.getProject());

            ResultSet resultSet = updateProjectPreparedStatement.executeQuery();

            if (resultSet.next()) {
                dto.setProject(resultSet.getString("project"));
                return dto;
            }

        } catch (SQLException sqlException) {
            FactoryAlertViewCreator.getAlert("error:sqle");
        }

        return null;
    }

    /**
     * @implSpec This method creates new projects. In the database, a project has a unique key, therefore, each project name is distinct <br>
     * @implNote {@code create} overrides create(T dto) in {@code DataAccessObject} class<br>
     * {@code INSERT_PROJECT} is an SQL query that inserts a new rows to project table <br>
     * {@code INSERT_USER_PROJECT} is an SQL query that inserts foreign keys to save table by referencing keys from user and project tables <br>
     * {@code dto} is instance variable is an abbreviation of DataTransferObject.<br> */
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

    public boolean createColumn(String column) {

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(INSERT_STAGE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, column);
            preparedStatement.execute();

            if (preparedStatement.getGeneratedKeys().next()) {
                return true;
            }
        } catch (SQLException sqlException) {
            FactoryAlertViewCreator.getAlert("error:sqle");
        }
        return false;
    }

    @Override
    public void delete(long id) {

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

    public int getUserID(Project dto) {

        int id = 0;

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_USER_ID, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, dto.getProfile().getUsername());
            preparedStatement.execute();

            if (preparedStatement.getResultSet().next()) {

                id = preparedStatement.getResultSet().getInt("userID");

                return id;
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return id;
    }

    /** @implSpec Finds all the projects which have been created in association with the given userID
     *  @implNote Pass a userID to find row or rows which have given userID.
     *  @return  List of projects which match given userID  */
    public List<Project> loadProject(Project project) {

        List<Project> projects = new ArrayList<>();

        try(PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_PROJECTS, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, project.getProfile().getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                projects.add(new Project(resultSet.getString("project")));
            }
            //System.out.println(projects);
            return projects;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
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
