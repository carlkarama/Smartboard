package com.smartboard.model.workspace;

/**@author Carl Karama
 * @see Taskable
 * @implNote This is a child interface to provide the deletion of tasks
 * @version 1.0 */

public interface DeleteTask extends Taskable {

    public void deleteExistingTask();
}
