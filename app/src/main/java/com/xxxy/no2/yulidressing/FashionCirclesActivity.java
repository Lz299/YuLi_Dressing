package com.xxxy.no2.yulidressing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.xxxy.no2.yulidressing.Adapter.FashionCirclesAdapter;
import com.xxxy.no2.yulidressing.Adapter.HorizontalVpAdapter;
import com.xxxy.no2.yulidressing.Info.ViewPager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FashionCirclesActivity extends AppCompatActivity {


    //1、建立数据源
    String[] titles=new String[]{"张亚琼班","快乐家园","小仲","群英荟萃","70次计算机等级考试教师群","初三8班语文学习群","文件传输助手","Android课程助教",
            "订阅号消息","信息学院-信息通知群","东小62班","安博职业教育","快乐家园","小仲","群英荟萃"
    };
    String[] infos=new String[]{"高晨星爸爸[视频]","五一假期一起去吃饭","收到","","付军：监考教师快速就位","已收到","[图片]","第三组作业已提交",
            "CSP测试将在10月21日举行","本周四下午3点会议室开会","冷漠：好的","集成电路可测性设计(DFT)工程师","五一假期一起去吃饭","收到","",
    };
    String[] times=new String[]{"23","11","8","5","9","8","87","2",
            "15","15","14","19","5","93","8"
    };
    int[] images=new int[]{R.drawable.addimg,R.drawable.tubiao,R.drawable.addimg,R.drawable.tubiao,R.drawable.addimg,R.drawable.tubiao,R.drawable.addimg,
            R.drawable.addimg,R.drawable.tubiao,R.drawable.addimg,R.drawable.tubiao,R.drawable.addimg,R.drawable.tubiao,R.drawable.addimg,R.drawable.tubiao,
    };
    RecyclerView rvWechat;
    private List<ViewPager> viewPagerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fashion_circles);

        initData();
        ViewPager2 viewPager2 = findViewById(R.id.view_pager);
        HorizontalVpAdapter horizontalVpAdapter = new HorizontalVpAdapter(viewPagerList);
        viewPager2.setAdapter(horizontalVpAdapter);



        init();

    }
    private  void init(){
        rvWechat=findViewById(R.id.rv_wechat);
        //2.建立适配器
        FashionCirclesAdapter adapter=new FashionCirclesAdapter(getData(),this);
        LinearLayoutManager layoutManager =  new LinearLayoutManager(this);
        rvWechat.setLayoutManager(layoutManager);
        //3.设置适配器
        rvWechat.setAdapter(adapter);



    }
    //1.建立数据源第二步
    private List<Map<String,Object>> getData(){
        List<Map<String,Object>> myList=new ArrayList<Map<String,Object>>();
        for (int i=0;i<titles.length;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("title",titles[i]);
            map.put("info",infos[i]);
            map.put("time",times[i]);
            map.put("image",images[i]);
            myList.add(map);
        }
        return myList;
    }

    private void initData(){
        for (int i = 0; i < 2; i++){
            ViewPager viewPager1 = new ViewPager(R.drawable.show1);
            viewPagerList.add(viewPager1);
            ViewPager viewPager2 = new ViewPager(R.drawable.show2);
            viewPagerList.add(viewPager2);
            ViewPager viewPager3 = new ViewPager(R.drawable.show3);
            viewPagerList.add(viewPager3);
            ViewPager viewPager4 = new ViewPager(R.drawable.show4);
            viewPagerList.add(viewPager4);
        }
    }

}