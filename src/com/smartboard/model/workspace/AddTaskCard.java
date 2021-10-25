package com.smartboard.model.workspace;

/**@author Carl Karama
 * @see Taskable
 * @implNote This is a child interface to provide the adding of tasks
 * @version 1.0 */

public interface AddTaskCard<T extends Taskable> {

    public boolean addTaskCard(T task);
}
