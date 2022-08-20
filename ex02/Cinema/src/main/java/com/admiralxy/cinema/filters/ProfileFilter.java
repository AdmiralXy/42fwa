package com.admiralxy.cinema.filters;

import com.admiralxy.cinema.models.User;
import com.admiralxy.cinema.services.AuthService;
import org.springframework.context.ApplicationContext;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/profile")
public class ProfileFilter implements Filter {

    private AuthService authService;

    @Override
    public void init(FilterConfig config) {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.authService = springContext.getBean(AuthService.class);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        User authenticated = this.authService.getAuthenticated(request.getSession());
        if (authenticated == null) {
            response.sendError(403);
        } else {
            chain.doFilter(req, resp);
        }
    }
}
