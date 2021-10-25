package com.smartboard.model.database.dao;

import com.smartboard.model.database.DatabaseHandlerSingleton;
import com.smartboard.model.database.dto.DataTransferObject;
import java.sql.Connection;
import java.util.List;

/** @author Carl Karama
 *  @see ProfileDataAccessObject
 *  @see ProjectDataAccessObject
 *  @see DatabaseHandlerSingleton
 *  @see DataTransferObject
 *  @implSpec DataAccessObject is a generic abstract class that provides an abstraction between JDBC and the rest of the code to implement common CRUD operations.
 *            DataAccessObject & child classes uses Facade Pattern to access classes that implement DataTransferObject interface.
 *  @implNote Extend to class which you want to perform common CRUD (Create, Read, Update, Delete) operations.
 *            DataTransferObject is an interface acts as a placeholder for model classes e.g. Profile, Project etc.
 *            Any class that implements DataTransferObject can be accessed from their respective DataAccessObject classes e.g. ProfileDataAccessObject, ProjectDataAccessObject
 *
 *  @version 1.0 */

public abstract class DataAccessObject<T extends DataTransferObject> {

    protected Connection connection;

    public DataAccessObject() {

    }

    public DataAccessObject(Connection connection) {
        super();
        this.connection = connection;
    }

    public abstract T findByID(long id);  //generic method to search for user by ID

    public abstract List<T> findAll();  //generic method to return a list of users

    public abstract T update(T dto);  //generic method to update profile details

    public abstract boolean create(T dto); //generic method to register new user

    public abstract void delete(long id);  //generic method to delete existing user

    public abstract int getLastValue(String sequence);

}
