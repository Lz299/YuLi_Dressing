package com.xxxy.no2.yulidressing.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.xxxy.no2.yulidressing.Adapter.MainAdapter;
import com.xxxy.no2.yulidressing.Info.PostBean;
import com.xxxy.no2.yulidressing.R;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class MainFragment extends Fragment {

    private RecyclerView rv_community;
    public MainFragment() {
        // Required empty public constructor
    }

    //将传入的list参数序列化后存储到bundle中，键名为"list"。这里使用了强制类型转换(Serializable) list，以确保list可以被序列化。
    public MainFragment getInstance(List<PostBean> list){
        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("list",(Serializable) list);
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.community_main_layout, container, false);
        rv_community = view.findViewById(R.id.rv_community);
        if(getArguments() != null){
            List<PostBean> list = ( List<PostBean>) getArguments().
                    getSerializable("list");
            rv_community.setLayoutManager(new LinearLayoutManager(getContext()));
            //这里绑定adapter
            MainAdapter adapter = new MainAdapter(getActivity(),list);
            rv_community.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
            rv_community.setAdapter(adapter);
        }
        return view;
    }
}