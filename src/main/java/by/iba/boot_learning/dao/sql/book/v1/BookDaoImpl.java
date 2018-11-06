package by.iba.boot_learning.dao.sql.book.v1;

import by.iba.boot_learning.constants.book.BookFields;
import by.iba.boot_learning.constants.book.BookQueries;
import by.iba.boot_learning.constants.user.UserQueries;
import by.iba.boot_learning.dao.sql.AbstractDao;
import by.iba.boot_learning.dao.sql.book.BookDao;
import by.iba.boot_learning.dao.exception.DaoException;
import by.iba.boot_learning.entity.book.Book;
import by.iba.boot_learning.entity.book.type.BookType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public class BookDaoImpl extends JdbcDaoSupport implements BookDao, AbstractDao<Book> {

    public static final Logger LOGGER = LogManager.getLogger(BookDaoImpl.class);

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void initialize() {
        setDataSource(dataSource);

    }

    @Override
    public void insert(Book book) throws DaoException {
        String insert_book_sql = BookQueries.SQL_INSERT_BOOK;
        String exists_user_by_email_sql = UserQueries.SQL_EXISTS_USER_BY_EMAIL;
        try {
            boolean isExistUserWithSameEmail = getJdbcTemplate().queryForObject(exists_user_by_email_sql, new Object[]{book.getUserEmail()}, Boolean.class);
            if (!isExistUserWithSameEmail) {
                throw new DaoException("User with email " + book.getUserEmail() + "doesn't exists. ");
            }
            getJdbcTemplate().update(insert_book_sql, new Object[]{book.getName(), book.getEditionName(), book.getYearOfEdition(), book.getBookType().toString(),
                    book.getPrice(), book.getDateWhenAdded(), book.getUserEmail()});
        } catch (DataAccessException ex) {
            LOGGER.error("Book was not added: " + ex.getMessage());
            throw new DaoException("Book was not added because of database error. Try again later. ", ex);
        }
    }

    @Override
    public List<Book> loadAllObjects() {
        String select_all_books_sql = BookQueries.SQL_SELECT_ALL_BOOKS;
        List<Book> books = new ArrayList<>();
        try {
            List<Map<String, Object>> rows = getJdbcTemplate().queryForList(select_all_books_sql);
            for (Map<String, Object> row : rows) {
                Book book = new Book();
                book.setBookType(BookType.valueOf(row.get(BookFields.BOOK_TYPE).toString()));
                book.setDateWhenAdded(row.get(BookFields.DATE_WHEN_ADDED).toString());
                book.setEditionName(row.get(BookFields.EDITION_NAME).toString());
                book.setName(row.get(BookFields.NAME).toString());
                book.setPrice(Integer.parseInt(row.get(BookFields.PRICE).toString()));
                book.setUserEmail(row.get(BookFields.USER_EMAIL).toString());
                book.setYearOfEdition(Integer.parseInt(row.get(BookFields.YEAR_OF_EDITION).toString()));
                books.add(book);
            }
        } catch (DataAccessException ex) {
            LOGGER.error("Error while trying to load all books: " + ex.getMessage());
            throw new DaoException("Cannot load all books because of database error. Try again later. ", ex);
        }
        return books;

    }

    @Override
    public Book findObjectById(long id) throws DaoException {
        Book book;
        try {
            String select_book_by_id_sql = BookQueries.SQL_SELECT_BOOK_BY_ID;
            book = (Book) getJdbcTemplate().queryForObject(select_book_by_id_sql, new Object[]{id}, new BeanPropertyRowMapper(Book.class));
            if (book == null) {
                throw new DaoException("Book with id" + id + " was not found.");
            }
        } catch (DataAccessException ex) {
            LOGGER.error("Book with id" + id + " was not found: " + ex.getMessage());
            throw new DaoException("Cannot load book with id "+id+" because of database error. Try again later.", ex);
        }
        return book;
    }

    @Override
    public int getAmountOfAllObjects() throws DaoException {
        try {
            String select_amount_of_books_sql = BookQueries.SQL_SELECT_AMOUNT_OF_BOOKS;
            return getJdbcTemplate().queryForObject(select_amount_of_books_sql, Integer.class);
        } catch (DataAccessException ex) {
            LOGGER.error("Cannot get amount of all books. Message: " + ex.getMessage());
            throw new DaoException("Cannot get amount of all books because of database error. Try again later. ", ex);
        }
    }
}
