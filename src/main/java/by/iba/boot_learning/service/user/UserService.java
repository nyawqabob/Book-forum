package by.iba.boot_learning.service.user;

import by.iba.boot_learning.entity.user.User;

public interface UserService {

    User findUserByEmail(String email);

}
