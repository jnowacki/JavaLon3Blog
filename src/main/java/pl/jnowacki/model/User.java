package pl.jnowacki.model;

import java.io.Serializable;

public class User implements Serializable {

    private Long id;
    private String login;
    private String password;
    private Boolean active;
    private String token;

    public User() {
    }

    public User(Long id, String login, String password, Boolean active, String token) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.active = active;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
