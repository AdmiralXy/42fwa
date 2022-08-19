package com.admiralxy.cinema.servlets;

import com.admiralxy.cinema.models.AuthLog;
import com.admiralxy.cinema.services.AuthService;
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
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/signIn")
public class LoginServlet extends HttpServlet {

    private AuthService authService;

    private static final List<AuthLog> auths = new ArrayList<>();

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        context.setAttribute("auths", auths);
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.authService = springContext.getBean(AuthService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/jsp/signIn.jsp");
        view.forward(req, resp);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (this.authService.setAuthenticated(
                req.getParameter("email"),
                req.getParameter("password"),
                req.getSession())
        ) {
            String remoteAddr = req.getRemoteAddr();
            if (remoteAddr.equals("0:0:0:0:0:0:0:1")) {
                InetAddress localIp = java.net.InetAddress.getLocalHost();
                remoteAddr = localIp.getHostAddress();
            }
            ((ArrayList<AuthLog>) req.getServletContext().getAttribute("auths"))
                    .add(new AuthLog(remoteAddr));
            resp.sendRedirect("/profile");
        } else {
            resp.sendRedirect("/signIn");
        }
    }
}
