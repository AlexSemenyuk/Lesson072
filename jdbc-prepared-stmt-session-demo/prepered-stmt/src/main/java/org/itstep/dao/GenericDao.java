package org.itstep.dao;

import java.sql.SQLException;
import java.util.List;

// Data Access Object
public interface GenericDao<T, ID> {
    ID save(T data);
    T findById(ID id) throws SQLException;
    List<T> findAll();
}
