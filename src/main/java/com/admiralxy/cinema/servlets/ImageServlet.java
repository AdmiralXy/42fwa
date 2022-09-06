package com.admiralxy.cinema.servlets;

import org.springframework.context.ApplicationContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/cinema/images/*")
public class ImageServlet extends HttpServlet {

    private String storagePath;

    @Override
    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.storagePath = springContext.getEnvironment().getProperty("storage.path");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String pathImage = req.getPathInfo().split("/")[1];
            ServletContext context = req.getServletContext();
            String filename = String.format("%s/%s", storagePath, pathImage);
            String mime = context.getMimeType(filename);
            if (mime == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            resp.setContentType(mime);
            File file = new File(filename);
            resp.setContentLength((int)file.length());

            FileInputStream in = new FileInputStream(file);
            OutputStream out = resp.getOutputStream();

            byte[] buf = new byte[1024];
            int count;
            while ((count = in.read(buf)) >= 0) {
                out.write(buf, 0, count);
            }
            out.close();
            in.close();
        } catch (IOException e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
