package com.xxxy.no2.yulidressing.model;

public class Users {
    private int users_id;
    private String username;
    private String password;
    private String phone;
    private String users_img;


    public Users() {
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

    public String getUsers_img() {
        return users_img;
    }

    public void setUsers_img(String users_img) {
        this.users_img = users_img;
    }

    @Override
    public String toString() {
        return "Users{" +
                "users_id=" + users_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", users_img='" + users_img + '\'' +
                '}';
    }
}
