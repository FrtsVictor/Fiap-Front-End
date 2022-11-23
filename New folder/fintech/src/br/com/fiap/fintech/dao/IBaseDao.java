package br.com.fiap.fintech.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IBaseDao<T> {

    Optional<T> getById(Long id, boolean closeConnection) throws SQLException;

    List<T> getAll(boolean closeConnection);

    void save(T t, boolean closeConnection) throws SQLException;

    void updateById(Long id, T entity, boolean closeConnection);

    void deleteById(Long id, boolean closeConnection);
}
