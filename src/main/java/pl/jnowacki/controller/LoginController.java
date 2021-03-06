package pl.jnowacki.controller;

import org.apache.commons.lang3.StringUtils;
import pl.jnowacki.service.UserService;
import pl.jnowacki.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");

        boolean isActivated = false;

        if(StringUtils.isNotEmpty(token)) {
            isActivated = userService.activateUser(token);
        }

        if(isActivated) {
            resp.getWriter().println("Aktywowano twojego uzytkownika!");
        } else {
            resp.getWriter().println("Cos poszlo nie tak :(");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        switch (action) {
            case "login":
                loginUser(req, resp);
                break;
            case "logout":
                logoutUser(req, resp);
                break;
            case "register":
                registerUser(req, resp);
                break;
        }
    }

    private void registerUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        userService.createUser(username, password, req.getContextPath());

        resp.sendRedirect(req.getContextPath() + "/");
    }

    private void logoutUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath() + "/");
    }

    private void loginUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (userService.isUserValid(username, password)) {
            req.getSession().setAttribute("username", username);
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            resp.sendRedirect(req.getContextPath() + "/?hasError=true");
        }
    }
}
