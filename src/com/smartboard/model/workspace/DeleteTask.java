package com.smartboard.model.workspace;

/**@author Carl Karama
 * @see Taskable
 * @implNote This is a child interface to provide the deletion of tasks
 * @version 1.0 */

public interface DeleteTask<T extends Taskable> extends Taskable {

    public boolean deleteExistingTask(T task);
}
