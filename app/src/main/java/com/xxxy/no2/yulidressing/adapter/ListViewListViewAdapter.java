package com.xxxy.no2.yulidressing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xxxy.no2.yulidressing.R;
import com.xxxy.no2.yulidressing.hoder.IndexListViewHoder;
import com.xxxy.no2.yulidressing.hoder.ListViewListViewHoder;
import com.xxxy.no2.yulidressing.model.ListVewListViewModel;

import java.util.List;

public class ListViewListViewAdapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    Context context;
    List<ListVewListViewModel> list;

    public ListViewListViewAdapter(Context context, List<ListVewListViewModel> list){
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return  list.size();
    }

    @Override
    public Object getItem(int i) {
        return  list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ListViewListViewHoder hoder=null;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.listview_listview_item, null);
            hoder=new ListViewListViewHoder();
            hoder.imageView=view.findViewById(R.id.iv_listview_img);
            view.setTag(hoder);
        }else {
            hoder=(ListViewListViewHoder) view.getTag();
        }
        Glide.with(context).load(list.get(i).getImgurl()).into((ImageView) hoder.imageView);
        return view;

    }














}
