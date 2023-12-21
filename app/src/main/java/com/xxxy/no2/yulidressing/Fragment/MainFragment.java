package com.xxxy.no2.yulidressing.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

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

    public MainFragment getInstance(List<Map<String,Object>> list){
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
            List<Map<String,Object>> list = (List<Map<String,Object>>) getArguments().
                    getSerializable("list");
            MainAdapter adapter = new MainAdapter(list,getActivity());
            rv_community.setAdapter(adapter);
        }
        return view;
    }
}