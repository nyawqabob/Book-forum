package by.iba.boot_learning.dao.sql.user.v1;

import by.iba.boot_learning.constants.database.user.UserFields;
import by.iba.boot_learning.constants.database.user.UserQueries;
import by.iba.boot_learning.dao.sql.book.v1.BookDaoImpl;
import by.iba.boot_learning.dao.sql.user.mapper.UserMapper;
import by.iba.boot_learning.entity.book.Book;
import by.iba.boot_learning.exceptions.DaoException;
import by.iba.boot_learning.dao.sql.user.UserDao;
import by.iba.boot_learning.entity.user.User;
import by.iba.boot_learning.entity.user.status.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

    @Autowired
    private UserQueries userQueries;

    @Autowired
    private UserFields userFields;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BookDaoImpl bookDao;

    @Override
    public boolean insert(User user) throws DaoException {
        String sqlInsertUser = userQueries.getSqlInsertUser();
        String sqlExistsUserByEmail = userQueries.getSqlExistsUserByEmail();
        boolean result;
        try {
            boolean isExistUserWithSameEmail = jdbcTemplate.queryForObject(sqlExistsUserByEmail, new Object[]{user.getEmail()}, Boolean.class);
            if (isExistUserWithSameEmail) {
                throw new DaoException("User with email " + user.getEmail() + " exists. ");
            }
            int intResult = jdbcTemplate.update(sqlInsertUser, new Object[]{user.getName(), user.getAge(), user.getEmail(), user.getCityOfBirth(),
                    user.getDateOfBirth(), user.getDateOfRegistration(), user.getStatus().toString()});
            result = intResult == 1;
        } catch (DataAccessException exception) {
            LOGGER.error("User was not added: " + exception.getMessage());
            throw new DaoException("User was not added because of database error. Try again later. ", exception);
        }
        return result;
    }

    @Override
    public List<User> loadAllObjects() {
        String sqlSelectAllUsers = userQueries.getSqlSelectAllUsers();
        List<User> users = new ArrayList<>();
        try {
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlSelectAllUsers);
            for (Map<String, Object> row : rows) {
                String email = row.get(userFields.getEmail()).toString();
                List<Book> userBooks = bookDao.findBooksByUserEmail(email);
                User user = new User();
                user.setId(Long.parseLong(row.get(userFields.getId()).toString()));
                user.setStatus(Status.valueOf(row.get(userFields.getStatus()).toString()));
                user.setDateOfRegistration(row.get(userFields.getDateOfRegistration()).toString());
                user.setDateOfBirth(row.get(userFields.getDateOfBirth()).toString());
                user.setName(row.get(userFields.getName()).toString());
                user.setCityOfBirth(row.get(userFields.getCityOfBirth()).toString());
                user.setEmail(row.get(userFields.getEmail()).toString());
                user.setAge(Integer.parseInt(row.get(userFields.getAge()).toString()));
                user.setBooks(userBooks);
                users.add(user);
            }
        } catch (DataAccessException ex) {
            LOGGER.error("Error while trying to load all users: " + ex.getMessage());
            throw new DaoException("Cannot load all users because of database error. Try again later. ", ex);
        }
        return users;


    }

    @Override
    public User findObjectById(long id) throws DaoException {
        User user;
        try {
            String sqlSelectUserById = userQueries.getSqlSelectUserById();
            user = jdbcTemplate.queryForObject(sqlSelectUserById, new Object[]{id}, userMapper);
            List<Book> userBooks = bookDao.findBooksByUserEmail(user.getEmail());
            user.setBooks(userBooks);
        } catch (EmptyResultDataAccessException ex) {
            LOGGER.error("User with id" + id + " was not found: " + ex.getMessage());
            throw new DaoException("User with " + id + " was not found. ", ex);
        } catch (DataAccessException ex) {
            LOGGER.error("User with id" + id + " was not found: " + ex.getMessage());
            throw new DaoException("Cannot load user with id " + id + " because of database error. Try again later. ", ex);
        }
        return user;
    }

    @Override
    public int getAmountOfAllObjects() throws DaoException {
        try {
            String sqlSelectAmountOfUsers = userQueries.getSqlSelectAmountOfUsers();
            return jdbcTemplate.queryForObject(sqlSelectAmountOfUsers, Integer.class);
        } catch (DataAccessException ex) {
            LOGGER.error("Cannot get amount of all users: " + ex.getMessage());
            throw new DaoException("Cannot get amount of all books users of database error. Try again later. ", ex);
        }
    }

    @Override
    public void deleteObjectById(long id) {
        try {
            String sqlDeleteUserById = userQueries.getSqlDeleteUserById();
            jdbcTemplate.update(sqlDeleteUserById, new Object[]{id});
        } catch (DataAccessException ex) {
            LOGGER.error("Cannot delete book with id " + id + ". Message: " + ex.getMessage());
            throw new DaoException("Cannot delete book with id " + id + " because of database error. Try again later. ", ex);
        }

    }

    @Override
    public User findUserByEmail(String email) throws DaoException {
        User user;
        try {
            String sqlSelectUserByEmail = userQueries.getSqlSelectUserByEmail();
            user = jdbcTemplate.queryForObject(sqlSelectUserByEmail, new Object[]{email}, userMapper);
            List<Book> userBooks = bookDao.findBooksByUserEmail(email);
            user.setBooks(userBooks);
        } catch (EmptyResultDataAccessException ex) {
            LOGGER.error("User with email" + email + " was not found: " + ex.getMessage());
            throw new DaoException("User with email " + email + " was not found. ", ex);
        } catch (DataAccessException ex) {
            LOGGER.error("User with email" + email + " was not found: " + ex.getMessage());
            throw new DaoException("Cannot load user with email " + email + " because of database error. Try again later. ", ex);
        }
        return user;
    }
}
