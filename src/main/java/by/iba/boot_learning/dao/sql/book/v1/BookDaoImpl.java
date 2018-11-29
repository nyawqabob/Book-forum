package by.iba.boot_learning.dao.sql.book.v1;

import by.iba.boot_learning.constants.database.book.BookFields;
import by.iba.boot_learning.constants.database.book.BookQueries;
import by.iba.boot_learning.constants.database.user.UserQueries;
import by.iba.boot_learning.dao.sql.book.BookDao;
import by.iba.boot_learning.dao.sql.book.mapper.BookMapper;
import by.iba.boot_learning.entity.book.Book;
import by.iba.boot_learning.entity.book.type.BookType;
import by.iba.boot_learning.exceptions.DaoException;
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
public class BookDaoImpl implements BookDao {

    private static final Logger LOGGER = LogManager.getLogger(BookDaoImpl.class);

    @Autowired
    private BookFields bookFields;

    @Autowired
    private BookQueries bookQueries;

    @Autowired
    private UserQueries userQueries;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public boolean insert(Book book) throws DaoException {
        String sqlInsertBook = bookQueries.getSqlInsertBook();
        String sqlExistsUserByEmail = userQueries.getSqlExistsUserByEmail();
        boolean result;
        try {
            boolean isExistUserWithSameEmail = jdbcTemplate.queryForObject(sqlExistsUserByEmail, new Object[]{book.getUserEmail()}, Boolean.class);
            if (!isExistUserWithSameEmail) {
                throw new DaoException("User with email " + book.getUserEmail() + " doesn't exists. ");
            }
            int intResult = jdbcTemplate.update(sqlInsertBook, new Object[]{book.getName(), book.getEditionName(), book.getYearOfEdition(), book.getBookType().toString(),
                    book.getPrice(), book.getDateWhenAdded(), book.getUserEmail()});
            result = intResult == 1;
        } catch (DataAccessException ex) {
            LOGGER.error("Book was not added: " + ex.getMessage());
            throw new DaoException("Book was not added because of database error. Try again later. ", ex);
        }
        return result;
    }

    @Override
    public List<Book> loadAllObjects() {
        String sqlSelectAllBooks = bookQueries.getSqlSelectAllBooks();
        List<Book> books = new ArrayList<>();
        try {
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlSelectAllBooks);
            for (Map<String, Object> row : rows) {
                Book book = new Book();
                book.setId(Long.parseLong(row.get(bookFields.getId()).toString()));
                book.setBookType(BookType.valueOf(row.get(bookFields.getBookType()).toString()));
                book.setDateWhenAdded(row.get(bookFields.getDateWhenAdded()).toString());
                book.setEditionName(row.get(bookFields.getEditionName()).toString());
                book.setName(row.get(bookFields.getName()).toString());
                book.setPrice(Integer.parseInt(row.get(bookFields.getPrice()).toString()));
                book.setUserEmail(row.get(bookFields.getUserEmail()).toString());
                book.setYearOfEdition(Integer.parseInt(row.get(bookFields.getYearOfEdition()).toString()));
                books.add(book);
            }
        } catch (DataAccessException ex) {
            LOGGER.error("Error while trying to load all books: " + ex.getMessage());
            throw new DaoException("Cannot load all books because of database error. Try again later. ", ex);
        }
        return books;
    }

    @Override
    public List<Book> findBooksByUserEmail(String email) {
        String sqlSelectBooksByUserEmail = bookQueries.getSqlSelectBooksByUserEmail();
        List<Book> books = new ArrayList<>();
        try {
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlSelectBooksByUserEmail, email);
            for (Map<String, Object> row : rows) {
                Book book = new Book();
                book.setId(Long.parseLong(row.get(bookFields.getId()).toString()));
                book.setBookType(BookType.valueOf(row.get(bookFields.getBookType()).toString()));
                book.setDateWhenAdded(row.get(bookFields.getDateWhenAdded()).toString());
                book.setEditionName(row.get(bookFields.getEditionName()).toString());
                book.setName(row.get(bookFields.getName()).toString());
                book.setPrice(Integer.parseInt(row.get(bookFields.getPrice()).toString()));
                book.setUserEmail(row.get(bookFields.getUserEmail()).toString());
                book.setYearOfEdition(Integer.parseInt(row.get(bookFields.getYearOfEdition()).toString()));
                books.add(book);
            }
        } catch (DataAccessException ex) {
            LOGGER.error("Error while trying to load books of user: " + ex.getMessage());
            throw new DaoException("Cannot load books of user because of database error. Try again later. ", ex);
        }
        return books;
    }

    @Override
    public Book findObjectById(long id) throws DaoException {
        Book book;
        try {
            String sqlSelectBookById = bookQueries.getSqlSelectBookById();
            book = jdbcTemplate.queryForObject(sqlSelectBookById, new Object[]{id}, bookMapper);
        } catch (EmptyResultDataAccessException ex) {
            LOGGER.error("Book with id" + id + " was not found: " + ex.getMessage());
            throw new DaoException("Book with id" + id + " was not found.");
        } catch (DataAccessException ex) {
            LOGGER.error("Book with id" + id + " was not found: " + ex.getMessage());
            throw new DaoException("Cannot load book with id " + id + " because of database error. Try again later.", ex);

        }
        return book;
    }

    @Override
    public int getAmountOfAllObjects() throws DaoException {
        try {
            String sqlSelectAmountOfBooks = bookQueries.getSqlSelectAmountOfBooks();
            return jdbcTemplate.queryForObject(sqlSelectAmountOfBooks, Integer.class);
        } catch (DataAccessException ex) {
            LOGGER.error("Cannot get amount of all books. Message: " + ex.getMessage());
            throw new DaoException("Cannot get amount of all books because of database error. Try again later. ", ex);
        }
    }

    @Override
    public void deleteObjectById(long id) {
        try {
            String sqlDeleteBookById = bookQueries.getSqlDeleteBookById();
            jdbcTemplate.update(sqlDeleteBookById, new Object[]{id});
        } catch (DataAccessException ex) {
            LOGGER.error("Cannot delete book with id " + id + ". Message: " + ex.getMessage());
            throw new DaoException("Cannot delete book with id " + id + " because of database error. Try again later. ", ex);
        }
    }
}

