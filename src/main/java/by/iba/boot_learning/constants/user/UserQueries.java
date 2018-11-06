package by.iba.boot_learning.constants.user;

import by.iba.boot_learning.properties.PropertiesHandler;

public class UserQueries {
    private static final String USER_QUERIES_PROPERTY_NAME = "database.user_queries";
    public static final String SQL_INSERT_USER = PropertiesHandler.getProperty("SQL_INSERT_USER", USER_QUERIES_PROPERTY_NAME);
    public static final String SQL_SELECT_ALL_USERS = PropertiesHandler.getProperty("SQL_SELECT_ALL_USERS", USER_QUERIES_PROPERTY_NAME);
    public static final String SQL_SELECT_USER_BY_ID = PropertiesHandler.getProperty("SQL_SELECT_USER_BY_ID", USER_QUERIES_PROPERTY_NAME);
    public static final String SQL_SELECT_AMOUNT_OF_USERS = PropertiesHandler.getProperty("SQL_SELECT_AMOUNT_OF_USERS", USER_QUERIES_PROPERTY_NAME);
    public static final String SQL_SELECT_USER_BY_EMAIL = PropertiesHandler.getProperty("SQL_SELECT_USER_BY_EMAIL", USER_QUERIES_PROPERTY_NAME);
    public static final String SQL_EXISTS_USER_BY_EMAIL = PropertiesHandler.getProperty("SQL_EXISTS_USER_BY_EMAIL", USER_QUERIES_PROPERTY_NAME);
}
