package com.smartboard.model.database.dao;

import com.smartboard.model.database.dto.DataTransferObject;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/** @author Carl Karama
 *  @implNote The DAO provides an abstraction between JDBC and the rest of the code to implement CRUD operations
 *  @version 1.0 */

public abstract class DataAccessObject<T extends DataTransferObject> {

    protected final Connection connection;

    public DataAccessObject(Connection connection) {
        super();
        this.connection = connection;
    }

    public abstract T findByID(long id);

    public abstract List<T> findAll();

    public abstract T update(T dto);

    public abstract T create(T dto) throws SQLException;

    public abstract void delete(long id);

}
