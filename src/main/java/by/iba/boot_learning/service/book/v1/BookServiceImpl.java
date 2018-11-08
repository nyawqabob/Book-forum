package by.iba.boot_learning.service.book.v1;

import by.iba.boot_learning.constants.ConstantHelper;
import by.iba.boot_learning.dao.sql.book.v1.BookDaoImpl;
import by.iba.boot_learning.entity.book.Book;
import by.iba.boot_learning.service.Service;
import by.iba.boot_learning.service.book.BookService;
import by.iba.boot_learning.validator.name.NameValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@org.springframework.stereotype.Service
public class BookServiceImpl implements BookService, Service<Book> {

    public static final Logger LOGGER = LogManager.getLogger(BookServiceImpl.class);
    @Autowired
    private BookDaoImpl bookDao;

    @Override
    public void insert(Book book) {
        NameValidator nameValidator = new NameValidator();
        nameValidator.isValidNameByRegEx(book.getEditionName(), ConstantHelper.NAME_VALIDATOR_REG_EX);
        nameValidator.isValidNameByRegEx(book.getName(), ConstantHelper.NAME_VALIDATOR_REG_EX);
        bookDao.insert(book);

    }

    @Override
    public List<Book> loadAllObjects() {
        return bookDao.loadAllObjects();
    }

    @Override
    public Book findObjectById(long id) {
        return bookDao.findObjectById(id);

    }

    @Override
    public int getAmountOfAllObjects() {
        return bookDao.getAmountOfAllObjects();

    }
}
