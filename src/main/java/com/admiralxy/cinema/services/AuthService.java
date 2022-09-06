package com.admiralxy.cinema.services;

import com.admiralxy.cinema.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpSession;

@Component
public class AuthService {
    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    public AuthService(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public User getAuthenticated(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            User userFromDb = this.userService.findByEmail(user.getEmail());
            if (userFromDb != null) {
                return user;
            }
        }
        this.unsetAuthenticated(session);
        return null;
    }

    public boolean setAuthenticated(String email, String password, HttpSession session) {
        User user = this.userService.findByEmail(email);
        if (user != null) {
            boolean isPasswordMatches = passwordEncoder.matches(password, user.getPassword());
            if (isPasswordMatches) {
                session.setAttribute("user", user);
                return true;
            }
        }
        return false;
    }

    public void unsetAuthenticated(HttpSession session) {
        session.removeAttribute("user");
    }
}
