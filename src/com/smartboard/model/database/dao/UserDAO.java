package com.smartboard.model.database.dao;

/**@author Carl Karama
 * @implNote A Data Access Object to implement CRUD operations on {@code User} and {@code Profile} class
 * @version 1.0
 * */

public interface UserDAO {

    public void createUser();

    public void updateUser();

    public void deleteUser();

    public void readUser();

}
