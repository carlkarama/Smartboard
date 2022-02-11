package com.smartboard.model.database.dao;

import com.smartboard.model.workspace.Task;
import com.smartboard.view.FactoryAlertViewCreator;

import java.sql.*;
import java.util.List;

public class TaskDataAccessObject extends DataAccessObject<Task> {

    private static String INSERT_TASK = "INSERT INTO task(taskID, task,  taskDueDate, Description) VALUES (NULL, ?, ?, ?)";
    private static String INSERT_CHECKLIST = "INSERT INTO checklist(taskID, checklistItem) VALUES (LAST_INSERT_ID(), 'Add Grades')";

    public TaskDataAccessObject(Connection connection) {
        super(connection);
    }

    @Override
    public Task findByID(long id) {
        return null;
    }

    @Override
    public List<Task> findAll() {
        return null;
    }

    @Override
    public Task update(Task dto) {
        return null;
    }

    @Override
    public boolean create(Task dto) {

        try(PreparedStatement insertTaskPreparedStatement = this.connection.prepareStatement(INSERT_TASK, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement insertChecklistListPreparedStatement = this.connection.prepareStatement(INSERT_CHECKLIST, Statement.RETURN_GENERATED_KEYS)) {

            insertTaskPreparedStatement.setString(1, dto.getTask());
            insertTaskPreparedStatement.setString(2, dto.getEndDate().toString());
            insertTaskPreparedStatement.setString(3, dto.getDescription());

            insertTaskPreparedStatement.execute();

            if (insertTaskPreparedStatement.getGeneratedKeys().next()) {

                insertChecklistListPreparedStatement.executeUpdate();
                return true;
            }

        } catch (SQLException sqlException) {
            FactoryAlertViewCreator.getAlert("error:sqle");
        } catch (NullPointerException nullPointerException) {
            FactoryAlertViewCreator.getAlert("error:npe");
        }
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
