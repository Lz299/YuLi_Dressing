package com.xxxy.no2.yulidressing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.xxxy.no2.yulidressing.R;
import com.xxxy.no2.yulidressing.hoder.IndexListViewHoder;
import com.xxxy.no2.yulidressing.model.IndexListViewModel;
import com.xxxy.no2.yulidressing.model.ListVewListViewModel;

import java.util.ArrayList;
import java.util.List;

public class IndexListViewAdapter extends BaseAdapter {

    private  LayoutInflater layoutInflater;
    private Context context;
    private List<IndexListViewModel> list;

    public IndexListViewAdapter( Context context, List<IndexListViewModel> list) {
        this.layoutInflater = layoutInflater.from(context);
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
        IndexListViewHoder hoder=null;
        if (view==null){
            view=layoutInflater.inflate(R.layout.index_listview_item,null,false);
            hoder=new IndexListViewHoder();
            hoder.bigimg=view.findViewById(R.id.iv_bigimg);
            hoder.info=view.findViewById(R.id.tv_info);
            hoder.listView=(ListView) view.findViewById(R.id.lv_list_list);
            view.setTag(hoder);
        }else{
            hoder=(IndexListViewHoder)view.getTag();
        }
        Glide.with(context).load(list.get(i).getBigimgurl()).into((ImageView) hoder.bigimg);


        hoder.info.setText(list.get(i).getInfo());

        return view;
    }


}
