package com.xxxy.no2.yulidressing.model;

import java.util.List;

public class IndexListViewModel {
    private String bigimgurl;
    private String info;

    private List<ListVewListViewModel> listView;

    public String getBigimgurl() {
        return bigimgurl;
    }

    public void setBigimgurl(String bigimgurl) {
        this.bigimgurl = bigimgurl;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<ListVewListViewModel> getListView() {
        return listView;
    }

    public void setListView(List<ListVewListViewModel> listView) {
        this.listView = listView;
    }

    public IndexListViewModel() {
    }


    public IndexListViewModel(String bigimgurl, String info, List<ListVewListViewModel> listView) {
        this.bigimgurl = bigimgurl;
        this.info = info;
        this.listView = listView;
    }
}
