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

public class PersonalAdapter3 extends RecyclerView.Adapter<PersonalHolder3> {

    private List<Map<String, Object>> data3;
    private Context context;

    public PersonalAdapter3(List<Map<String, Object>> data3, Context context) {
        this.data3 = data3;
        this.context = context;
    }

    @NonNull
    @Override
    public PersonalHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.personal_item, parent, false);
        return new PersonalHolder3(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonalHolder3 holder, int position) {
        Map<String, Object> map = data3.get(position);
        int image = (int) map.get("image");
        holder.itemImage.setImageResource(image);
    }

    @Override
    public int getItemCount() {
        return data3.size();
    }

}

class PersonalHolder3 extends RecyclerView.ViewHolder {

    ImageView itemImage;
    public PersonalHolder3(@NonNull View itemView) {
        super(itemView);
        itemImage = itemView.findViewById(R.id.rv_item_image);

    }

}