package org.itstep.dao.impl;

import org.itstep.DbUtils;
import org.itstep.dao.UserDao;
import org.itstep.data.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserDaoImpl implements UserDao {

    private final DbUtils dbUtils;

    private final static String INSERT = "INSERT INTO users(login, password) VALUES (?, ?)";
    private final static String SELECT = "SELECT id, login, password, created, modified FROM users";
    private final static String SelectById = "SELECT * FROM users WHERE id = %s;";

    public UserDaoImpl(String url, String username, String password) {
        dbUtils = DbUtils.getInstance();
        dbUtils.init(url, username, password);
    }

    @Override
    public Integer save(User data) {
        try {
            Optional<Connection> optionalConnection = dbUtils.getConnection();
            optionalConnection.ifPresent(connection -> {
                try {
//                    var stmt = connection.createStatement();
//                    String insertQuery = INSERT.formatted(data.getLogin(), data.getPassword());
//                    System.out.println("Insert Query: " + insertQuery);
//                    stmt.executeQuery(insertQuery);
                    var stmt = connection.prepareStatement(INSERT);
                    stmt.setString(1, data.getLogin());
                    stmt.setString(2, data.getPassword());
                    stmt.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public User findById(Integer number) throws SQLException {
        List<User> users = new CopyOnWriteArrayList<>();
        User[] rezult = new User[1];
        rezult[0] = null;
//        user[0] = null;
//        String selectTotal = SelectById.formatted(number);
//        System.out.println("selectTotal = " + selectTotal);
        try {
            Optional<Connection> optionalConnection = dbUtils.getConnection();
            optionalConnection.ifPresent(connection -> {
                try {
                    Statement stmt = connection.createStatement();
//                    ResultSet resultSet = stmt.executeQuery(selectTotal);
                    ResultSet resultSet = stmt.executeQuery(SELECT);
                    int id;
                    String login;
                    String password;
                    Timestamp created;
                    Timestamp modified;
                    while (resultSet.next()) {
                        id = resultSet.getInt("id");
                        login = resultSet.getString("login");
                        password = resultSet.getString("password");
                        created = resultSet.getTimestamp("created");
                        modified = resultSet.getTimestamp("modified");
                        System.out.println("id = " + id);
                        System.out.println("login = " + login);
                        System.out.println("password = " + password);
                        System.out.println("created = " + created.toString());
                        System.out.println("modified = " + modified.toString());
                        users.add(new User(id, login, password, created, modified));
                    }
                    users.stream().forEach(us -> System.out.println(us.toString()));
                    for (User user: users){
                        if (user.getId() == number){
                            rezult[0] = user;
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rezult[0];
    }


    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            Optional<Connection> optionalConnection = dbUtils.getConnection();
            optionalConnection.ifPresent(connection -> {
                try {
                    var stmt = connection.createStatement();
                    ResultSet resultSet = stmt.executeQuery(SELECT);
                    while (resultSet.next()) {
                        users.add(new User(
                                resultSet.getInt("id"),
                                resultSet.getString("login"),
                                resultSet.getString("password"),
                                resultSet.getTimestamp("created"),
                                resultSet.getTimestamp("modified")
                        ));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
}
