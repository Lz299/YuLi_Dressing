package com.xxxy.no2.yulidressing.Info;

import java.io.Serializable;

public class PostBean implements Serializable {
    private int post_img;
    private int post_touximg;
    private String post_title;
    private String post_username;
    private String post_like;


    public PostBean(int post_img, int post_touximg, String post_title, String post_username, String post_like) {
        this.post_img = post_img;
        this.post_touximg = post_touximg;
        this.post_title = post_title;
        this.post_username = post_username;
        this.post_like = post_like;
    }

    public PostBean() {

    }

    @Override
    public String toString() {
        return "PostBean{" +
                "post_img=" + post_img +
                ", post_touximg=" + post_touximg +
                ", post_title='" + post_title + '\'' +
                ", post_username='" + post_username + '\'' +
                ", post_like='" + post_like + '\'' +
                '}';
    }

    public int getPost_img() {
        return post_img;
    }

    public void setPost_img(int post_img) {
        this.post_img = post_img;
    }

    public int getPost_touximg() {
        return post_touximg;
    }

    public void setPost_touximg(int post_touximg) {
        this.post_touximg = post_touximg;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public String getPost_username() {
        return post_username;
    }

    public void setPost_username(String post_username) {
        this.post_username = post_username;
    }

    public String getPost_like() {
        return post_like;
    }

    public void setPost_like(String post_like) {
        this.post_like = post_like;
    }
}

