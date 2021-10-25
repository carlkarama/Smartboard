package com.smartboard.model.workspace;

public interface AddColumn extends Columnable {

    /**@param column is the object to be added
     * @return true if the column object is inserted into the list of columns else return false */
    public boolean addColumn(Column column);

}
