package com.xxxy.no2.yulidressing.model;

public class ListVewListViewModel {
    private String imgurl;

    public ListVewListViewModel(String imgurl) {
        this.imgurl = imgurl;
    }

    public ListVewListViewModel() {
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    @Override
    public String toString() {
        return "ListVewListViewModel{" +
                "imgurl='" + imgurl + '\'' +
                '}';
    }
}
