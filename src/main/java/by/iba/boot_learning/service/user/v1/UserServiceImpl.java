package by.iba.boot_learning.service.user.v1;

import by.iba.boot_learning.constants.reg_ex.RegExs;
import by.iba.boot_learning.dao.sql.user.v1.UserDaoImpl;
import by.iba.boot_learning.date.DateHandler;
import by.iba.boot_learning.entity.user.User;
import by.iba.boot_learning.service.AbstractService;
import by.iba.boot_learning.service.user.UserService;
import by.iba.boot_learning.validator.AbstractValidator;
import by.iba.boot_learning.validator.date.DateValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, AbstractService<User> {
    public static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDaoImpl userDao;

    @Override
    public void insert(User user) {
        String currentDay = DateHandler.getCurrentDay();
        DateValidator.isValid(user.getDateOfBirth());
        AbstractValidator.isValidByRegEx(user.getEmail(), RegExs.MAIL_VALIDATOR_REG_EX);
        AbstractValidator.isValidByRegEx(user.getName(), RegExs.NAME_VALIDATOR_REG_EX);
        AbstractValidator.isValidByRegEx(user.getCityOfBirth(), RegExs.NAME_VALIDATOR_REG_EX);
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
