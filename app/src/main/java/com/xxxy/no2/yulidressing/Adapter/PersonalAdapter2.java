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

public class PersonalAdapter2 extends RecyclerView.Adapter<PersonalHolder2> {

    private List<Map<String, Object>> data2;
    private Context context;

    public PersonalAdapter2(List<Map<String, Object>> data2, Context context) {
        this.data2 = data2;
        this.context = context;
    }

    @NonNull
    @Override
    public PersonalHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.personal_item, parent, false);
        return new PersonalHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonalHolder2 holder, int position) {
        Map<String, Object> map = data2.get(position);
        int image = (int) map.get("image");
        holder.itemImage.setImageResource(image);
    }

    @Override
    public int getItemCount() {
        return data2.size();
    }

}

class PersonalHolder2 extends RecyclerView.ViewHolder {

    ImageView itemImage;
    public PersonalHolder2(@NonNull View itemView) {
        super(itemView);
        itemImage = itemView.findViewById(R.id.rv_item_image);

    }

}