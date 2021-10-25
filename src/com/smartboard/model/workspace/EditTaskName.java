package com.smartboard.model.workspace;

/**@author Carl Karama
 * @see Taskable
 * @implNote This is a child interface to provide the editing of tasks
 * @version 1.0 */

public interface EditTaskName<T extends Taskable> {

    public boolean editTaskName(T task1, T task2);
}
