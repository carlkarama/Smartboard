package com.smartboard.model.workspace;

/**@author Carl Karama
 * @see ProjectTaskable
 * @implNote This is a child interface to provide the ability to rename a project
 * @version 1.0 */

public interface RenameProject extends ProjectTaskable {

    public void renameProjectBoard();
}
