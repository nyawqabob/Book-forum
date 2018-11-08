package by.iba.boot_learning.dao.sql.user;

import by.iba.boot_learning.exceptions.DaoException;
import by.iba.boot_learning.dao.sql.Dao;
import by.iba.boot_learning.entity.user.User;

public interface UserDao extends Dao<User> {

    User findUserByEmail(String email) throws DaoException;

}
