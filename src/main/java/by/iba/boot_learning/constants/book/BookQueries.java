package by.iba.boot_learning.constants.book;

import by.iba.boot_learning.properties.PropertiesHandler;

public class BookQueries {
    private static final String DATABASE_PROPERTIES_NAME = "database.book_queries";
    public static final String SQL_INSERT_BOOK = PropertiesHandler.getProperty("SQL_INSERT_BOOK", DATABASE_PROPERTIES_NAME);
    public static final String SQL_SELECT_ALL_BOOKS = PropertiesHandler.getProperty("SQL_SELECT_ALL_BOOKS", DATABASE_PROPERTIES_NAME);
    public static final String SQL_SELECT_BOOK_BY_ID = PropertiesHandler.getProperty("SQL_SELECT_BOOK_BY_ID", DATABASE_PROPERTIES_NAME);
    public static final String SQL_SELECT_AMOUNT_OF_BOOKS = PropertiesHandler.getProperty("SQL_SELECT_AMOUNT_OF_BOOKS", DATABASE_PROPERTIES_NAME);
}
