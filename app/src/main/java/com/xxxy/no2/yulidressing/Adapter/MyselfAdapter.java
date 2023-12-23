package com.xxxy.no2.yulidressing.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xxxy.no2.yulidressing.R;

import java.util.List;
import java.util.Map;

public class MyselfAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<Map<String,Object>> myList;
    private Context mContext;
    private LayoutInflater mInflater;

    public MyselfAdapter(List<Map<String,Object>> mylist, Context mContext) {
        this.myList = mylist;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.showitem, parent, false);//物理托盘和逻辑托盘对应
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //盘子里东西
        Map<String, Object> map = myList.get(position);
        holder.itemShowImg.setImageResource((int) map.get("itemImg"));
        holder.itemtitle.setText(map.get("itemTitle").toString());
        holder.itemShowtx.setImageResource((int) map.get("itemTouxiang"));
        holder.itemname.setText(map.get("itemName").toString());
        holder.itemlike.setText(map.get("itemLike").toString());
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }


}

class ViewHolder extends RecyclerView.ViewHolder{
    ImageView itemShowImg,itemShowtx;

    TextView itemtitle,itemname,itemlike;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        itemShowImg = itemView.findViewById(R.id.show_im_img);
        itemtitle = itemView.findViewById(R.id.show_title);
        itemShowtx = itemView.findViewById(R.id.show_iv_touxiang);
        itemname = itemView.findViewById(R.id.show_tv_name);
        itemlike = itemView.findViewById(R.id.show_like);

    }
}
