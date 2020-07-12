package pl.jnowacki.dao;

import pl.jnowacki.model.User;

public interface UserDao {
    User getUser(String login);
    void createUser(String login, String hashedPassword);
}
