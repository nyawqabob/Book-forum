package by.iba.boot_learning.dao.sql.book.mapper;

import by.iba.boot_learning.constants.database.book.BookFields;
import by.iba.boot_learning.entity.book.Book;
import by.iba.boot_learning.entity.book.type.BookType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BookMapper implements RowMapper<Book> {

    @Autowired
    private BookFields bookFields;

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setYearOfEdition(rs.getInt(bookFields.getYearOfEdition()));
        book.setId(rs.getLong(bookFields.getId()));
        book.setUserEmail(rs.getString(bookFields.getUserEmail()));
        book.setPrice(rs.getInt(bookFields.getPrice()));
        book.setEditionName(rs.getString(bookFields.getEditionName()));
        book.setName(rs.getString(bookFields.getName()));
        book.setDateWhenAdded(rs.getString(bookFields.getDateWhenAdded()));
        book.setBookType(BookType.valueOf(rs.getString(bookFields.getBookType())));
        return book;
    }
}
