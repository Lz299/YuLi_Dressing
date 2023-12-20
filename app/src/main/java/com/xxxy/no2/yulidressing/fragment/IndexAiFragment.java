package com.xxxy.no2.yulidressing.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.xxxy.no2.yulidressing.R;
import com.xxxy.no2.yulidressing.adapter.IndexListViewAdapter;
import com.xxxy.no2.yulidressing.model.IndexListViewModel;
import com.xxxy.no2.yulidressing.model.ListVewListViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class IndexAiFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view1=inflater.inflate(R.layout.fragment_index_ai, container, false);
        ListView listView=(ListView)view1.findViewById(R.id.lv_index_listview);
        List<IndexListViewModel> list=(List<IndexListViewModel>)getArguments().getSerializable("list");
        listView.setAdapter(new IndexListViewAdapter(getContext(),list));
        return view1;
    }


    public IndexAiFragment addDate(List<IndexListViewModel> list) {
        IndexAiFragment fragment=new IndexAiFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable("list",(Serializable) list);
        fragment.setArguments(bundle);
        return fragment;
    }
}