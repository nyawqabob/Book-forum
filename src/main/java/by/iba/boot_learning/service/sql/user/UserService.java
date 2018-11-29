package by.iba.boot_learning.service.sql.user;

import by.iba.boot_learning.entity.user.User;
import by.iba.boot_learning.service.sql.IService;

public interface UserService extends IService<User> {

    User findUserByEmail(String email);

}
