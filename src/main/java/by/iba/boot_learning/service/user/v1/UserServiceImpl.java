package by.iba.boot_learning.service.user.v1;

import by.iba.boot_learning.constants.ConstantHelper;
import by.iba.boot_learning.dao.sql.user.v1.UserDaoImpl;
import by.iba.boot_learning.date.DateHandler;
import by.iba.boot_learning.entity.user.User;
import by.iba.boot_learning.service.Service;
import by.iba.boot_learning.service.user.UserService;
import by.iba.boot_learning.validator.date.DateValidator;
import by.iba.boot_learning.validator.mail.EmailValidator;
import by.iba.boot_learning.validator.name.NameValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class UserServiceImpl implements UserService, Service<User> {
    public static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDaoImpl userDao;

    @Override
    public void insert(User user) {
        String currentDay = DateHandler.getCurrentDay();
        DateValidator.isValid(user.getDateOfBirth());
        NameValidator nameValidator = new NameValidator();
        EmailValidator emailValidator = new EmailValidator();
        nameValidator.isValidNameByRegEx(user.getName(), ConstantHelper.NAME_VALIDATOR_REG_EX);
        nameValidator.isValidNameByRegEx(user.getCityOfBirth(), ConstantHelper.NAME_VALIDATOR_REG_EX);
        emailValidator.isValidEmailByRegEx(user.getEmail(), ConstantHelper.MAIL_VALIDATOR_REG_EX);
        user.setDateOfRegistration(currentDay);
        userDao.insert(user);
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
