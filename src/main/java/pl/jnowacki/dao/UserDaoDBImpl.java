package pl.jnowacki.dao;

import pl.jnowacki.model.User;
import pl.jnowacki.util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoDBImpl implements UserDao {

    private static UserDaoDBImpl instance;

    public static UserDaoDBImpl getInstance(){
        if (instance == null) {
            instance = new UserDaoDBImpl();
        }

        return instance;
    }

    private UserDaoDBImpl() {
    }

    @Override
    public User getUser(String userLogin) {

        String selectSQL = "SELECT * FROM users WHERE login = ?";

        try (Connection dbConnection = DbConnection.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(selectSQL)) {

            preparedStatement.setString(1, userLogin);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Long id = rs.getLong("id");
                String login = rs.getString("login");
                String pwd = rs.getString("password");
                boolean active = rs.getBoolean("active");
                String token = rs.getString("token");

                return new User(id, login, pwd, active, token);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public boolean createUser(String login, String hashedPassword, String token) {
        String selectSQL = "INSERT INTO users (login, password, token) VALUE (?, ?, ?)";

        try (Connection dbConnection = DbConnection.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(selectSQL)) {

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, hashedPassword);
            preparedStatement.setString(3, token);

            return preparedStatement.executeUpdate() == 1;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean activateUser(String token) {

        String selectSQL = "UPDATE users SET active = true WHERE token = ? AND active = false";

        try (Connection dbConnection = DbConnection.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(selectSQL)) {

            preparedStatement.setString(1, token);

            return preparedStatement.executeUpdate() == 1;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }
}
