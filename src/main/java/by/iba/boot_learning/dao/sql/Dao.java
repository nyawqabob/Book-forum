package by.iba.boot_learning.dao.sql;

import by.iba.boot_learning.exceptions.DaoException;

import java.util.List;

public interface Dao<T> {

    boolean insert(T object) throws DaoException;

    List<T> loadAllObjects();

    T findObjectById(long id) throws DaoException;

    int getAmountOfAllObjects() throws DaoException;

    void deleteObjectById(long id);
}
