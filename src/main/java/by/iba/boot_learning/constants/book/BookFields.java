package by.iba.boot_learning.constants.book;

import by.iba.boot_learning.properties.PropertiesHandler;

public class BookFields {
    private static final String BOOK_FIELDS_PROPERTY_NAME = "database.book_fields";
    public static final String BOOK_TYPE = PropertiesHandler.getProperty("bookType", BOOK_FIELDS_PROPERTY_NAME);
    public static final String DATE_WHEN_ADDED = PropertiesHandler.getProperty("dateWhenAdded", BOOK_FIELDS_PROPERTY_NAME);
    public static final String EDITION_NAME = PropertiesHandler.getProperty("editionName", BOOK_FIELDS_PROPERTY_NAME);
    public static final String NAME = PropertiesHandler.getProperty("name", BOOK_FIELDS_PROPERTY_NAME);
    public static final String PRICE = PropertiesHandler.getProperty("price", BOOK_FIELDS_PROPERTY_NAME);
    public static final String USER_EMAIL = PropertiesHandler.getProperty("userEmail", BOOK_FIELDS_PROPERTY_NAME);
    public static final String YEAR_OF_EDITION = PropertiesHandler.getProperty("yearOfEdition", BOOK_FIELDS_PROPERTY_NAME);
}
