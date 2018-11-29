package by.iba.boot_learning.service.sql.user.v1;

import by.iba.boot_learning.constants.ConstantHelper;
import by.iba.boot_learning.dao.sql.user.v1.UserDaoImpl;
import by.iba.boot_learning.date.DateHandler;
import by.iba.boot_learning.entity.InsertResult;
import by.iba.boot_learning.entity.user.User;
import by.iba.boot_learning.service.sql.user.UserService;
import by.iba.boot_learning.validator.date.DateValidator;
import by.iba.boot_learning.validator.mail.EmailValidator;
import by.iba.boot_learning.validator.name.NameValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserIServiceImpl implements UserService {
    public static final Logger LOGGER = LogManager.getLogger(UserIServiceImpl.class);

    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private NameValidator nameValidator;

    @Autowired
    private EmailValidator emailValidator;

    @Override
    public InsertResult insert(User user) {
        InsertResult insertResult = new InsertResult("User");
        String currentDay = DateHandler.getCurrentDay();
        DateValidator.isValid(user.getDateOfBirth());
        nameValidator.isValidByRegEx(user.getName(), ConstantHelper.NAME_VALIDATOR_REG_EX);
        nameValidator.isValidByRegEx(user.getCityOfBirth(), ConstantHelper.NAME_VALIDATOR_REG_EX);
        emailValidator.isValidByRegEx(user.getEmail(), ConstantHelper.MAIL_VALIDATOR_REG_EX);
        user.setDateOfRegistration(currentDay);
        boolean isAdded = userDao.insert(user);
        insertResult.defineMessage(isAdded);
        return insertResult;
    }

    @Override
    public List<User> loadAllObjects() {
        return userDao.loadAllObjects();
    }

    @Override
    public User findObjectById(long id) {
        return userDao.findObjectById(id);
    }

    @Override
    public int getAmountOfAllObjects() {
        return userDao.getAmountOfAllObjects();
    }

    @Override
    public User findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }
}
