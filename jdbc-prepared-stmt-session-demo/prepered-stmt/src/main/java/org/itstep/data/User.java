package org.itstep.data;

import java.sql.Timestamp;

public class User {
    private Integer id;
    private String login;
    private String password;
    private Timestamp created;
    private Timestamp modified;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User() {
    }

    public User(Integer id, String login, String password, Timestamp created, Timestamp modified) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.created = created;
        this.modified = modified;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", login='" + login + '\'' +
               ", password='" + password + '\'' +
               ", created=" + created +
               ", modified=" + modified +
               '}';
    }

    public String toStringForHTML() {
        return "<div class='task'>" +
               "<span class='taskid'>" + id + "</span>" +
               "<span class='tasklogin'>" + login + "</span>" +
               "<span class='taskpassword'>" + password + "</span>" +
               "<span class='taskcreated'>" + created.toString() + "</span>" +
               "<span class='taskmodified'>" + modified.toString() + "</span>" +
               "</div>";
    }
}
