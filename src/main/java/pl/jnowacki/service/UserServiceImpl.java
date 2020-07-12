package pl.jnowacki.service;

import org.apache.commons.lang3.RandomStringUtils;
import pl.jnowacki.dao.UserDao;
import pl.jnowacki.dao.UserDaoDBImpl;
import pl.jnowacki.model.User;
import pl.jnowacki.util.EmailUtil;
import pl.jnowacki.util.PasswordUtil;

public class UserServiceImpl implements UserService {

    private UserDao userDao = UserDaoDBImpl.getInstance();

    private static UserServiceImpl instance;

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }

        return instance;
    }

    private UserServiceImpl() {
    }

    @Override
    public boolean isUserValid(String login, String password) {

        User user = userDao.getUser(login);

        return user != null &&
                user.getLogin().equals(login) &&
                PasswordUtil.checkPassword(password, user.getPassword()) &&
                user.getActive();
    }

    @Override
    public void createUser(String login, String password, String path) {
        int tokenLength = 50;
        String token = RandomStringUtils.randomAlphanumeric(tokenLength);

        if(userDao.createUser(login, PasswordUtil.hashPassword(password), token)) {
            EmailUtil.sendActivationEmail(login, token, path);
        }
    }

    @Override
    public boolean activateUser(String token) {
        return userDao.activateUser(token);
    }
}
