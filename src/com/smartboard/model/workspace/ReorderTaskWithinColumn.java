package com.smartboard.model.workspace;

/**@author Carl Karama
 * @see Taskable
 * @implNote This is a child interface to provide the reordering of tasks
 * @version 1.0 */

public interface ReorderTaskWithinColumn<T extends Taskable> {

    public boolean reorderTaskWithinColumn(T task);
}
