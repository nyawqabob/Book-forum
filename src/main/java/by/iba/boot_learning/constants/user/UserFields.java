package by.iba.boot_learning.constants.user;

import by.iba.boot_learning.properties.PropertiesHandler;

public class UserFields {
    private static final String USER_FIELDS_PROPERTY_NAME = "database.user_fields";
    public static final String AGE = PropertiesHandler.getProperty("age", USER_FIELDS_PROPERTY_NAME);
    public static final String CITY_OF_BIRTH = PropertiesHandler.getProperty("cityOfBirth", USER_FIELDS_PROPERTY_NAME);
    public static final String STATUS = PropertiesHandler.getProperty("status", USER_FIELDS_PROPERTY_NAME);
    public static final String NAME = PropertiesHandler.getProperty("name", USER_FIELDS_PROPERTY_NAME);
    public static final String DATE_OF_BIRTH = PropertiesHandler.getProperty("dateOfBirth", USER_FIELDS_PROPERTY_NAME);
    public static final String EMAIL = PropertiesHandler.getProperty("email", USER_FIELDS_PROPERTY_NAME);
    public static final String DATE_OF_REGISTRATION = PropertiesHandler.getProperty("dateOfRegistration", USER_FIELDS_PROPERTY_NAME);

}
