package com.smartboard.model.workspace;

import java.util.List;

/**
 *  @author Carl Karama
 *  @see Task
 *  @see Project
 *  @version 1.0 */


public class Column {

    // the name of the column;
    private String column;

    private List<Task> tasks;

    public Column(String column, List<Task> tasks) {
        this.column = column;
        this.tasks = tasks;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
