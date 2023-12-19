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

public class PersonalAdapter extends RecyclerView.Adapter<PersonalHolder> {

    private List<Map<String, Object>> data;
    private Context context;

    public PersonalAdapter(List<Map<String, Object>> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public PersonalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.personal_item, parent, false);
        return new PersonalHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonalHolder holder, int position) {
        Map<String, Object> map = data.get(position);
        int image = (int) map.get("image");
        holder.itemImage.setImageResource(image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}

class PersonalHolder extends RecyclerView.ViewHolder {

    ImageView itemImage;
    public PersonalHolder(@NonNull View itemView) {
        super(itemView);
        itemImage = itemView.findViewById(R.id.rv_item_image);

    }

}