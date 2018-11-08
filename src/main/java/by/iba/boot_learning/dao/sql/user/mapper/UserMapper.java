package by.iba.boot_learning.dao.sql.user.mapper;

import by.iba.boot_learning.constants.database.user.UserFields;
import by.iba.boot_learning.entity.user.User;
import by.iba.boot_learning.entity.user.status.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserMapper implements RowMapper<User> {

    @Autowired
    private UserFields userFields;

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setAge(rs.getInt(userFields.getAge()));
        user.setEmail(rs.getString(userFields.getEmail()));
        user.setCityOfBirth(rs.getString(userFields.getCityOfBirth()));
        user.setDateOfRegistration(rs.getString(userFields.getDateOfRegistration()));
        user.setDateOfBirth(rs.getString(userFields.getDateOfBirth()));
        user.setStatus(Status.valueOf(rs.getString(userFields.getStatus())));
        user.setId(rs.getLong(userFields.getId()));
        user.setName(rs.getString(userFields.getName()));
        return user;
    }
}
