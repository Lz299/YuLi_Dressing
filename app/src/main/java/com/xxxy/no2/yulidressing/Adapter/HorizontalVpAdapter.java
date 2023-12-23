package com.xxxy.no2.yulidressing.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xxxy.no2.yulidressing.Info.ViewPager;
import com.xxxy.no2.yulidressing.R;

import java.util.List;

public class HorizontalVpAdapter extends RecyclerView.Adapter<HorizontalVpAdapter.HorizontalVpViewHolder> {

    private List<ViewPager> viewpagerList;

    public HorizontalVpAdapter(List<ViewPager> viewpagerList) {
        this.viewpagerList = viewpagerList;
    }

    @NonNull
    @Override
    public HorizontalVpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fashion_ircles_item_2,parent,false);
        return new HorizontalVpViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalVpViewHolder holder, int position) {
        ViewPager viewpager = viewpagerList.get(position);
        holder.mImage.setImageResource(viewpager.getImage());
    }

    @Override
    public int getItemCount() {
        return viewpagerList.size();
    }

    class HorizontalVpViewHolder extends RecyclerView.ViewHolder {
        ImageView mImage;

        HorizontalVpViewHolder(@NonNull View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image);
        }
    }

}
