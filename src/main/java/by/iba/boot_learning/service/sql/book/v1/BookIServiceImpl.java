package by.iba.boot_learning.service.sql.book.v1;

import by.iba.boot_learning.constants.ConstantHelper;
import by.iba.boot_learning.dao.sql.book.v1.BookDaoImpl;
import by.iba.boot_learning.date.DateHandler;
import by.iba.boot_learning.entity.InsertResult;
import by.iba.boot_learning.entity.book.Book;
import by.iba.boot_learning.service.sql.book.BookService;
import by.iba.boot_learning.validator.name.NameValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookIServiceImpl implements BookService {

    private static final Logger LOGGER = LogManager.getLogger(BookIServiceImpl.class);
    @Autowired
    private BookDaoImpl bookDao;

    @Autowired
    private NameValidator nameValidator;

    @Override
    public InsertResult insert(Book book) {
        InsertResult insertResult = new InsertResult("Book");
        String currentTime = DateHandler.getCurrentDay();
        nameValidator.isValidByRegEx(book.getEditionName(), ConstantHelper.NAME_VALIDATOR_REG_EX);
        nameValidator.isValidByRegEx(book.getName(), ConstantHelper.NAME_VALIDATOR_REG_EX);
        book.setDateWhenAdded(currentTime);
        boolean isAdded = bookDao.insert(book);
        insertResult.defineMessage(isAdded);
        return insertResult;
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
