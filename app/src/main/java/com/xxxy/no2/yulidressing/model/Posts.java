package com.xxxy.no2.yulidressing.model;

//帖子
public class Posts {
    private int posts_id;
    private String posts_title;
    private String posts_content;
    private Users users;

    public Posts() {
    }

    public Posts(int posts_id, Users users, String posts_title, String posts_content) {
        this.posts_id = posts_id;
        this.users = users;
        this.posts_title = posts_title;
        this.posts_content = posts_content;
    }

    public int getPosts_id() {
        return posts_id;
    }

    public void setPosts_id(int posts_id) {
        this.posts_id = posts_id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users_id) {
        this.users = users_id;
    }

    public String getPosts_title() {
        return posts_title;
    }

    public void setPosts_title(String posts_title) {
        this.posts_title = posts_title;
    }

    public String getPosts_content() {
        return posts_content;
    }

    public void setPosts_content(String posts_content) {
        this.posts_content = posts_content;
    }

    @Override
    public String toString() {
        return "Posts{" +
                "posts_id=" + posts_id +
                ", posts_title='" + posts_title + '\'' +
                ", posts_content='" + posts_content + '\'' +
                ", users=" + users +
                '}';
    }
}
