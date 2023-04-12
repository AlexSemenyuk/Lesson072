package org.itstep;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.itstep.data.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/")
public class HomeServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();

        List<User> allUsers = userDao.findAll();
        writer.println("""
                <!document html>
                <html lang='en'>
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
                    <meta http-equiv="X-UA-Compatible" content="ie=edge">
                    <title>Document</title>
                </head>
                <body>
                    <nav>
                    <ul>
                        <li><a href='/login'>Login</a></li>
                        <li><a href='/singup'>Sing Up</a></li>
                        <li><a href='users'>Find user by id</a></li>
                    </ul>
                    </nav>         
                    <main>
                        <table>
                            <thead>
                            <tr>
                                <th>Id</th>
                                <th>Login</th>
                                <th>Created</th>
                                <th>Modified</th>
                            </tr>
                            </thead>
                            <tbody>%s</tbody>
                        </table>
                    </main>         
                </body>
                </html>
                """.formatted(
                allUsers
                        .stream()
                        .map(u -> "<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>"
                                .formatted(u.getId(), u.getLogin(), u.getCreated(), u.getModified()))
                        .collect(Collectors.joining())
        ));
    }
}
