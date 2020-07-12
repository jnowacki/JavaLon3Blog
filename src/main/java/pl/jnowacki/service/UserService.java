package pl.jnowacki.service;

public interface UserService {
    boolean isUserValid(String login, String password);
    void createUser(String login, String password);
}
