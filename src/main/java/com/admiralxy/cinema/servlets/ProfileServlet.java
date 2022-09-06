package com.admiralxy.cinema.servlets;

import com.admiralxy.cinema.models.User;
import com.admiralxy.cinema.services.AuthService;
import com.admiralxy.cinema.services.ImageService;
import com.admiralxy.cinema.services.UserService;
import org.springframework.context.ApplicationContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private UserService userService;

    private AuthService authService;

    private ImageService imageService;

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.userService = springContext.getBean(UserService.class);
        this.authService = springContext.getBean(AuthService.class);
        this.imageService = springContext.getBean(ImageService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User authenticated = this.authService.getAuthenticated(req.getSession());
        req.setAttribute("user", this.userService.findByEmail(authenticated.getEmail()));
        req.setAttribute("images", this.imageService.findAll());
        RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/jsp/profile.jsp");
        view.forward(req, resp);
    }
}
