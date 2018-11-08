package by.iba.boot_learning.dao.sql.book.v1;

import by.iba.boot_learning.constants.database.book.BookFields;
import by.iba.boot_learning.constants.database.book.BookQueries;
import by.iba.boot_learning.constants.database.user.UserQueries;
import by.iba.boot_learning.dao.sql.book.mapper.BookMapper;
import by.iba.boot_learning.exceptions.DaoException;
import by.iba.boot_learning.dao.sql.book.BookDao;
import by.iba.boot_learning.entity.book.Book;
import by.iba.boot_learning.entity.book.type.BookType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
    public void insert(Book book) throws DaoException {
        String insert_book_sql = bookQueries.getSqlInsertBook();
        String exists_user_by_email_sql = userQueries.getSqlExistsUserByEmail();
        try {
            boolean isExistUserWithSameEmail = jdbcTemplate.queryForObject(exists_user_by_email_sql, new Object[]{book.getUserEmail()}, Boolean.class);
            if (!isExistUserWithSameEmail) {
                throw new DaoException("User with email " + book.getUserEmail() + " doesn't exists. ");
            }
            jdbcTemplate.update(insert_book_sql, new Object[]{book.getName(), book.getEditionName(), book.getYearOfEdition(), book.getBookType().toString(),
                    book.getPrice(), book.getDateWhenAdded(), book.getUserEmail()});
        } catch (DataAccessException ex) {
            LOGGER.error("Book was not added: " + ex.getMessage());
            throw new DaoException("Book was not added because of database error. Try again later. ", ex);
        }
    }

    @Override
    public List<Book> loadAllObjects() {
        String select_all_books_sql = bookQueries.getSqlSelectAllBooks();
        List<Book> books = new ArrayList<>();
        try {
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(select_all_books_sql);
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
    public Book findObjectById(long id) throws DaoException {
        Book book;
        try {
            String select_book_by_id_sql = bookQueries.getSqlSelectBookById();
            book = jdbcTemplate.queryForObject(select_book_by_id_sql, new Object[]{id}, bookMapper);
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
            String select_amount_of_books_sql = bookQueries.getSqlSelectAmountOfBooks();
            return jdbcTemplate.queryForObject(select_amount_of_books_sql, Integer.class);
        } catch (DataAccessException ex) {
            LOGGER.error("Cannot get amount of all books. Message: " + ex.getMessage());
            throw new DaoException("Cannot get amount of all books because of database error. Try again later. ", ex);
        }
    }

    @Override
    public void deleteObjectById(long id) {
        try {
            String delete_book_by_id_sql = bookQueries.getSqlDeleteBookById();
            jdbcTemplate.update(delete_book_by_id_sql, new Object[]{id});
        } catch (DataAccessException ex) {
            LOGGER.error("Cannot delete book with id " + id + ". Message: " + ex.getMessage());
            throw new DaoException("Cannot delete book with id " + id + " because of database error. Try again later. ", ex);
        }
    }
}

