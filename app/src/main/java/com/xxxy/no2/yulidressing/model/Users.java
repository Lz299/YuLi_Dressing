package com.xxxy.no2.yulidressing.model;

public class Users {
    private int users_id;
    private String username;
    private String password;
    private String phone;


    public Users() {
    }

    public Users(int users_id, String username, String password, String phone) {
        this.users_id = users_id;
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public String toString() {
        return "User{" +
                "user_id=" + users_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
