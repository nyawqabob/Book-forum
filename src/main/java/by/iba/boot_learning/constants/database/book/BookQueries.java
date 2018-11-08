package by.iba.boot_learning.constants.database.book;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:book_queries.properties")
@ConfigurationProperties()
public class BookQueries {
    private String sqlInsertBook;
    private String sqlSelectAllBooks;
    private String sqlSelectBookById;
    private String sqlSelectAmountOfBooks;
    private String sqlDeleteBookById;

    public String getSqlInsertBook() {
        return sqlInsertBook;
    }

    public void setSqlInsertBook(String sqlInsertBook) {
        this.sqlInsertBook = sqlInsertBook;
    }

    public String getSqlSelectAllBooks() {
        return sqlSelectAllBooks;
    }

    public void setSqlSelectAllBooks(String sqlSelectAllBooks) {
        this.sqlSelectAllBooks = sqlSelectAllBooks;
    }

    public String getSqlSelectBookById() {
        return sqlSelectBookById;
    }

    public void setSqlSelectBookById(String sqlSelectBookById) {
        this.sqlSelectBookById = sqlSelectBookById;
    }

    public String getSqlSelectAmountOfBooks() {
        return sqlSelectAmountOfBooks;
    }

    public void setSqlSelectAmountOfBooks(String sqlSelectAmountOfBooks) {
        this.sqlSelectAmountOfBooks = sqlSelectAmountOfBooks;
    }

    public String getSqlDeleteBookById() {
        return sqlDeleteBookById;
    }

    public void setSqlDeleteBookById(String sqlDeleteBookById) {
        this.sqlDeleteBookById = sqlDeleteBookById;
    }
}
