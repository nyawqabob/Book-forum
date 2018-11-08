package by.iba.boot_learning.dao.sql.user.v1;

import by.iba.boot_learning.constants.database.user.UserFields;
import by.iba.boot_learning.constants.database.user.UserQueries;
import by.iba.boot_learning.dao.exception.DaoException;
import by.iba.boot_learning.dao.sql.user.UserDao;
import by.iba.boot_learning.entity.user.User;
import by.iba.boot_learning.entity.user.status.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserQueries userQueries;

    @Autowired
    private UserFields userFields;

    @PostConstruct
    public void initialize() {
        setDataSource(dataSource);

    }

    @Override
    public void insert(User user) throws DaoException {
        String insert_user_sql = userQueries.getSqlInsertUser();
        String exists_user_by_email_sql = userQueries.getSqlExistsUserByEmail();
        try {
            boolean isExistUserWithSameEmail = getJdbcTemplate().queryForObject(exists_user_by_email_sql, new Object[]{user.getEmail()}, Boolean.class);
            if (isExistUserWithSameEmail) {
                throw new DaoException("User with email " + user.getEmail() + " exists. ");
            }
            getJdbcTemplate().update(insert_user_sql, new Object[]{user.getName(), user.getAge(), user.getEmail(), user.getCityOfBirth(),
                    user.getDateOfBirth(), user.getDateOfRegistration(), user.getStatus().toString()});
        } catch (DataAccessException exception) {
            LOGGER.error("User was not added: " + exception.getMessage());
            throw new DaoException("User was not added because of database error. Try again later. ", exception);
        }
    }

    @Override
    public List<User> loadAllObjects() {
        String select_all_users_sql = userQueries.getSqlSelectAllUsers();
        List<User> users = new ArrayList<>();
        try {
            List<Map<String, Object>> rows = getJdbcTemplate().queryForList(select_all_users_sql);
            for (Map<String, Object> row : rows) {
                User user = new User();
                user.setStatus(Status.valueOf(row.get(userFields.getStatus()).toString()));
                user.setDateOfRegistration(row.get(userFields.getDateOfRegistration()).toString());
                user.setDateOfBirth(row.get(userFields.getDateOfBirth()).toString());
                user.setName(row.get(userFields.getName()).toString());
                user.setCityOfBirth(row.get(userFields.getCityOfBirth()).toString());
                user.setEmail(row.get(user.getEmail()).toString());
                user.setAge(Integer.parseInt(row.get(user.getAge()).toString()));
                users.add(user);
            }
        } catch (DataAccessException ex) {
            LOGGER.error("Error while trying to load all books: " + ex.getMessage());
            throw new DaoException("Cannot load all books because of database error. Try again later. ", ex);
        }
        return users;


    }

    @Override
    public User findObjectById(long id) throws DaoException {
        User user;
        try {
            String select_user_by_id_sql = userQueries.getSqlSelectUserById();
            user = (User) getJdbcTemplate().queryForObject(select_user_by_id_sql, new Object[]{id}, new BeanPropertyRowMapper(User.class));

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
            String select_amount_of_users_sql = userQueries.getSqlSelectAmountOfUsers();
            return getJdbcTemplate().queryForObject(select_amount_of_users_sql, Integer.class);
        } catch (DataAccessException ex) {
            LOGGER.error("Cannot get amount of all users: " + ex.getMessage());
            throw new DaoException("Cannot get amount of all books users of database error. Try again later. ", ex);
        }
    }

    @Override
    public void deleteObjectById(long id) {
        try {
            String delete_user_by_id_sql = userQueries.getSqlDeleteUserById();
            getJdbcTemplate().update(delete_user_by_id_sql, new Object[]{id});
        } catch (DataAccessException ex) {
            LOGGER.error("Cannot delete book with id " + id + ". Message: " + ex.getMessage());
            throw new DaoException("Cannot delete book with id " + id + " because of database error. Try again later. ", ex);
        }

    }

    @Override
    public User findUserByEmail(String email) throws DaoException {
        User user;
        try {
            String sql_select_user_by_email = userQueries.getSqlSelectUserByEmail();
            user = (User) getJdbcTemplate().queryForObject(sql_select_user_by_email, new Object[]{email}, new BeanPropertyRowMapper(User.class));
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
