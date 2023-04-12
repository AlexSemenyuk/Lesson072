package org.itstep;

import org.itstep.dao.UserDao;
import org.itstep.dao.impl.UserDaoImpl;
import org.itstep.data.User;

public class Application {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl("jdbc:mariadb://localhost/blog",
                "blogApp", "blogPass");
        userDao.save(new User("admin", "qwerty"));
    }
}
