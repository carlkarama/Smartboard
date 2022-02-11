package com.smartboard.model.workspace;


import com.smartboard.model.database.dto.DataTransferObject;

import java.time.LocalDate;
import java.util.Date;

/**
 *  @author Carl Karama
 *  @see Column
 *  @version 1.0 */

public class Task implements Taskable, DataTransferObject {

    //ID of task
    private int taskID;

    // the name of the task
    private String task;

    // when the task begins
    private LocalDate startDate;

    // when the task ends
    private LocalDate endDate;

    // checks whether task is completed;
    private boolean isCompleted;

    // a short summary of the task to be completed
    private String description;

    //a checklist to finish off items
    private String checklist;

    public Task() {}


    public Task(String task) {
        this.task = task;
    }

    public Task(String task, boolean isCompleted) {
        this.task = task;
        this.isCompleted = isCompleted;
    }

    public Task(String task, LocalDate endDate, String description) {
        this.task = task;
        this.endDate = endDate;
        this.description = description;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public void getID() {

    }
}
