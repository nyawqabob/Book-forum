package by.iba.boot_learning.dao.sql.user;

import by.iba.boot_learning.dao.exception.DaoException;
import by.iba.boot_learning.entity.user.User;

public interface UserDao {

    User findUserByEmail(String email) throws DaoException;

}
