package org.itstep;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.itstep.data.User;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/singup")
public class SingUpServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("""
                                <!doctype html>
                                <html lang="en">
                                <head>
                                <meta charset="UTF-8">
                                             <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
                                             <meta http-equiv="X-UA-Compatible" content="ie=edge">
                                             <title>Sing Up</title>
                                </head>
                                <body>
                                    <form method='post'>
                                        <div>
                                            <lable>Login <input name='login' required></lable>
                                        </div>
                                        <div>
                                            <lable>Password <input name='password' type='password' required></lable>
                                        </div>
                                        <input type='submit'>
                                    </form>
                                </body>
                                </html>
                """);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login != null && password != null) {
            userDao.save(new User(login, password));
        }
        resp.sendRedirect("/");
    }
}
