package com.xxxy.no2.yulidressing.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xxxy.no2.yulidressing.Info.PostBean;
import com.xxxy.no2.yulidressing.R;

import java.util.List;
import java.util.Map;

public class MainAdapter extends RecyclerView.Adapter<MainViewHolder>{
    private List<PostBean> myList;
    private Context mContext;
    private LayoutInflater mInflater;

    public MainAdapter(Context mContext,List<PostBean> mylist){
        this.myList = mylist;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.showitem, parent, false);//物理托盘和逻辑托盘对应
        MainViewHolder mainholder = new MainViewHolder(view);

        return mainholder;
    }


    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        //盘子里东西
        PostBean  data = myList.get(position);
        holder.itemShowImg.setImageResource(data.getPost_img());
        holder.itemtitle.setText(data.getPost_title());
        holder.itemShowtx.setImageResource(data.getPost_touximg());
        holder.itemname.setText(data.getPost_username());
        holder.itemlike.setText(data.getPost_like());

    }

    @Override
    public int getItemCount() {return myList.size();}
}

class MainViewHolder extends RecyclerView.ViewHolder{
      ImageView itemShowImg,itemShowtx;
     TextView itemtitle,itemname,itemlike;

    public MainViewHolder(@NonNull View itemView) {
        super(itemView);
        itemShowImg = itemView.findViewById(R.id.show_im_img);
        itemtitle = itemView.findViewById(R.id.show_title);
        itemShowtx = itemView.findViewById(R.id.show_iv_touxiang);
        itemname = itemView.findViewById(R.id.show_tv_name);
        itemlike = itemView.findViewById(R.id.show_like);
    }
}
