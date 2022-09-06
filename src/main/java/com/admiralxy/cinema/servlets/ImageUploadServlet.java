package com.admiralxy.cinema.servlets;

import com.admiralxy.cinema.models.Image;
import com.admiralxy.cinema.models.User;
import com.admiralxy.cinema.services.AuthService;
import com.admiralxy.cinema.services.ImageService;
import org.springframework.context.ApplicationContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

@WebServlet("/images")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class ImageUploadServlet extends HttpServlet {

    private ImageService imageService;

    private AuthService authService;

    private String storagePath;

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.imageService = springContext.getBean(ImageService.class);
        this.authService = springContext.getBean(AuthService.class);
        this.storagePath = springContext.getEnvironment().getProperty("storage.path");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = this.authService.getAuthenticated(req.getSession());
        if (user == null) {
            resp.sendError(403);
        } else {
            try {
                Part filePart = req.getPart("image");

                String type = filePart.getContentType().split("/")[0];
                String extension = filePart.getContentType().split("/")[1];

                if (!type.equals("image")) {
                    throw new UnsupportedEncodingException();
                }

                String fileName = String.format("%s.%s", UUID.randomUUID(), extension);

                for (Part part : req.getParts()) {
                    part.write(storagePath + fileName);
                }

                Image image = new Image(
                        fileName,
                        filePart.getSubmittedFileName(),
                        filePart.getSize() + " B",
                        filePart.getContentType()
                );

                this.imageService.save(user, image);
                resp.sendRedirect(req.getContextPath() + "/profile");
            } catch (Exception e) {
                resp.sendError(400);
            }
        }
    }
}
