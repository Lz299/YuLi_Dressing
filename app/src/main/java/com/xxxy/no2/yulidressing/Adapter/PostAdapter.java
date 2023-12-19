package com.xxxy.no2.yulidressing.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xxxy.no2.yulidressing.R;

import java.util.List;
import java.util.Map;

public class PostAdapter  extends RecyclerView.Adapter<PostViewHolder> {
    private List<Map<String,Object>> myList;
    private Context mContext;
    private LayoutInflater mInflater;

    public PostAdapter(List<Map<String,Object>> mylist, Context mContext) {
        this.myList = mylist;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.postitem, parent, false);//物理托盘和逻辑托盘对应
        PostViewHolder holder = new PostViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Map<String, Object> map = myList.get(position);
        holder.post_imgs.setImageResource((int) map.get("addImgs"));
    }


    @Override
    public int getItemCount() {
        return myList.size();
    }

}

class PostViewHolder extends RecyclerView.ViewHolder{
    ImageView post_imgs;
    public PostViewHolder(@NonNull View itemView) {
        super(itemView);
        post_imgs = itemView.findViewById(R.id.post_item_img);
    }
}
