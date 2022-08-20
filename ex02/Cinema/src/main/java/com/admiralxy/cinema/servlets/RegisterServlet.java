package com.admiralxy.cinema.servlets;

import com.admiralxy.cinema.models.User;
import com.admiralxy.cinema.services.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signUp")
public class RegisterServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.userService = springContext.getBean(UserService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/jsp/signUp.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = new User(
                req.getParameter("first_name"),
                req.getParameter("last_name"),
                req.getParameter("phone_number"),
                req.getParameter("email"),
                req.getParameter("password"),
                null
        );
        try {
            this.userService.save(user);
            resp.sendRedirect(req.getContextPath() + "/signIn");
        } catch (DataAccessException e) {
            resp.sendRedirect(req.getContextPath() + "/signUp");
        }
    }
}
