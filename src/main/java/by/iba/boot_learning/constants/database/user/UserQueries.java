package by.iba.boot_learning.constants.database.user;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:user_queries.properties")
@ConfigurationProperties()
public class UserQueries {
    private String sqlInsertUser;
    private String sqlSelectAllUsers;
    private String sqlSelectUserById;
    private String sqlSelectAmountOfUsers;
    private  String sqlSelectUserByEmail;
    private  String sqlExistsUserByEmail;
    private  String sqlDeleteUserById;

    public String getSqlInsertUser() {
        return sqlInsertUser;
    }

    public void setSqlInsertUser(String sqlInsertUser) {
        this.sqlInsertUser = sqlInsertUser;
    }

    public String getSqlSelectAllUsers() {
        return sqlSelectAllUsers;
    }

    public void setSqlSelectAllUsers(String sqlSelectAllUsers) {
        this.sqlSelectAllUsers = sqlSelectAllUsers;
    }

    public String getSqlSelectUserById() {
        return sqlSelectUserById;
    }

    public void setSqlSelectUserById(String sqlSelectUserById) {
        this.sqlSelectUserById = sqlSelectUserById;
    }

    public String getSqlSelectAmountOfUsers() {
        return sqlSelectAmountOfUsers;
    }

    public void setSqlSelectAmountOfUsers(String sqlSelectAmountOfUsers) {
        this.sqlSelectAmountOfUsers = sqlSelectAmountOfUsers;
    }

    public String getSqlSelectUserByEmail() {
        return sqlSelectUserByEmail;
    }

    public void setSqlSelectUserByEmail(String sqlSelectUserByEmail) {
        this.sqlSelectUserByEmail = sqlSelectUserByEmail;
    }

    public String getSqlExistsUserByEmail() {
        return sqlExistsUserByEmail;
    }

    public void setSqlExistsUserByEmail(String sqlExistsUserByEmail) {
        this.sqlExistsUserByEmail = sqlExistsUserByEmail;
    }

    public String getSqlDeleteUserById() {
        return sqlDeleteUserById;
    }

    public void setSqlDeleteUserById(String sqlDeleteUserById) {
        this.sqlDeleteUserById = sqlDeleteUserById;
    }


}
