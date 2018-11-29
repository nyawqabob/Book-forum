package by.iba.boot_learning.dao.sql.book;

import by.iba.boot_learning.dao.sql.Dao;
import by.iba.boot_learning.entity.book.Book;

import java.util.List;

public interface BookDao extends Dao<Book> {
    List<Book> findBooksByUserEmail(String email);
}
