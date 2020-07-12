package pl.jnowacki.dao;

import pl.jnowacki.model.User;

public interface UserDao {
    User getUser(String login);
    boolean createUser(String login, String hashedPassword, String token);
    boolean activateUser(String token);
}
