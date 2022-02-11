package com.smartboard.model.workspace;

import java.util.ArrayList;
import java.util.List;

/**
 *  @author Carl Karama
 *  @see Task
 *  @see Project
 *  @version 1.0 */


public class Column implements AddTaskCard<Task>, EditTaskName<Task>, DeleteTask<Task>, ReorderTaskWithinColumn<Task> {

    // the name of the column;
    private String column;

    private List<Task> tasks = new ArrayList<>();

    public Column(String column) {
        this.column = column;
    }

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

    @Override
    public boolean addTaskCard(Task task) {
        return this.tasks.add(task);
    }

    @Override
    public boolean deleteExistingTask(Task task) {
        return this.tasks.remove(task);
    }

    @Override
    public boolean editTaskName(Task task1, Task task2) {

        int nameToChange = this.tasks.indexOf(task1);

        return this.tasks.set(nameToChange, task1) != null;
    }

    @Override
    public boolean reorderTaskWithinColumn(Task task) {
        return false;
    }
}
