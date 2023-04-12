package org.itstep;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.itstep.data.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/users")
public class UserDetailsServlet extends BaseServlet {

    private static User user = null;
    public static String TEMPLATE;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // 1. Загрузка TEMPLATE
        ServletContext servletContext = config.getServletContext();
        try (var in = servletContext.getResourceAsStream("/WEB-INF/template/users.html");
             var rdr = new BufferedReader(new InputStreamReader(in))) {
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = rdr.readLine()) != null) {
                stringBuilder.append(line);
            }
            TEMPLATE = stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(TEMPLATE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        PrintWriter writer = resp.getWriter();
        String userString;

        // 1 Получение параметра id
        String id = req.getParameter("id");
        System.out.println("id = " + id);

        if (id != null && !id.isBlank()) {
            try {
                user = userDao.findById(Integer.parseInt(id));
                System.out.println("user = " + user);
                if (user != null){
                    userString = user.toStringForHTML();
                    writer.printf(TEMPLATE, userString);
                }
                else {
                    userString = "User with id = " + id + " hadn't found";
                    writer.printf(TEMPLATE, userString);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }  else {
            userString = "";
            writer.printf(TEMPLATE, userString);
        }

        // 2. Печать


//        writer.printf(TEMPLATE, userString);

//        writer.printf(TEMPLATE, "Hello");

    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        resp.sendRedirect("/users");
//    }
}