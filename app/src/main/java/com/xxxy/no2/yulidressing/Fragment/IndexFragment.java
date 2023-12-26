package com.xxxy.no2.yulidressing.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.xxxy.no2.yulidressing.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class IndexFragment extends Fragment {
    private View view;
    private ListView showlistview;
    SimpleAdapter simpleAdapter;

    private int[] imgs_index_show = new int[]{R.drawable.show2,R.drawable.show1};
    private int[] imgs_index_item1 = new int[]{R.drawable.clothes1,R.drawable.clothes1};
    private int[] imgs_index_item2 = new int[]{R.drawable.clothes2,R.drawable.clothes2};
    private int[] imgs_index_item3 = new int[]{R.drawable.clothes3,R.drawable.clothes3};
    private int[] imgs_index_item4 = new int[]{R.drawable.clothes4,R.drawable.clothes4};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_index, container, false);

        //显示主页的穿搭搭配模块
        showlistview = view.findViewById(R.id.lv_showitem);
        simpleAdapter = new SimpleAdapter(getContext(),getData(),R.layout.indexitem,
                new String[]{"im_show","im_item1","im_item2","im_item3","im_item4"},new int[]{R.id.iv_show,R.id.imageView1,R.id.imageView2,R.id.imageView3,R.id.imageView4});
        showlistview.setAdapter(simpleAdapter);

        return view;
    }

    private List<Map<String,Object>> getData() {
        List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
        //把三个数组变成List《map《》》
        for(int i = 0;i<imgs_index_show.length;i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("im_show",imgs_index_show[i]);
            map.put("im_item1",imgs_index_item1[i]);
            map.put("im_item2",imgs_index_item2[i]);
            map.put("im_item3",imgs_index_item3[i]);
            map.put("im_item4",imgs_index_item4[i]);

            listItems.add(map);
        }
        return listItems;
    }
}