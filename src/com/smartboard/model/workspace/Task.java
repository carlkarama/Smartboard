package com.smartboard.model.workspace;


import java.util.Date;

/**
 *  @author Carl Karama
 *  @see Column
 *  @version 1.0 */

public class Task implements Taskable {

    // the name of the task
    private String task;

    // when the task begins
    private Date startDate;

    // when the task ends
    private Date endDate;

    // checks whether task is completed;
    private boolean isCompleted;

    public Task(String task) {
        this.task = task;
    }

    public Task(String task, boolean isCompleted) {
        this.task = task;
        this.isCompleted = isCompleted;
    }

    public Task(String task, Date startDate, Date endDate, boolean isCompleted) {
        this.task = task;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isCompleted = isCompleted;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
