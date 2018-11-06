package by.iba.boot_learning.dao.sql;

import by.iba.boot_learning.dao.exception.DaoException;

import java.util.List;

public interface AbstractDao<T> {

    void insert(T object) throws DaoException;

    List<T> loadAllObjects();

    T findObjectById(long id) throws DaoException;

    int getAmountOfAllObjects() throws DaoException;
}
