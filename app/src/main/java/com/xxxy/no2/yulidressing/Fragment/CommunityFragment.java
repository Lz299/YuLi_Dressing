package com.xxxy.no2.yulidressing.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xxxy.no2.yulidressing.Adapter.MainAdapter;
import com.xxxy.no2.yulidressing.Info.BannerDataInfo;
import com.xxxy.no2.yulidressing.Info.PostBean;
import com.xxxy.no2.yulidressing.R;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CommunityFragment extends Fragment {
    private View view;
    private Banner banner;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private TopFragment topFragment;
    private MainFragment mainFragment;
    RecyclerView Rc_fragment_main;
    private TextView tv_comm_tj,tv_comm_ps,tv_comm_dp,tv_comm_fx,tv_comm_xz,tv_comm_bb,tv_comm_zc;

    //推荐数据
    private int[] tj_imgs_show = {R.drawable.show1,R.drawable.show2,R.drawable.show3,R.drawable.show2,R.drawable.show1};
    private int[] tj_imgs_touxiang = {R.drawable.show1,R.drawable.show2,R.drawable.show3,R.drawable.show1,R.drawable.show2};
    private String[] tj_titlts = {"关于今日的穿搭意见，回头率一定爆表哈哈哈哈哈，整条街最亮的崽","今天的酷酷穿搭","分享今日穿搭","关于今日的穿搭意见，回头率一定爆表哈哈哈哈哈，整条街最亮的崽","今天的酷酷穿搭"};
    private String[] tj_usernames ={"是吃香菜","韩设计师","灰原丽香","是烦烦烦","Faiwj21"};
    private String[] tj_likes = {"12","5","7","88","9"};

    //配饰数据
    private int[] ps_imgs_show ={R.drawable.ps1,R.drawable.ps2,R.drawable.ps3,R.drawable.ps4,R.drawable.ps5};
    private int[] ps_imgs_touxiang = {R.drawable.show2,R.drawable.show1,R.drawable.show3,R.drawable.ps4,R.drawable.ps2};
    private String[] ps_titlts = {"美式风格配饰分享，小众高级感","男生必备基础配饰","这个项链诀绝子","# 戒指 黑暗风 手环 #","冬日氛围感围巾"};
    private String[] ps_usernames ={"阿樊","隔壁王叔","是吃香菜","韩设计师","灰原丽香"};
    private String[] ps_likes = {"14","8","6","35","16"};
    //搭配数据

    //发型数据

    //鞋子数据

    //包包数据

    //种草

    private Map<String, List<PostBean>> map;

    private List<BannerDataInfo> mBannerDataInfo = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_community, container, false);

        //**轮播图控件
        banner = view.findViewById(R.id.banner_lunbo);
            //模拟数据
        mBannerDataInfo.add(new BannerDataInfo(R.drawable.lunbo1,"标题一"));
        mBannerDataInfo.add(new BannerDataInfo(R.drawable.lunbo2,"标题二"));
        mBannerDataInfo.add(new BannerDataInfo(R.drawable.lunbo3,"标题三"));
        banner.setAdapter(new BannerImageAdapter<BannerDataInfo>(mBannerDataInfo) {
            @Override
            public void onBindView(BannerImageHolder holder, BannerDataInfo data, int position, int size) {
                holder.imageView.setImageResource(data.getImg());
            }
        })
                .addBannerLifecycleObserver(this)
                .setIndicator(new CircleIndicator(getContext()));

        //**标签栏Fragment事件
        setData();
        init();
        clickEvent();

        return view;
    }


    //初始化topFragment，拿到top的七个fragment按钮
    private void init() {
        fragmentManager = getChildFragmentManager();
        topFragment = (TopFragment) fragmentManager.findFragmentById(R.id.fragment_top);
        tv_comm_tj = topFragment.getView().findViewById(R.id.tv_comm_tj);
        tv_comm_ps = topFragment.getView().findViewById(R.id.tv_comm_ps);
        tv_comm_dp = topFragment.getView().findViewById(R.id.tv_comm_dp);
        tv_comm_fx = topFragment.getView().findViewById(R.id.tv_comm_fx);
        tv_comm_xz = topFragment.getView().findViewById(R.id.tv_comm_xz);
        tv_comm_bb = topFragment.getView().findViewById(R.id.tv_comm_bb);
        tv_comm_zc = topFragment.getView().findViewById(R.id.tv_comm_zc);
    }

  //  给两个标签绑定数据倒两个map
    private void setData() {
        map = new HashMap<>();
        List<PostBean> list_1tj = new ArrayList<>();
        List<PostBean> list_2ps = new ArrayList<>();
        //第一组数据
        for (int i = 0; i < tj_imgs_show.length; i++) {
            PostBean postBean = new PostBean();
            postBean.setPost_img(tj_imgs_show[i]);
            postBean.setPost_touximg(tj_imgs_touxiang[i]);
            postBean.setPost_title(tj_titlts[i]);
            postBean.setPost_username(tj_usernames[i]);
            postBean.setPost_like(tj_likes[i]);
            list_1tj.add(postBean);
        }
        map.put("1",list_1tj);
        //第二组数据
        for (int i = 0; i < ps_imgs_show.length; i++) {
            PostBean postBean = new PostBean();
            postBean.setPost_img(ps_imgs_show[i]);
            postBean.setPost_touximg(ps_imgs_touxiang[i]);
            postBean.setPost_title(ps_titlts[i]);
            postBean.setPost_username(ps_usernames[i]);
            postBean.setPost_like(ps_likes[i]);
            list_2ps.add(postBean);
        }
        map.put("2",list_2ps);
    }

    private void clickEvent() {
        //初始页面给的数据是 kiy为 1 map
        switchData(map.get("1"));
        tv_comm_tj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchData(map.get("1"));
                //给点击的按钮设置颜色样式
                tv_comm_tj.setBackgroundColor(Color.GRAY);
                tv_comm_ps.setBackgroundColor(Color.WHITE);
                tv_comm_dp.setBackgroundColor(Color.WHITE);
                tv_comm_fx.setBackgroundColor(Color.WHITE);
                tv_comm_xz.setBackgroundColor(Color.WHITE);
                tv_comm_bb.setBackgroundColor(Color.WHITE);
                tv_comm_zc.setBackgroundColor(Color.WHITE);
            }
        });
        tv_comm_ps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchData(map.get("2"));
                ////给点击的按钮设置颜色样式
                tv_comm_tj.setBackgroundColor(Color.WHITE);
                tv_comm_ps.setBackgroundColor(Color.GRAY);
                tv_comm_dp.setBackgroundColor(Color.WHITE);
                tv_comm_fx.setBackgroundColor(Color.WHITE);
                tv_comm_xz.setBackgroundColor(Color.WHITE);
                tv_comm_bb.setBackgroundColor(Color.WHITE);
                tv_comm_zc.setBackgroundColor(Color.WHITE);
            }
        });
        tv_comm_dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  switchData(map.get("3"));
                ////给点击的按钮设置颜色样式
                tv_comm_tj.setBackgroundColor(Color.WHITE);
                tv_comm_ps.setBackgroundColor(Color.WHITE);
                tv_comm_dp.setBackgroundColor(Color.GRAY);
                tv_comm_fx.setBackgroundColor(Color.WHITE);
                tv_comm_xz.setBackgroundColor(Color.WHITE);
                tv_comm_bb.setBackgroundColor(Color.WHITE);
                tv_comm_zc.setBackgroundColor(Color.WHITE);
            }
        });
        tv_comm_fx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  switchData(map.get("4"));
                ////给点击的按钮设置颜色样式
                tv_comm_tj.setBackgroundColor(Color.WHITE);
                tv_comm_ps.setBackgroundColor(Color.WHITE);
                tv_comm_dp.setBackgroundColor(Color.GRAY);
                tv_comm_fx.setBackgroundColor(Color.WHITE);
                tv_comm_xz.setBackgroundColor(Color.WHITE);
                tv_comm_bb.setBackgroundColor(Color.WHITE);
                tv_comm_zc.setBackgroundColor(Color.WHITE);
            }
        });
    }

    private void switchData(List<PostBean> list){
        fragmentManager = getChildFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        //getInstance写在MainFragment内
        mainFragment= new MainFragment().getInstance(list);
       // 将内容替换到MainFragment
        fragmentTransaction.replace(R.id.fragment_main,mainFragment);
        fragmentTransaction.commit();

    }
}