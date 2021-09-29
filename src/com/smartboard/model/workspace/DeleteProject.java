package com.smartboard.model.workspace;

/**@author Carl Karama
 * @see ProjectTaskable
 * @implNote This is a child interface to provide the ability to delete a project
 * @version 1.0 */

public interface DeleteProject extends ProjectTaskable {

    public void deleteExistingProjectBoard();
}
