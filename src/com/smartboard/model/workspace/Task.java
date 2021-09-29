package com.smartboard.model.workspace;

import com.smartboard.model.workspace.EditProfileTaskable;
import com.smartboard.model.workspace.Taskable;

import java.util.Date;

/**
 *  @author Carl Karama
 *  @see EditProfileTaskable
 *  @version 1.0 */

public class Task implements AddTaskCard, EditTaskName, DeleteTask, ReorderTaskWithinColumn {

    // the name of the task
    private String task;

    // when the task begins
    private Date startDate;

    // when the task ends
    private Date endDate;

    // checks whether task is completed;
    private boolean isCompleted;


    @Override
    public void editTaskName() {

    }

    @Override
    public void addTaskCard() {

    }

    @Override
    public void reorderTaskWithinColumn() {

    }

    @Override
    public void deleteExistingTask() {

    }

}
