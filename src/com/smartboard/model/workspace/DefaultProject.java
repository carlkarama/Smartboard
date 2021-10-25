package com.smartboard.model.workspace;

/**@author Carl Karama
 * @see ProjectTaskable
 * @implNote This is a child interface to provide the make a project the default one
 * @version 1.0 */

public interface DefaultProject extends ProjectTaskable {

    public void setDefaultProject();
}
