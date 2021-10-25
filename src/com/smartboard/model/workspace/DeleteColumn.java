package com.smartboard.model.workspace;

import com.smartboard.model.workspace.Column;
import com.smartboard.model.workspace.Columnable;

public interface DeleteColumn extends Columnable {

    /**@param column is the object to be deleted
     * @return true if the column object is removed from the list of columns else return false */
    public boolean deleteExistingColumn(Column column);
}
